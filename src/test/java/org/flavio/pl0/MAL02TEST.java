package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MAL02TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/MAL-02.PL0");
        assertTrue(parser.isError());
    }

}