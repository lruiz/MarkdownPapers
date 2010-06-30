package org.tautua.markdownpapers.grammar;

public class ClosingTag extends Tag {

    public ClosingTag(int id) {
        super(id);
    }

    public ClosingTag(Parser p, int id) {
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