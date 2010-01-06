package org.tautua.markdownpapers.grammar;

public class EmptyLine extends SimpleNode {
    public EmptyLine(int id) {
        super(id);
    }

    public EmptyLine(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
