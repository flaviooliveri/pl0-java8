package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SymbolTypeTest {

    @Test
    public void testMatch() throws Exception {

        assertFalse(SymbolType.ASSIGN.match(":"));
        assertTrue(SymbolType.COMMA.match(","));
        assertFalse(SymbolType.COMMA.match(",,"));
        assertTrue(SymbolType.COLON.match(":"));
        assertFalse(SymbolType.COLON.match(":="));
        assertTrue(SymbolType.PLUS.match("+"));
        assertFalse(SymbolType.PLUS.match("++"));
        assertTrue(SymbolType.MINUS.match("-"));
        assertFalse(SymbolType.MINUS.match("--"));
        assertTrue(SymbolType.TIMES.match("*"));
        assertFalse(SymbolType.TIMES.match("*/"));
        assertTrue(SymbolType.SLASH.match("/"));
        assertFalse(SymbolType.SLASH.match("//"));
        assertTrue(SymbolType.EQ.match("="));
        assertFalse(SymbolType.EQ.match(":="));
        assertTrue(SymbolType.LT.match("<"));
        assertFalse(SymbolType.LT.match("<="));
        assertTrue(SymbolType.GT.match(">"));
        assertFalse(SymbolType.GT.match(">="));
        assertTrue(SymbolType.NE.match("<>"));
        assertFalse(SymbolType.NE.match("<<"));
        assertTrue(SymbolType.LE.match("<="));
        assertFalse(SymbolType.LE.match("<"));
        assertTrue(SymbolType.GE.match(">="));
        assertFalse(SymbolType.GE.match(">"));
        assertTrue(SymbolType.LPAREN.match("("));
        assertFalse(SymbolType.LPAREN.match("(("));
        assertTrue(SymbolType.RPAREN.match(")"));
        assertFalse(SymbolType.RPAREN.match("))"));
        assertTrue(SymbolType.PERIOD.match("."));
        assertFalse(SymbolType.PERIOD.match(".."));
        assertTrue(SymbolType.SEMICOLON.match(";"));
        assertFalse(SymbolType.SEMICOLON.match(";;"));

        assertTrue(SymbolType.CALL.match("call"));
        assertFalse(SymbolType.CALL.match("calls"));
        assertTrue(SymbolType.CONST.match("const"));
        assertFalse(SymbolType.CONST.match("consts"));
        assertTrue(SymbolType.DO.match("do"));
        assertFalse(SymbolType.DO.match("doo"));
        assertTrue(SymbolType.END.match("end"));
        assertFalse(SymbolType.END.match("ends"));
        assertTrue(SymbolType.IF.match("if"));
        assertFalse(SymbolType.IF.match("aif"));
        assertTrue(SymbolType.ODD.match("odd"));
        assertFalse(SymbolType.ODD.match("odds"));
        assertTrue(SymbolType.PROCEDURE.match("procedure"));
        assertFalse(SymbolType.PROCEDURE.match("shomething"));
        assertTrue(SymbolType.THEN.match("then"));
        assertFalse(SymbolType.THEN.match("them"));
        assertTrue(SymbolType.VAR.match("var"));
        assertFalse(SymbolType.VAR.match("vars"));
        assertTrue(SymbolType.WHILE.match("while"));
        assertFalse(SymbolType.WHILE.match("whilee"));

        assertTrue(SymbolType.IDENTIFIER.match("anIndentifier"));

        assertTrue(SymbolType.INTEGER.match("12345"));
        assertFalse(SymbolType.INTEGER.match("12s345"));
        assertFalse(SymbolType.INTEGER.match("12.345"));
    }
}