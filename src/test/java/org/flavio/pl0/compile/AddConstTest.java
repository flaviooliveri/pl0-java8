package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddConstTest extends AbstractParserTest  {

    @Test
    public void testSimpleDoubleDeclaration() {
        setUp("CONST A=4,A=3; .");
        assertTrue(parser.isError());
    }

    @Test
    public void testDoubleDeclarationInsideBlock() {
        setUp("CONST A=4;PROCEDURE SARASA; CONST A=4;; .");
        assertFalse(parser.isError());
    }

    @Test
    public void testDoubleDeclarationInsideBlock2() {
        setUp("CONST A=4,B=2,C=3;PROCEDURE SARASA; CONST A=4,C=3;; .");
        assertFalse(parser.isError());
    }

}