package org.flavio.pl0;

public class BaseAndOffset {

    private int base;
    private int offset;

    public BaseAndOffset(int base, int offset) {
        this.base = base;
        this.offset = offset;
    }

    public int getBase() {
        return base;
    }

    public int getOffset() {
        return offset;
    }

    public int getBasePlusOffset() {
        return base + offset;
    }

    public void increment() {
        offset ++;
    }

}
