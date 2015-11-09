package org.flavio.pl0;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class TokenEvaluatorTest {


    private TokenEvaluator evaluator;

    @Before
    public void before() {
        evaluator = new TokenEvaluator();
    }
    
    @Test
    public void testFindSymbolType() throws Exception {
        assertTrue(SymbolType.CONST == evaluator.findSymbolType("const"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("const"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("<"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(">"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("<="));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("var"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("end"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType("begin"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(","));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(":"));
        assertFalse(SymbolType.IDENTIFIER == evaluator.findSymbolType(";"));
        assertTrue(SymbolType.IDENTIFIER == evaluator.findSymbolType("co444"));
        assertTrue(SymbolType.ERROR == evaluator.findSymbolType("=>"));
        assertTrue(SymbolType.ERROR == evaluator.findSymbolType("44a"));
        assertTrue(SymbolType.ERROR == evaluator.findSymbolType("a$"));
        assertTrue(SymbolType.ERROR == evaluator.findSymbolType("!a"));
    }

    @Test
    public void testEvaluate() throws Exception {
        assertEquals(new Symbol(SymbolType.CONST,"CONST"),evaluator.evaluate("const"));
        assertEquals(new Symbol(SymbolType.IDENTIFIER,"anIdentifier"),evaluator.evaluate("anIdentifier"));
        assertEquals(new Symbol(SymbolType.COMMA,","),evaluator.evaluate(","));
        assertEquals(new Symbol(SymbolType.INTEGER,"152"),evaluator.evaluate("152"));
    }}