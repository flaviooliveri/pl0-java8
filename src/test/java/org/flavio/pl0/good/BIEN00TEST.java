package org.flavio.pl0.good;

import org.flavio.pl0.AbstractFileParserTest;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BIEN00TEST extends AbstractFileParserTest {

    @Test
    public void test() {
        setUp("/BIEN-00.PL0");
        assertFalse(parser.isError());
    }

}