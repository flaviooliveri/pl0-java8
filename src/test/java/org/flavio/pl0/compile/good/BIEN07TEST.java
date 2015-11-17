package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BIEN07TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-07.PL0");
        assertFalse(parser.isError());
    }

}