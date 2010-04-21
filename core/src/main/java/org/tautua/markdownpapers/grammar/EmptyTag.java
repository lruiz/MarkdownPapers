package org.tautua.markdownpapers.grammar;

import java.util.*;
import java.util.List;

public class EmptyTag extends SimpleNode {
    private String name;
    private java.util.List<TagAttr> attributes = new ArrayList();

    public EmptyTag(int id) {
        super(id);
    }

    public EmptyTag(Parser p, int id) {
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

    public void setAttributes(List<TagAttr> attributes) {
        this.attributes = attributes;
    }

    public void addAttr(TagAttr attr) {
        attributes.add(attr);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

