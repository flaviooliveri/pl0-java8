package org.flavio.pl0.bad;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MAL08TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/MAL-08.PL0");
        assertTrue(parser.isError());
    }

}