package org.tautua.markdownpapers.grammar;

public class TagAttr {
    private String name;
    private String value;

    public TagAttr(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}