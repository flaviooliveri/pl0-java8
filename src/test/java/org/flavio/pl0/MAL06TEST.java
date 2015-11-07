package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MAL06TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/MAL-06.PL0");
        assertTrue(parser.isError());
    }

}