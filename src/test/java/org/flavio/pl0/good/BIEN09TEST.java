package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BIEN09TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-09.PL0");
        assertFalse(parser.isError());
    }

}