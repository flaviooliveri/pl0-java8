package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BIEN08TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-08.PL0");
        assertFalse(parser.isError());
        assertEquals("BF 0A 85 04 08 E9 5C 00 00 00 E9 30 00 00 00 B9 A3 84 04 08 BA 01 00 00 00 E8 D2 FC FF FF E9 01 00 00 00 2C 8B 87 00 00 00 00 50 B8 01 00 00 00 5B 01 D8 89 87 00 00 00 00 E8 CC FF FF FF C3 8B 87 00 00 00 00 50 B8 0A 00 00 00 5B 39 C3 7C 05 E9 10 00 00 00 8B 87 00 00 00 00 E8 B0 FC FF FF E8 AA FF FF FF C3 B8 01 00 00 00 89 87 00 00 00 00 E8 94 FF FF FF B8 0A 00 00 00 E8 90 FC FF FF E8 7B FC FF FF E9 F6 FD FF FF 00 00 00 00", generator.getVariableAsHex());
    }

}