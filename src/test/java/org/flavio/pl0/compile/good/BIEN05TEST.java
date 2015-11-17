package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BIEN05TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-05.PL0");
        assertFalse(parser.isError());
    }

}