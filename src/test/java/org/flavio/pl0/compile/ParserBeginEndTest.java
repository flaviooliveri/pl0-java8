package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParserBeginEndTest extends AbstractParserTest  {

    @Test
    public void testSimpleBeginEnd() {
        log.debug("Test: SimpleBeginEnd");
        setUp("VAR A; BEGIN A:=4*4 END.");
        assertFalse(parser.isError());
    }

    @Test
    public void testEmptyBeginEnd() {
        log.debug("Test: EmptyBeginEnd");
        setUp("BEGIN END.");
        assertFalse(parser.isError());
    }

    @Test
    public void testBeginEndWithEmptyPrepositionAtTheEnd() {
        log.debug("Test: BeginEndWithEmptyPrepositionAtTheEnd");
        setUp("VAR A;BEGIN A:=4*4; END.");
        assertFalse(parser.isError());
    }

    @Test
    public void testTwoBeginEnd() {
        log.debug("Test: TwoBeginEnd");
        setUp("VAR A; BEGIN BEGIN A:=4*4 END END.");
        assertFalse(parser.isError());
    }

    @Test
    public void testBeginEndErrorBEGINBEGIN() {
        log.debug("Test: BeginEndErrorBEGINBEGIN");
        setUp("BEGIN VAR A; BEGIN A:=4*4; END.");
        assertTrue(parser.isError());
    }

}