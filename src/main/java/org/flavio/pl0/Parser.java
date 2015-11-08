package org.flavio.pl0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;

import static java.text.MessageFormat.format;
import static org.flavio.pl0.SymbolType.*;

public class Parser {

    private final static Logger log = LoggerFactory.getLogger(Parser.class);

    private Scanner scanner;
    private boolean error = false;

    private final Set<SymbolType> plusMinus = new HashSet<>();
    private final Set<SymbolType> multiplyDivide = new HashSet<>();
    private final Set<SymbolType> booleanOperators = new HashSet<>();

    private final IDTable idTable = new IDTable();

    private Symbol last;


    public Parser(Scanner scanner) {
        this.scanner = scanner;
        plusMinus.add(PLUS);
        plusMinus.add(MINUS);
        multiplyDivide.add(SLASH);
        multiplyDivide.add(TIMES);
        booleanOperators.add(EQ);
        booleanOperators.add(NE);
        booleanOperators.add(LT);
        booleanOperators.add(GT);
        booleanOperators.add(LE);
        booleanOperators.add(GE);
    }

    private boolean accept(SymbolType symbolType) {
        if (scanner.getSymbol().getType() == symbolType) {
            last = scanner.getSymbol();
            scanner.next();
            return true;
        }
        return false;
    }

    private boolean accept(Collection<SymbolType> symbolTypes) {
        if (symbolTypes.contains(scanner.getSymbol().getType())) {
            last = scanner.getSymbol();
            scanner.next();
            return true;
        }
        return false;
    }

    private boolean expect(Collection<SymbolType> symbols) {
        if (accept(symbols)) {
            return true;
        }
        error = true;
        log.error("Unexpected token:{} at line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), symbols);
        return false;
    }

    private boolean expect(SymbolType symbolType) {
        if (accept(symbolType)) {
            return true;
        }
        error = true;
        log.error("Unexpected token:{} at line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), symbolType);
        return false;
    }


    public void scan() {
        log.debug("PROGRAM");
        scanner.next();
        block(0);
        expect(PERIOD);
    }

    private void block(int base) {
        BaseAndOffset baseAndOffset = new BaseAndOffset(base, 0);

        log.debug(scanner.toString());
        if (accept(CONST)) {
            cons(baseAndOffset);
        }
        if (accept(VAR)) {
            var(baseAndOffset);
        }
        while (accept(PROCEDURE)) {
            procedure(baseAndOffset);
        }
        preposition(baseAndOffset);
        idTable.removeScope(baseAndOffset);
    }

    private void preposition(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        if (accept(IDENTIFIER)) {
            log.debug("ASSIGN");
            Optional<ID> variable = idTable.findVariable(last.getValue(), baseAndOffset);
            if (!variable.isPresent()) {
                error = true;
                log.error(format("Variable \"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
            }
            expect(ASSIGN);
            expression(baseAndOffset);
        }
        if (accept(CALL)) {
            log.debug("CALL");
            expect(IDENTIFIER);
            Optional<ID> procedure = idTable.findProcedure(last.getValue(), baseAndOffset);
            if (!procedure.isPresent()) {
                error = true;
                log.error(format("Procedure \"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
            }
        }
        if (accept(BEGIN)) {
            log.debug("BEGIN");
            preposition(baseAndOffset);
            while (accept(SEMICOLON)) {
                preposition(baseAndOffset);
            }
            expect(END);
        }
        if (accept(IF)) {
            condition(baseAndOffset);
            expect(THEN);
            preposition(baseAndOffset);
        }
        if (accept(WHILE)) {
            condition(baseAndOffset);
            expect(DO);
            preposition(baseAndOffset);
        }
        if (accept(READLN)) {
            log.debug("READLN");
            expect(LPAREN);
            do {
                expect(IDENTIFIER);
                Optional<ID> variable = idTable.findVariable(last.getValue(), baseAndOffset);
                if (!variable.isPresent()) {
                    error = true;
                    log.error(format("Variable \"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
                }
            }
            while (accept(COMMA));
            expect(RPAREN);
        }

        if (accept(WRITE)) {
            log.debug("WRITE");
            expect(LPAREN);
            do {
               if (accept(STRING)) {

               } else {
                   expression(baseAndOffset);
               }
            } while (accept(COMMA));
            expect(RPAREN);
        }

        if (accept(WRITELN)) {
            log.debug("WRITELN");
            if (accept(LPAREN)) {
                do {
                    if (accept(STRING)) {

                    } else {
                        expression(baseAndOffset);
                    }
                } while (accept(COMMA));
                expect(RPAREN);
            }
        }
    }

    private void condition(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        if (accept(ODD)) {
            expression(baseAndOffset);
        } else {
            expression(baseAndOffset);
            expect(booleanOperators);
            expression(baseAndOffset);
        }
    }

    private void expression(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        accept(plusMinus);
        term(baseAndOffset);
        while (accept(plusMinus)) {
            term(baseAndOffset);
        }
    }

    private void term(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        factor(baseAndOffset);
        while (accept(multiplyDivide)) {
            factor(baseAndOffset);
        }
    }

    private void factor(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        if (accept(IDENTIFIER)) {
            Optional var = idTable.findVariable(last.getValue(), baseAndOffset);
            Optional constant = idTable.findConst(last.getValue(), baseAndOffset);
            if (!var.isPresent() && !constant.isPresent()) {
                error = true;
                log.error(format("\"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
            }
            return;
        }
        if (accept(INTEGER)) {
            return;
        }
        if (accept(LPAREN)) {
            expression(baseAndOffset);
            expect(RPAREN);
            return;
        }
        error = true;
        log.error("Unexpected token:{} at line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), "expresion");
    }

    private void procedure(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        ID id = new ID();
        id.setType(IDType.PROCEDURE);
        id.setName(scanner.getSymbol().getValue());
        expect(IDENTIFIER);
        expect(SEMICOLON);
        if (!idTable.addId(id, baseAndOffset)) {
            error = true;
            log.error(MessageFormat.format("{0} {1} already declared", id.getType(), id.getName()));
        }
        block(baseAndOffset.getBasePlusOffset());
        expect(SEMICOLON);
    }

    private void cons(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        while (true) {
            ID id = new ID();
            id.setType(IDType.CONST);
            id.setName(scanner.getSymbol().getValue());
            expect(IDENTIFIER);
            expect(EQ);
            expect(INTEGER);
            if (!idTable.addId(id, baseAndOffset)) {
                error = true;
                log.error(MessageFormat.format("{0} {1} already declared", id.getType(), id.getName()));
            }
            if (!accept(COMMA)) break;
        }
        expect(SEMICOLON);
    }

    private void var(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        while (true) {
            ID id = new ID();
            id.setType(IDType.VAR);
            id.setName(scanner.getSymbol().getValue());
            expect(IDENTIFIER);
            if (!idTable.addId(id, baseAndOffset)) {
                error = true;
                log.error(MessageFormat.format("{0} already declared", id.getName()));
            }
            if (!accept(COMMA)) break;
        }
        expect(SEMICOLON);
    }

    public boolean isError() {
        return error;
    }

}
