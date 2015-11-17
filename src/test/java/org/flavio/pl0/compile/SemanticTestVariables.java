package org.flavio.pl0.compile;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SemanticTestVariables extends AbstractParserTest {

    @Test
    public void testVariableNotDeclared() {
        setUp("A:=1 .");
        assertTrue(parser.isError());
    }

    @Test
    public void testVariableNotDeclaredWithProcedure() {
        String source = "VAR x;\n" +
                "PROCEDURE PEPE;\n " +
                "BEGIN\n" +
                    "y:=4;\n" +
                "END;\n" +
                ".";
        setUp(source);
        assertTrue(parser.isError());
    }

    @Test
    public void testVariableDeclaredInOtherScope() {
        String source = "VAR x; " +
                        "PROCEDURE PEPE; " +
                        "BEGIN " +
                            "x:=4; " +
                        "END;" +
                        ".";
        setUp(source);
        assertFalse(parser.isError());
    }

    @Test
    public void testProcedure() {
        String source = "VAR x;\n" +
                        "PROCEDURE PEPE;\n " +
                        "BEGIN\n" +
                            "x:=4;\n" +
                        "END;\n" +
                        "CALL PEPE\n" +
                        ".";
        setUp(source);
        assertFalse(parser.isError());
    }

    @Test
    public void testAssign() {
        String source = "VAR x,y;\n" +
                "BEGIN\n" +
                    "x := 4;\n" +
                    "y := x + 10;\n" +
                "END\n" +
                ".";
        setUp(source);
        assertFalse(parser.isError());
    }

    @Test
    public void testAssignWithConstant() {
        String source = "CONST x = 4;\n" +
                "VAR y;\n" +
                "BEGIN\n" +
                    "y := x + 10;\n" +
                "END\n" +
                ".";
        setUp(source);
        assertFalse(parser.isError());
    }

    @Test
    public void testAssignError() {
        String source = "CONST x = 4;\n" +
                "VAR y;\n" +
                "BEGIN\n" +
                    "y := x + w;\n" +
                "END\n" +
                ".";
        setUp(source);
        assertTrue(parser.isError());
    }

}