package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BIEN08TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-08.PL0");
        assertFalse(parser.isError());
    }

}