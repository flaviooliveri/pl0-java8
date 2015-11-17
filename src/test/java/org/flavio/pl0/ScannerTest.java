package org.flavio.pl0;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ScannerTest {

    @Test
    public void testAssign() {

        StringReader reader = new StringReader("CONST A:=2;");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.CONST, "CONST");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER, "A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.ASSIGN, ":=");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.INTEGER, "2");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.SEMICOLON, ";");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
        
    }


    @Test
    public void testErrorWithDouble() {

        StringReader reader = new StringReader("CONST A=2.4;");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.CONST,"CONST");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.EQ,"=");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.ERROR,"2.4");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

    }

    @Test
    public void testNotEquals() {

        StringReader reader = new StringReader("A<>B");
        Scanner scanner = getScanner(reader);
        
        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.NE,"<>");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testLE() {

        StringReader reader = new StringReader("A<=B");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.LE,"<=");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testLT() {

        StringReader reader = new StringReader("A<B");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.LT,"<");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testGT() {

        StringReader reader = new StringReader("A>B");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.GT,">");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testGE() {

        StringReader reader = new StringReader("A>=B");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.GE,">=");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testSlash() {

        StringReader reader = new StringReader("A/B");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.IDENTIFIER,"A");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.SLASH,"/");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.IDENTIFIER,"B");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());
    }

    @Test
    public void testWrite() {

        StringReader reader = new StringReader("WRITE('STRING')");
        Scanner scanner = getScanner(reader);

        Symbol expected = new Symbol(SymbolType.WRITE,"WRITE");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.LPAREN,"(");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

        expected = new Symbol(SymbolType.STRING,"'STRING'");
        scanner.next();
        assertEquals(expected, scanner.getSymbol());

    }


    private Scanner getScanner(StringReader reader) {
        return new Scanner(reader, new TokenEvaluator());
    }

}