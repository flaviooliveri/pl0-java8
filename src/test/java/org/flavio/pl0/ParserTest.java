package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserTest extends AbstractParserTest {

    @Test
    public void testNotPeriod() {
        setUp("A");
        assertTrue(parser.isError());
    }

    @Test
    public void testBlockNotPeriod() {
        setUp("CONST A=2;");
        assertTrue(parser.isError());
    }

    @Test
    public void testBlockIncomplete() {
        setUp("CONST A=2");
        assertTrue(parser.isError());
    }

    @Test
    public void testPeriod() {
        setUp(".");
        assertFalse(parser.isError());
    }

    @Test
    public void testCONST() {
        setUp("CONST A=3;.");
        assertFalse(parser.isError());
    }

    @Test
    public void testServeralCONST() {
        setUp("CONST A=3,B=2,FLAVIO=5;.");
        assertFalse(parser.isError());
    }

    @Test
    public void testBadCONST() {
        setUp("CONST A:=3.");
        assertTrue(parser.isError());
    }

    @Test
    public void testBadCONST2() {
        setUp("CONST A=something.");
        assertTrue(parser.isError());
    }

    @Test
    public void testBadCONST3() {
        setUp("CONST A=3;CONST=B=2.");
        assertTrue(parser.isError());
    }

    @Test
    public void testServeralVAR() {
        setUp("VAR A,FLAVIO;.");
        assertFalse(parser.isError());
    }

    @Test
    public void testVAR() {
        setUp("VAR A;.");
        assertFalse(parser.isError());
    }

    @Test
    public void testBadVAR() {
        setUp("VAR A=something.");
        assertTrue(parser.isError());
    }

}