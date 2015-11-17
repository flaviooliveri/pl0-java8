package org.flavio.pl0.compile.good;

import org.flavio.pl0.compile.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BIEN04TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-04.PL0");
        assertFalse(parser.isError());
    }

}