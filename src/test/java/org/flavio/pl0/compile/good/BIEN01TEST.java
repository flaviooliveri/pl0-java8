package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BIEN01TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-01.PL0");
        assertFalse(parser.isError());
        assertEquals("BF 8B 85 04 08 E9 48 00 00 00 8B 87 04 00 00 00 50 B8 00 00 00 00 5B 39 C3 7F 05 E9 31 00 00 00 8B 87 08 00 00 00 50 8B 87 00 00 00 00 5B F7 EB 89 87 08 00 00 00 8B 87 04 00 00 00 50 B8 01 00 00 00 5B 93 29 D8 89 87 04 00 00 00 E8 B9 FF FF FF C3 B9 E6 84 04 08 BA 06 00 00 00 E8 8F FC FF FF E9 06 00 00 00 42 41 53 45 3A 20 E8 1F FE FF FF 89 87 00 00 00 00 B9 0B 85 04 08 BA 0B 00 00 00 E8 6A FC FF FF E9 0B 00 00 00 45 58 50 4F 4E 45 4E 54 45 3A 20 E8 F5 FD FF FF 89 87 04 00 00 00 B8 01 00 00 00 89 87 08 00 00 00 E8 59 FF FF FF 8B 87 04 00 00 00 50 B8 00 00 00 00 5B 39 C3 7C 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 08 00 00 00 B9 66 85 04 08 BA 0B 00 00 00 E8 0F FC FF FF E9 0B 00 00 00 52 45 53 55 4C 54 41 44 4F 3A 20 8B 87 08 00 00 00 E8 14 FC FF FF E8 FF FB FF FF E8 FA FB FF FF E9 75 FD FF FF 00 00 00 00 00 00 00 00 00 00 00 00", generator.getVariableAsHex());
    }

}