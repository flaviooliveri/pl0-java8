package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class AddProcedureTest extends AbstractParserTest  {

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleDoubleDeclaration() {
        setUp("PROCEDURE A;;PROCEDURE A;; .");
    }

    @Test
    public void testDoubleDeclarationInsideBlock() {
        setUp("PROCEDURE A; PROCEDURE A;;; .");
        assertFalse(parser.isError());
    }

}