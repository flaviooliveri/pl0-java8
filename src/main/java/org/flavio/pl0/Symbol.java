package org.flavio.pl0;

public class Symbol {

    SymbolType type;
    String value;

    public Symbol(SymbolType type, String value) {
        this.type = type;
        this.value = value;
    }

    public SymbolType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return type == symbol.type && value.equals(symbol.value);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
