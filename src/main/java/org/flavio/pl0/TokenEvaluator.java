package org.flavio.pl0;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class TokenEvaluator {

    private List<SymbolType> symbols;

    public TokenEvaluator() {
        this.symbols =  Arrays.asList(SymbolType.values());
    }

    public SymbolType findSymbolType(String token) {
        BinaryOperator<SymbolType> minPrecedence = (o1, o2) -> o1.getPrecedence() < o2.getPrecedence() ? o1 : o2;
        Optional<SymbolType> result = symbols.stream().filter(symbolType -> symbolType.match(token)).reduce(minPrecedence);
        return result.get();
    }

    public Symbol evaluate(String token) {
        SymbolType type = findSymbolType(token);
        if (type.isRegex()) {
            return new Symbol(type,token);
        }
        return new Symbol(type,type.getPattern().toUpperCase());
    }

    public Symbol createError(String token) {
        return new Symbol(SymbolType.ERROR, token);
    }

    public Symbol createSymbol(SymbolType symbolType) {
        return new Symbol(symbolType, symbolType.getPattern());
    }

}
