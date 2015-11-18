package org.flavio.pl0;

import org.flavio.pl0.generator.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.text.MessageFormat.format;
import static org.flavio.pl0.SymbolType.*;

public class Parser {

    private final static Logger log = LoggerFactory.getLogger(Parser.class);

    private Scanner scanner;
    private boolean error = false;

    private final Set<SymbolType> plusMinus = new HashSet<>();
    private final Set<SymbolType> multiplyDivide = new HashSet<>();
    private final Set<SymbolType> booleanOperators = new HashSet<>();

    private final IDTable idTable;
    private CodeGenerator generator;

    private Symbol last;


    public Parser(Scanner scanner, IDTable idTable, CodeGenerator codeGenerator) {
        this.idTable = idTable;
        this.generator = codeGenerator;
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

    public void compile(String path) {
        generator.movEdi((long) 0);
        program();
        generator.fixUp(1153, generator.getNumberAt(64) + generator.getContentSize());
        for (int i = 0; i < idTable.getNumberOfVariables(); i++) {
            generator.addNumber(0);
        }
        generator.fixHeader();
        if (path != null) {
            generateFile(path);
        }
    }

    private void generateFile(String pathStr) {
        Path path = Paths.get(pathStr);
        try (OutputStream writer = new FileOutputStream(pathStr)) {
            for (int i = 0; i < generator.getContent().size(); i++) {
                writer.write(generator.getContent().get(i));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }
        Set<PosixFilePermission> permissions = new HashSet<>();
        permissions.add(PosixFilePermission.OTHERS_READ);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_READ);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OWNER_WRITE);
        permissions.add(PosixFilePermission.OWNER_READ);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        try {
            Files.setPosixFilePermissions(path, permissions);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void program() {
        log.debug("PROGRAM");
        scanner.next();
        block(0);
        expect(PERIOD);
        generator.jmp(generator.distanceTo(0x300));
    }

    private void block(int base) {
        BaseAndOffset baseAndOffset = new BaseAndOffset(base, 0);

        generator.jmp(0);

        int beforeDeclarations = generator.getContentSize();

        log.debug(scanner.toString());
        if (accept(CONST)) {
            cons(baseAndOffset);
        }
        if (accept(VAR)) {
            var(baseAndOffset);
        }
        while (accept(PROCEDURE)) {
            procedure(baseAndOffset);
            generator.ret();
        }

        if (generator.getContentSize() - beforeDeclarations == 0) {
            generator.removeLastJump();
        } else {
            generator.fixUp(beforeDeclarations - 4, generator.getContentSize() - beforeDeclarations);
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
            if (!error) {
                generator.popEax();
                generator.movEdiPlusOffsetEax(variable.get().getValue());
            }
        }
        if (accept(CALL)) {
            log.debug("CALL");
            expect(IDENTIFIER);
            Optional<ID> procedure = idTable.findProcedure(last.getValue(), baseAndOffset);
            if (!procedure.isPresent()) {
                error = true;
                log.error(format("Procedure \"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
            } else {
                generator.call(procedure.get().getValue() - (generator.getContentSize() + 5));
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
            boolean lparen = accept(LPAREN);
            condition(baseAndOffset);
            if (lparen) {
                expect(RPAREN);
            }
            int size = generator.getContentSize();
            expect(THEN);
            preposition(baseAndOffset);
            generator.fixUp(size - 4, generator.getContentSize() - size);
        }
        if (accept(WHILE)) {
            int beforeCondition = generator.getContentSize();
            condition(baseAndOffset);
            int afterCondition = generator.getContentSize();
            expect(DO);
            preposition(baseAndOffset);
            int atCondition = beforeCondition - (generator.getContentSize() + 5);
            generator.jmp(atCondition);
            generator.fixUp(afterCondition - 4, generator.getContentSize() - afterCondition);
        }
        if (accept(REPEAT)) {
            int beforePreposition = generator.getContentSize();
            preposition(baseAndOffset);
            while (accept(SEMICOLON)) {
                preposition(baseAndOffset);
            }
            expect(UNTIL);
            condition(baseAndOffset);
            generator.fixUp(generator.getContentSize() - 4, beforePreposition - generator.getContentSize());
        }
        if (accept(INC)) {
            expect(LPAREN);
            expect(IDENTIFIER);
            Optional<ID> var = idTable.findVariable(last.getValue(), baseAndOffset);
            if (var.isPresent()) {
                generator.movEax(1L);
                generator.changeEaxEbx();
                generator.movEaxEdiPlusOffset(var.get().getValue());
                generator.addEaxEbx();
                generator.movEdiPlusOffsetEax(var.get().getValue());
            } else {
                error = true;
                log.error(format("Variable \"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
            }
            expect(RPAREN);
        }
        if (accept(READLN)) {
            log.debug("READLN");
            expect(LPAREN);
            do {
                expect(IDENTIFIER);
                Optional<ID> variable = idTable.findVariable(last.getValue(), baseAndOffset);
                if (variable.isPresent()) {
                    generator.readln(variable.get().getValue());
                } else {
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
                    String unquoted = last.getValue().substring(1, last.getValue().length() - 1);
                    generator.write(unquoted);
                } else {
                    expression(baseAndOffset);
                    generator.write();
                }
            } while (accept(COMMA));
            expect(RPAREN);
        }

        if (accept(WRITELN)) {
            log.debug("WRITELN");
            if (accept(LPAREN)) {
                do {
                    if (accept(STRING)) {
                        String unquoted = last.getValue().substring(1, last.getValue().length() - 1);
                        generator.write(unquoted);
                    } else {
                        expression(baseAndOffset);
                        generator.write();
                    }
                } while (accept(COMMA));
                expect(RPAREN);
            }
            generator.writeNewLine();
        }
        if (accept(HALT)) {
            generator.jmp(generator.distanceTo(0x300));
        }
    }

    private void condition(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        if (accept(ODD)) {
            expression(baseAndOffset);
            generator.popEax();
            generator.testAl((byte) 0x01);
            generator.jpo((byte) 0x05);
            generator.jmp(0);
        } else {
            expression(baseAndOffset);
            expect(booleanOperators);
            SymbolType operation = last.getType();
            expression(baseAndOffset);
            generator.popEax();
            generator.popEbx();
            generator.cmpEaxEbx();
            switch (operation) {
                case EQ:
                    generator.addByte((byte) 0x74);
                    break;
                case NE:
                    generator.addByte((byte) 0x75);
                    break;
                case LT:
                    generator.addByte((byte) 0x7C);
                    break;
                case LE:
                    generator.addByte((byte) 0x7E);
                    break;
                case GT:
                    generator.addByte((byte) 0x7F);
                    break;
                case GE:
                    generator.addByte((byte) 0x7D);
            }
            generator.addByte((byte) 0x05);
            generator.jmp(0);
        }
    }

    private void expression(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        boolean minus = false;
        if (accept(plusMinus)) {
            minus = (last.getType() == MINUS);
        }
        term(baseAndOffset);
        if (minus) {
            generator.popEax();
            generator.negEax();
            generator.pushEax();
        }
        while (accept(plusMinus)) {
            minus = (last.getType() == MINUS);
            term(baseAndOffset);
            if (minus) {
                generator.popEax();
                generator.popEbx();
                generator.changeEaxEbx();
                generator.subEaxEbx();
                generator.pushEax();
            } else {
                generator.popEax();
                generator.popEbx();
                generator.addEaxEbx();
                generator.pushEax();
            }
        }
    }

    private void term(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        factor(baseAndOffset);
        while (accept(multiplyDivide)) {
            SymbolType operation = last.getType();
            factor(baseAndOffset);
            if (operation == TIMES) {
                generator.popEax();
                generator.popEbx();
                generator.mulEaxEbx();
                generator.pushEax();
            } else {
                generator.popEax();
                generator.popEbx();
                generator.changeEaxEbx();
                generator.cdq();
                generator.divEaxEbx();
                generator.pushEax();
            }
        }
    }

    private void factor(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        if (accept(IDENTIFIER)) {
            Optional<ID> variableOrConstantMaybe = idTable.findVariableOrConstant(last.getValue(), baseAndOffset);
            if (variableOrConstantMaybe.isPresent()) {
                ID variableOrConstant = variableOrConstantMaybe.get();
                if (variableOrConstant.getType() == IDType.VAR) {
                    generator.movEaxEdiPlusOffset(variableOrConstant.getValue());
                } else {
                    generator.movEax(variableOrConstant.getValue());
                }
                generator.pushEax();
            } else {
                log.error(format("\"{0}\" not declared at line {1}", last.getValue(), scanner.lineNumber()));
                error = true;
            }
            return;
        }
        if (accept(INTEGER)) {
            generator.movEax(last.getValue());
            generator.pushEax();
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
        expect(IDENTIFIER);
        id.setName(last.getValue());
        expect(SEMICOLON);
        id.setValue("" + generator.getContentSize());
        if (!idTable.addId(id, baseAndOffset)) {
            error = true;
            log.error(MessageFormat.format("{0} {1} already declared", id.getType(), id.getName()));
        }
        block(baseAndOffset.getBasePlusOffset());
        expect(SEMICOLON);
    }

    private void cons(BaseAndOffset baseAndOffset) {
        log.debug(scanner.toString());
        boolean minus;
        while (true) {
            ID id = new ID();
            id.setType(IDType.CONST);
            expect(IDENTIFIER);
            id.setName(last.getValue());
            expect(EQ);
            minus = accept(MINUS);
            expect(INTEGER);
            if (!error) {
                if (minus) {
                    id.setValue("-" + last.getValue());
                } else {
                    id.setValue(last.getValue());
                }
            }
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
            expect(IDENTIFIER);
            id.setName(last.getValue());
            if (!idTable.addVariable(id, baseAndOffset)) {
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
