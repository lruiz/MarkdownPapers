package org.tautua.markdownpapers.grammar;

import java.util.ArrayList;

public class Tag extends SimpleNode {
    private String name;
    private java.util.List<TagAttr> attributes = new ArrayList();

    public Tag(int id) {
        super(id);
    }

    public Tag(Parser p, int id) {
        super(p, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public java.util.List<TagAttr> getAttributes() {
        return attributes;
    }

    public void addAttr(TagAttr attr) {
        attributes.add(attr);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

