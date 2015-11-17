package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BIEN06TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-06.PL0");
        assertFalse(parser.isError());
        assertEquals("BF D4 90 04 08 E9 2F 00 00 00 B9 9E 84 04 08 BA 15 00 00 00 E8 D7 FC FF FF E9 15 00 00 00 56 41 4C 4F 52 20 46 55 45 52 41 20 44 45 20 52 41 4E 47 4F 21 E8 C8 FC FF FF C3 B9 CD 84 04 08 BA 2B 00 00 00 E8 A8 FC FF FF E9 2B 00 00 00 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A E8 83 FC FF FF B9 11 85 04 08 BA 2B 00 00 00 E8 64 FC FF FF E9 2B 00 00 00 56 55 45 4C 54 4F 20 50 41 52 41 20 49 4D 50 4F 52 54 45 53 20 50 41 47 41 44 4F 53 20 43 4F 4E 20 55 4E 20 42 49 4C 4C 45 54 45 E8 3F FC FF FF B9 55 85 04 08 BA 2B 00 00 00 E8 20 FC FF FF E9 2B 00 00 00 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A 2A E8 FB FB FF FF E8 F6 FB FF FF B8 01 00 00 00 F7 D8 89 87 1C 00 00 00 8B 87 1C 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 BA 02 00 00 B9 C1 85 04 08 BA 21 00 00 00 E8 B4 FB FF FF E9 21 00 00 00 49 4D 50 4F 52 54 45 20 28 6D 69 6E 20 24 30 2E 30 31 20 79 20 6D 61 78 20 24 31 30 30 2E 30 30 29 E8 99 FB FF FF B8 01 00 00 00 F7 D8 89 87 20 00 00 00 8B 87 20 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 9A 00 00 00 B9 1E 86 04 08 BA 0A 00 00 00 E8 57 FB FF FF E9 0A 00 00 00 43 45 4E 54 41 56 4F 53 3A 20 E8 E3 FC FF FF 89 87 10 00 00 00 B8 00 00 00 00 89 87 20 00 00 00 8B 87 10 00 00 00 50 B8 00 00 00 00 5B 39 C3 7C 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 20 00 00 00 8B 87 10 00 00 00 50 B8 63 00 00 00 5B 39 C3 7F 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 20 00 00 00 8B 87 20 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 05 00 00 00 E8 EB FD FF FF E9 50 FF FF FF B8 01 00 00 00 F7 D8 89 87 24 00 00 00 8B 87 24 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 97 00 00 00 B9 DB 86 04 08 BA 07 00 00 00 E8 9A FA FF FF E9 07 00 00 00 50 45 53 4F 53 3A 20 E8 29 FC FF FF 89 87 0C 00 00 00 B8 00 00 00 00 89 87 24 00 00 00 8B 87 0C 00 00 00 50 B8 00 00 00 00 5B 39 C3 7C 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 24 00 00 00 8B 87 0C 00 00 00 50 B8 64 00 00 00 5B 39 C3 7F 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 24 00 00 00 8B 87 24 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 05 00 00 00 E8 31 FD FF FF E9 53 FF FF FF B9 72 87 04 08 BA 0A 00 00 00 E8 03 FA FF FF E9 0A 00 00 00 49 4D 50 4F 52 54 45 3A 20 24 8B 87 0C 00 00 00 E8 09 FA FF FF B9 9B 87 04 08 BA 01 00 00 00 E8 DA F9 FF FF E9 01 00 00 00 2E 8B 87 10 00 00 00 50 B8 0A 00 00 00 5B 39 C3 7C 05 E9 15 00 00 00 B9 C6 87 04 08 BA 01 00 00 00 E8 AF F9 FF FF E9 01 00 00 00 30 8B 87 10 00 00 00 E8 BE F9 FF FF E8 A9 F9 FF FF 8B 87 0C 00 00 00 50 B8 64 00 00 00 5B F7 EB 50 8B 87 10 00 00 00 5B 01 D8 89 87 00 00 00 00 B8 00 00 00 00 89 87 1C 00 00 00 8B 87 00 00 00 00 50 B8 01 00 00 00 5B 39 C3 7C 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 1C 00 00 00 8B 87 00 00 00 00 50 B8 10 27 00 00 5B 39 C3 7F 05 E9 0D 00 00 00 B8 01 00 00 00 F7 D8 89 87 1C 00 00 00 8B 87 1C 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 05 00 00 00 E8 28 FC FF FF E9 30 FD FF FF B8 01 00 00 00 F7 D8 89 87 2C 00 00 00 8B 87 2C 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 09 02 00 00 B8 01 00 00 00 F7 D8 89 87 28 00 00 00 8B 87 28 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 58 01 00 00 B9 C1 88 04 08 BA 1E 00 00 00 E8 B4 F8 FF FF E9 1E 00 00 00 42 49 4C 4C 45 54 45 20 28 6D 69 6E 20 24 32 20 79 20 6D 61 78 20 24 31 30 30 29 3A 20 24 E8 2C FA FF FF 89 87 04 00 00 00 B8 01 00 00 00 F7 D8 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 02 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 05 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 0A 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 14 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 32 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 04 00 00 00 50 B8 64 00 00 00 5B 39 C3 74 05 E9 0B 00 00 00 B8 00 00 00 00 89 87 28 00 00 00 8B 87 28 00 00 00 50 B8 00 00 00 00 5B 39 C3 75 05 E9 2D 00 00 00 B9 E7 89 04 08 BA 14 00 00 00 E8 8E F7 FF FF E9 14 00 00 00 42 49 4C 4C 45 54 45 20 49 4E 45 58 49 53 54 45 4E 54 45 21 E8 80 F7 FF FF E9 92 FE FF FF 8B 87 04 00 00 00 50 B8 64 00 00 00 5B F7 EB 50 8B 87 00 00 00 00 5B 93 29 D8 89 87 08 00 00 00 B8 00 00 00 00 89 87 2C 00 00 00 8B 87 08 00 00 00 50 B8 00 00 00 00 5B 39 C3 7C 05 E9 48 00 00 00 B8 01 00 00 00 F7 D8 89 87 2C 00 00 00 B9 67 8A 04 08 BA 22 00 00 00 E8 0E F7 FF FF E9 22 00 00 00 42 49 4C 4C 45 54 45 20 49 4E 53 55 46 49 43 49 45 4E 54 45 20 50 41 52 41 20 45 4C 20 50 41 47 4F 21 E8 F2 F6 FF FF E9 E1 FD FF FF 8B 87 08 00 00 00 50 B8 64 00 00 00 5B 93 99 F7 FB 89 87 14 00 00 00 8B 87 08 00 00 00 50 8B 87 14 00 00 00 50 B8 64 00 00 00 5B F7 EB 5B 93 29 D8 89 87 18 00 00 00 B9 DE 8A 04 08 BA 0C 00 00 00 E8 97 F6 FF FF E9 0C 00 00 00 53 75 20 76 75 65 6C 74 6F 3A 20 24 8B 87 14 00 00 00 E8 9B F6 FF FF B9 09 8B 04 08 BA 01 00 00 00 E8 6C F6 FF FF E9 01 00 00 00 2E 8B 87 18 00 00 00 50 B8 0A 00 00 00 5B 39 C3 7C 05 E9 15 00 00 00 B9 34 8B 04 08 BA 01 00 00 00 E8 41 F6 FF FF E9 01 00 00 00 30 8B 87 18 00 00 00 E8 50 F6 FF FF E8 3B F6 FF FF E8 36 F6 FF FF 8B 87 08 00 00 00 50 B8 88 13 00 00 5B 39 C3 7D 05 E9 3F 00 00 00 B9 74 8B 04 08 BA 10 00 00 00 E8 01 F6 FF FF E9 10 00 00 00 31 20 62 69 6C 6C 65 74 65 20 64 65 20 24 35 30 E8 F7 F5 FF FF 8B 87 08 00 00 00 50 B8 88 13 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 D0 07 00 00 5B 39 C3 7D 05 E9 C0 00 00 00 8B 87 08 00 00 00 50 B8 D0 07 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 74 05 E9 29 00 00 00 B9 EA 8B 04 08 BA 10 00 00 00 E8 8B F5 FF FF E9 10 00 00 00 31 20 62 69 6C 6C 65 74 65 20 64 65 20 24 32 30 E8 81 F5 FF FF 8B 87 08 00 00 00 50 B8 D0 07 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 75 05 E9 2A 00 00 00 B9 34 8C 04 08 BA 11 00 00 00 E8 41 F5 FF FF E9 11 00 00 00 32 20 62 69 6C 6C 65 74 65 73 20 64 65 20 24 32 30 E8 36 F5 FF FF 8B 87 08 00 00 00 50 8B 87 08 00 00 00 50 B8 D0 07 00 00 5B 93 99 F7 FB 50 B8 D0 07 00 00 5B F7 EB 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 E8 03 00 00 5B 39 C3 7D 05 E9 3F 00 00 00 B9 9F 8C 04 08 BA 10 00 00 00 E8 D6 F4 FF FF E9 10 00 00 00 31 20 62 69 6C 6C 65 74 65 20 64 65 20 24 31 30 E8 CC F4 FF FF 8B 87 08 00 00 00 50 B8 E8 03 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 F4 01 00 00 5B 39 C3 7D 05 E9 3E 00 00 00 B9 F4 8C 04 08 BA 0F 00 00 00 E8 81 F4 FF FF E9 0F 00 00 00 31 20 62 69 6C 6C 65 74 65 20 64 65 20 24 35 E8 78 F4 FF FF 8B 87 08 00 00 00 50 B8 F4 01 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 C8 00 00 00 5B 39 C3 7D 05 E9 BE 00 00 00 8B 87 08 00 00 00 50 B8 C8 00 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 74 05 E9 28 00 00 00 B9 69 8D 04 08 BA 0F 00 00 00 E8 0C F4 FF FF E9 0F 00 00 00 31 20 62 69 6C 6C 65 74 65 20 64 65 20 24 32 E8 03 F4 FF FF 8B 87 08 00 00 00 50 B8 C8 00 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 75 05 E9 29 00 00 00 B9 B2 8D 04 08 BA 10 00 00 00 E8 C3 F3 FF FF E9 10 00 00 00 32 20 62 69 6C 6C 65 74 65 73 20 64 65 20 24 32 E8 B9 F3 FF FF 8B 87 08 00 00 00 50 8B 87 08 00 00 00 50 B8 C8 00 00 00 5B 93 99 F7 FB 50 B8 C8 00 00 00 5B F7 EB 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 64 00 00 00 5B 39 C3 7D 05 E9 3D 00 00 00 B9 1C 8E 04 08 BA 0E 00 00 00 E8 59 F3 FF FF E9 0E 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 24 31 E8 51 F3 FF FF 8B 87 08 00 00 00 50 B8 64 00 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 32 00 00 00 5B 39 C3 7D 05 E9 46 00 00 00 B9 6F 8E 04 08 BA 17 00 00 00 E8 06 F3 FF FF E9 17 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 35 30 20 63 65 6E 74 61 76 6F 73 E8 F5 F2 FF FF 8B 87 08 00 00 00 50 B8 32 00 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 19 00 00 00 5B 39 C3 7D 05 E9 46 00 00 00 B9 CB 8E 04 08 BA 17 00 00 00 E8 AA F2 FF FF E9 17 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 32 35 20 63 65 6E 74 61 76 6F 73 E8 99 F2 FF FF 8B 87 08 00 00 00 50 B8 19 00 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 0A 00 00 00 5B 39 C3 7D 05 E9 CE 00 00 00 8B 87 08 00 00 00 50 B8 0A 00 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 74 05 E9 30 00 00 00 B9 48 8F 04 08 BA 17 00 00 00 E8 2D F2 FF FF E9 17 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 31 30 20 63 65 6E 74 61 76 6F 73 E8 1C F2 FF FF 8B 87 08 00 00 00 50 B8 0A 00 00 00 5B 93 99 F7 FB 50 B8 01 00 00 00 5B 39 C3 75 05 E9 31 00 00 00 B9 99 8F 04 08 BA 18 00 00 00 E8 DC F1 FF FF E9 18 00 00 00 32 20 6D 6F 6E 65 64 61 73 20 64 65 20 31 30 20 63 65 6E 74 61 76 6F 73 E8 CA F1 FF FF 8B 87 08 00 00 00 50 8B 87 08 00 00 00 50 B8 0A 00 00 00 5B 93 99 F7 FB 50 B8 0A 00 00 00 5B F7 EB 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 05 00 00 00 5B 39 C3 7D 05 E9 45 00 00 00 B9 0B 90 04 08 BA 16 00 00 00 E8 6A F1 FF FF E9 16 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 35 20 63 65 6E 74 61 76 6F 73 E8 5A F1 FF FF 8B 87 08 00 00 00 50 B8 05 00 00 00 5B 93 29 D8 89 87 08 00 00 00 8B 87 08 00 00 00 50 B8 01 00 00 00 5B 39 C3 7F 05 E9 39 00 00 00 8B 87 08 00 00 00 E8 33 F1 FF FF B9 71 90 04 08 BA 15 00 00 00 E8 04 F1 FF FF E9 15 00 00 00 20 6D 6F 6E 65 64 61 73 20 64 65 20 31 20 63 65 6E 74 61 76 6F E8 F5 F0 FF FF 8B 87 08 00 00 00 50 B8 01 00 00 00 5B 39 C3 74 05 E9 2E 00 00 00 B9 B5 90 04 08 BA 15 00 00 00 E8 C0 F0 FF FF E9 15 00 00 00 31 20 6D 6F 6E 65 64 61 20 64 65 20 31 20 63 65 6E 74 61 76 6F E8 B1 F0 FF FF E9 2C F2 FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00", generator.getVariableAsHex());
    }

}