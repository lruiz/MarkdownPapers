package org.tautua.markdownpapers.grammar;

public class Document extends SimpleNode {
    public Document(int id) {
        super(id);
    }

    public Document(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

