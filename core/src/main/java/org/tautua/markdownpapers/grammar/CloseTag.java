package org.tautua.markdownpapers.grammar;

import java.util.ArrayList;

public class CloseTag extends SimpleNode {
    private String name;

    public CloseTag(int id) {
        super(id);
    }

    public CloseTag(Parser p, int id) {
        super(p, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}