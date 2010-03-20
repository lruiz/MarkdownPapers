package org.tautua.markdownpapers.grammar;

public class BlankLine extends SimpleNode {
    public BlankLine(int id) {
        super(id);
    }

    public BlankLine(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
