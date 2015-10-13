package org.flavio.pl0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.flavio.pl0.SymbolType.*;

public class Parser {

    private final static Logger log = LoggerFactory.getLogger(Parser.class);

    private Scanner scanner;
    private boolean error = false;

    private final Set<SymbolType> plusMinus = new HashSet<>();
    private final Set<SymbolType> multiplyDivide = new HashSet<>();
    private final Set<SymbolType> booleanOperators = new HashSet<>();

    public Parser (Scanner scanner){
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

    private boolean accept(SymbolType symbolType){
        if (scanner.getSymbol().getType() == symbolType){
            scanner.next();
            return true;
        }
        return false;
    }

    private boolean accept(Collection<SymbolType> symbolTypes){
        if (symbolTypes.contains(scanner.getSymbol().getType())){
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
        log.error("Unexpected token:{} @ line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), symbols);
        return false;
    }

    private boolean expect(SymbolType symbolType) {
        if (accept(symbolType)) {
            return true;
        }
        error = true;
        log.error("Unexpected token:{} @ line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), symbolType);
        return false;
    }


    public void scan() {
        log.debug("PROGRAM");
        scanner.next();
        block();
        expect(PERIOD);
    }

    private void block() {
        log.debug("BLOCK");
        if (accept(CONST)) {
            cons();
        }
        if (accept(VAR)) {
            var();
        }
        while(accept (PROCEDURE)) {
            procedure();
        }
        preposition();
    }

    private void preposition() {
        log.debug("PREPOSITION");
        if (accept(IDENTIFIER)) {
            log.debug("ASSIGN");
            expect(ASSIGN);
            expression();
        }
        if (accept(CALL)) {
            expect(IDENTIFIER);
        }
        if(accept(BEGIN)) {
           log.debug("BEGIN");
           preposition();
           while (accept(SEMICOLON)) {
               preposition();
           }
           expect(END);
        }
        if(accept(IF)) {
            condition();
            expect(THEN);
            preposition();
        }
        if(accept(WHILE)) {
            condition();
            expect(DO);
            preposition();
        }
    }

    private void condition() {
        if(accept(ODD)) {
            expression();
        } else {
            expression();
            expect(booleanOperators);
            expression();
        }
    }

    private void expression() {
        log.debug("EXPRESSION");
        accept(plusMinus);
        term();
        while(accept(plusMinus)) {
            term();
        }
    }

    private void term() {
        log.debug("TERM");
        factor();
        while (accept(multiplyDivide)) {
            factor();
        }
    }

    private void factor() {
        log.debug("FACTOR");
        if (accept(IDENTIFIER)) {
            return;
        }
        if(accept(INTEGER)) {
            return;
        }
        if(accept(LPAREN)) {
            expression();
            expect(RPAREN);
            return;
        }
        error = true;
        log.error("Unexpected token:{} @ line {}. {} expected", scanner.getSymbol().getValue(), scanner.lineNumber(), "expresion");
    }

    private void procedure() {
        log.debug("PROCEDURE");
        expect(IDENTIFIER);
        expect(SEMICOLON);
        block();
        expect(SEMICOLON);
    }

    private void cons() {
        log.debug("CONST");
        while (true) {
            expect(IDENTIFIER);
            expect(EQ);
            expect(INTEGER);
            if (!accept(COMMA)) break;
        }
        expect(SEMICOLON);
    }

    private void var() {
        log.debug("VAR");
        while (true) {
            expect(IDENTIFIER);
            if (!accept(COMMA)) break;
        }
        expect(SEMICOLON);
    }

    public boolean isError() {
        return error;
    }
}
