package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserExpressionTest extends AbstractParserTest  {

    @Test
    public void testSimpleFactor() {
        setUp("A:=4*4 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPlusFactor() {
        setUp("A:=4+4*3 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPar() {
        setUp("A:=4+(4*3) .");
        assertFalse(parser.isError());
    }

    @Test
    public void testMinusParen() {
        setUp("A:=4+(-4*3) .");
        assertFalse(parser.isError());
    }

    @Test
    public void testInvalidParen() {
        setUp("A:=4+(/4*3) .");
        assertTrue(parser.isError());
    }

    @Test
    public void testInvalidParen2() {
        setUp("A:=4+()+1 .");
        assertTrue(parser.isError());
    }

    @Test
    public void testValidParen() {
        setUp("A:=4+(5)+4*3 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testValidParen2() {
        setUp("A:=4+(+5)+4*(-3) .");
        assertFalse(parser.isError());
    }

}