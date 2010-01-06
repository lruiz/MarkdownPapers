package org.tautua.markdownpapers.grammar;

public class Line extends SimpleNode {
    public Line(int id) {
        super(id);
    }

    public Line(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
