package org.flavio.pl0;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class TokenEvaluator {

    private Stream<SymbolType> symbols;

    public TokenEvaluator() {
        symbols = Arrays.asList(SymbolType.values()).stream();
    }

    SymbolType findSymbolType(String token) {
        BinaryOperator<SymbolType> minPrecedence = (o1, o2) -> o1.getPrecedence() < o2.getPrecedence() ? o1 : o2;
        Optional<SymbolType> result = symbols.filter(symbolType -> symbolType.match(token)).reduce(minPrecedence);
        return result.get();
    }

}
