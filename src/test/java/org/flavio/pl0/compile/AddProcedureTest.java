package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddProcedureTest extends AbstractParserTest  {

    @Test
    public void testSimpleDoubleDeclaration() {
        setUp("PROCEDURE A;;PROCEDURE A;; .");
        assertTrue(parser.isError());
    }

    @Test
    public void testDoubleDeclarationInsideBlock() {
        setUp("PROCEDURE A; PROCEDURE A;;; .");
        assertFalse(parser.isError());
    }

}