package org.flavio.pl0;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserAssignTest extends AbstractParserTest  {

    @Test
    public void testSimpleAssign() {
        setUp("A:=4 .");
        assertFalse(parser.isError());
    }

    @Test
    public void testPlusMinusAssign() {
        setUp("A:=4+4-3 .");
        assertFalse(parser.isError());
    }

}