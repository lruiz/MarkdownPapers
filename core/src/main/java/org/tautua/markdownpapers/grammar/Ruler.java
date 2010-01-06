package org.tautua.markdownpapers.grammar;

public class Ruler extends SimpleNode {
    public Ruler(int id) {
        super(id);
    }

    public Ruler(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
