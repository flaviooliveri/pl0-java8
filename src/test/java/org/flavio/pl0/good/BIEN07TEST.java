package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BIEN07TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-07.PL0");
        assertFalse(parser.isError());
        assertEquals("BF F5 85 04 08 E9 98 00 00 00 E8 F1 FC FF FF B8 0F 00 00 00 89 87 0C 00 00 00 8B 87 0C 00 00 00 50 B8 00 00 00 00 5B 39 C3 7F 05 E9 71 00 00 00 B8 00 00 00 00 89 87 10 00 00 00 8B 87 10 00 00 00 50 8B 87 0C 00 00 00 5B 39 C3 7C 05 E9 2F 00 00 00 B9 E6 84 04 08 BA 01 00 00 00 E8 8F FC FF FF E9 01 00 00 00 2A 8B 87 10 00 00 00 50 B8 01 00 00 00 5B 01 D8 89 87 10 00 00 00 E9 BA FF FF FF E8 7A FC FF FF 8B 87 0C 00 00 00 50 B8 01 00 00 00 5B 93 29 D8 89 87 0C 00 00 00 E9 79 FF FF FF C3 B8 01 00 00 00 89 87 00 00 00 00 8B 87 00 00 00 00 50 B8 14 00 00 00 5B 39 C3 7E 05 E9 3A 00 00 00 8B 87 00 00 00 00 E8 42 FC FF FF B9 62 85 04 08 BA 01 00 00 00 E8 13 FC FF FF E9 01 00 00 00 20 8B 87 00 00 00 00 50 B8 01 00 00 00 5B 01 D8 89 87 00 00 00 00 E9 B0 FF FF FF E8 08 FF FF FF B8 14 00 00 00 F7 D8 89 87 04 00 00 00 B8 00 00 00 00 89 87 08 00 00 00 8B 87 04 00 00 00 50 8B 87 08 00 00 00 5B 39 C3 7C 05 E9 3A 00 00 00 8B 87 04 00 00 00 E8 D4 FB FF FF B9 D0 85 04 08 BA 01 00 00 00 E8 A5 FB FF FF E9 01 00 00 00 20 8B 87 04 00 00 00 50 B8 01 00 00 00 5B 01 D8 89 87 04 00 00 00 E9 AF FF FF FF E8 90 FB FF FF E9 0B FD FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00", generator.getVariableAsHex());
    }

}