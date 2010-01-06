package org.tautua.markdownpapers.grammar;

public class Codespan extends SimpleNode {
    private String text;

    public Codespan(int id) {
        super(id);
    }

    public Codespan(Parser p, int id) {
        super(p, id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}