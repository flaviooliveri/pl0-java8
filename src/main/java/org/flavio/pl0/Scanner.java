package org.flavio.pl0;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Scanner {

    private StreamTokenizer tokenizer;
    private TokenEvaluator evaluator;
    private Symbol symbol;

    public Scanner(Reader reader) {
        tokenizer = new StreamTokenizer(reader);
        tokenizer.lowerCaseMode(true);
        char dot = '.';
        char slash = '/';
        char minus = '-';
        tokenizer.ordinaryChar(dot);
        tokenizer.ordinaryChar(slash);
        tokenizer.ordinaryChar(minus);
        evaluator = new TokenEvaluator();
    }

    public int lineNumber() {
        return tokenizer.lineno();
    }

    public boolean hasNext() {
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tokenizer.pushBack();
        return !(tokenizer.ttype == StreamTokenizer.TT_EOF);
    }

    public Symbol next() {
        int token;
        try {
            token = tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (token == StreamTokenizer.TT_WORD) {
            symbol = evaluator.evaluate(tokenizer.sval);
        } else if (token == StreamTokenizer.TT_NUMBER) {
            processNumber();
        } else {
            String tokenStr = Character.toString((char) token);
            SymbolType symbolType = evaluator.findSymbolType(tokenStr);
            if (symbolType == SymbolType.COLON) {
                processColon();
            } else if (symbolType == SymbolType.LT) {
                processLT();
            } else if (symbolType == SymbolType.GT) {
                processGT();
            } else {
                symbol = evaluator.evaluate(tokenStr);
            }
        }
        return symbol;
    }

    private void processColon() {
        String tokenStr = getTokenAsString();
        SymbolType symbolType = evaluator.findSymbolType(tokenStr);
        if (symbolType == SymbolType.EQ) {
            symbol = evaluator.createSymbol(SymbolType.ASSIGN);
        } else {
            symbol = evaluator.createError(SymbolType.COLON.getPattern() + tokenStr);
            tokenizer.pushBack();
        }
    }

    private void processLT() {
        String tokenStr = getTokenAsString();
        SymbolType symbolType = evaluator.findSymbolType(tokenStr);
        if (symbolType == SymbolType.GT) {
            symbol = evaluator.createSymbol(SymbolType.NE);
        } else if (symbolType == SymbolType.EQ) {
            symbol = evaluator.createSymbol(SymbolType.LE);
        } else {
            symbol = evaluator.createSymbol(SymbolType.LT);
            tokenizer.pushBack();
        }
    }

    private void processGT() {
        String tokenStr = getTokenAsString();
        SymbolType symbolType = evaluator.findSymbolType(tokenStr);
        if (symbolType == SymbolType.EQ) {
            symbol = evaluator.createSymbol(SymbolType.GE);
        } else {
            symbol = evaluator.createSymbol(SymbolType.GT);
            tokenizer.pushBack();
        }
    }

    private void processNumber() {
        Double value = tokenizer.nval;
        if (value.equals(Double.valueOf(value.longValue()))) {
            symbol = evaluator.evaluate(String.valueOf(value.longValue()));
        } else {
            symbol = evaluator.createError(String.valueOf(value));
            tokenizer.pushBack();
        }
    }

    private String getTokenAsString() {
        String tokenStr;
        try {
            tokenStr =  Character.toString((char) tokenizer.nextToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tokenStr;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
