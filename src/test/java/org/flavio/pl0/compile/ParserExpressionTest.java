package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserExpressionTest extends AbstractParserTest  {

    @Test
    public void testSimpleFactor() {
        setUp("VAR A; A:=4*4 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPlusFactor() {
        setUp("VAR A; A:=4+4*3 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPar() {
        setUp("VAR A; A:=4+(4*3) .");
        assertFalse(parser.isError());
    }

    @Test
    public void testMinusParen() {
        setUp("VAR A; A:=4+(-4*3) .");
        assertFalse(parser.isError());
    }

    @Test
    public void testInvalidParen() {
        setUp("VAR A; A:=4+(/4*3) .");
        assertTrue(parser.isError());
    }

    @Test
    public void testInvalidParen2() {
        setUp("VAR A; A:=4+()+1 .");
        assertTrue(parser.isError());
    }

    @Test
    public void testValidParen() {
        setUp("VAR A; A:=4+(5)+4*3 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testValidParen2() {
        setUp("VAR A; A:=4+(+5)+4*(-3) .");
        assertFalse(parser.isError());
    }

}