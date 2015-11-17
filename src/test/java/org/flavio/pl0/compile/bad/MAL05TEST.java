package org.flavio.pl0.compile.bad;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MAL05TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/MAL-05.PL0");
        assertTrue(parser.isError());
    }

}