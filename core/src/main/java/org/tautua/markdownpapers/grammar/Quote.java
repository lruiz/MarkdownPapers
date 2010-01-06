package org.tautua.markdownpapers.grammar;

public class Quote extends SimpleNode {
    public Quote(int id) {
        super(id);
    }

    public Quote(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
