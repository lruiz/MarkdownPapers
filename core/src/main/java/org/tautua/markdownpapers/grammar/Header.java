package org.tautua.markdownpapers.grammar;

public class Header extends SimpleNode {
    public Header(int id) {
        super(id);
    }

    public Header(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
