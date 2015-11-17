package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BIEN00TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-00.PL0");
        assertFalse(parser.isError());
        assertEquals("BF 02 85 04 08 E9 17 00 00 00 E9 0C 00 00 00 B8 02 00 00 00 89 87 00 00 00 00 C3 E8 EF FF FF FF C3 B9 B5 84 04 08 BA 04 00 00 00 E8 C0 FC FF FF E9 04 00 00 00 4E 55 4D 3D E8 52 FE FF FF 89 87 04 00 00 00 E8 C1 FF FF FF B9 DD 84 04 08 BA 06 00 00 00 E8 98 FC FF FF E9 06 00 00 00 4E 55 4D 2A 32 3D 8B 87 04 00 00 00 50 8B 87 00 00 00 00 5B F7 EB E8 98 FC FF FF E8 83 FC FF FF E9 FE FD FF FF 00 00 00 00 00 00 00 00", generator.getVariableAsHex());
    }

}