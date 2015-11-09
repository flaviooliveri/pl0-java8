package org.flavio.pl0;

public class ID {

    private String name;
    private IDType type;
    private Long value = 0l;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IDType getType() {
        return type;
    }

    public void setType(IDType type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Long.valueOf(value);
    }
}
