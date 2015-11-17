package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddVarTest extends AbstractParserTest  {

    @Test
    public void testSimpleDoubleDeclaration() {
        setUp("VAR A,A; .");
        assertTrue(parser.isError());
    }

    @Test
    public void testDoubleDeclarationInsideBlock() {
        setUp("VAR A;PROCEDURE SARASA; var A;; .");
        assertFalse(parser.isError());
    }

    @Test
    public void testDoubleDeclarationInsideBlock2() {
        setUp("VAR A,B,C;PROCEDURE SARASA; VAR A,C;; .");
        assertFalse(parser.isError());
    }

}