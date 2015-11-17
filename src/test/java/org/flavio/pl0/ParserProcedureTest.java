package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserProcedureTest extends AbstractParserTest {

    @Test
    public void testPROCEDURE() {
        setUp("PROCEDURE doSomething;CONST a=4;;.");
        assertFalse(parser.isError());
    }


    @Test
    public void testBadPROCEDURE() {
        setUp("PROCEDURE doSomething CONST a=4;;.");
        assertTrue(parser.isError());
    }

}