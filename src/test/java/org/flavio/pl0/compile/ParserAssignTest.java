package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserAssignTest extends AbstractParserTest  {

    @Test
    public void testSimpleAssign() {
        setUp("VAR A; A:=4 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPlusMinusAssign() {
        setUp("VAR A; A:=4+4-3 .");
        assertFalse(parser.isError());
    }

}