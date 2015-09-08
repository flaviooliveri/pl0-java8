package org.flavio.pl0;

public enum SymbolType {

    ASSIGN(":=",1),	COMMA(",",1), COLON(":",1),
    PLUS("+",1), MINUS("-",1), TIMES("*",1),
    SLASH("/",1), EQ("=",1), LT("<",1),
    GT(">",1), NE("<>",1), LE("<=",1),
    GE(">=",1), LPAREN("(",1), RPAREN(")",1),
    PERIOD(".",1), SEMICOLON(";",1),

    CALL("call",2), CONST("const",2), DO("do",2),
    END("end",2), IF("if",2), ODD("odd",2), PROCEDURE("procedure",2),
    THEN("then",2), VAR("var",2), WHILE("while",2), BEGIN("begin",2),

    IDENTIFIER("[a-zA-Z]*",3, true),
    INTEGER("[0-9]*",3, true),

    ERROR(".*",4, true);

    private String pattern;
    private int precedence;
    private boolean regex;

    SymbolType(String pattern, int precedence, boolean regex) {
        this.pattern = pattern;
        this.precedence = precedence;
        this.regex = regex;
    }

    SymbolType(String pattern, int precedence) {
        this.pattern = pattern;
        this.precedence = precedence;
        this.regex = false;
    }

    boolean match(String token) {
        return (regex && token.matches(pattern)) || token.equalsIgnoreCase(pattern);
    }

    public int getPrecedence() {
        return precedence;
    }



}
