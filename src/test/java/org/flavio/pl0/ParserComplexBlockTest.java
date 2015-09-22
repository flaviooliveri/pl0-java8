package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserComplexBlockTest extends AbstractParserTest {

    @Test
    public void testBlock() {
        setUp("CONST a=4;VAR x;.");
        assertFalse(parser.isError());
    }


    @Test
    public void testBadBlock() {
        setUp("VAR x; CONST a=4;.");
        assertTrue(parser.isError());
    }

}