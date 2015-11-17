package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BIEN09TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-09.PL0");
        assertFalse(parser.isError());
        assertEquals("BF 8A 84 04 08 E9 76 FE FF FF", generator.getVariableAsHex());
    }

}