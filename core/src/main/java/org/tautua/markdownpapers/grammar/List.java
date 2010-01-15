package org.tautua.markdownpapers.grammar;

public class List extends SimpleNode {
    public List(int id) {
        super(id);
    }

    public List(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}