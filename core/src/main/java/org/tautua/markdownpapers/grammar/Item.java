package org.tautua.markdownpapers.grammar;

public class Item extends SimpleNode {
    public Item(int id) {
        super(id);
    }

    public Item(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
