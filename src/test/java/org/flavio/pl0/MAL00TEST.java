package org.flavio.pl0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MAL00TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/MAL-00.PL0");
        assertTrue(parser.isError());
    }

}