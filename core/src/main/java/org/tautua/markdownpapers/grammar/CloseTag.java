package org.tautua.markdownpapers.grammar;

import java.util.ArrayList;

public class CloseTag extends Tag {

    public CloseTag(int id) {
        super(id);
    }

    public CloseTag(Parser p, int id) {
        super(p, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}