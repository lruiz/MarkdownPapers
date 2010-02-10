package org.tautua.markdownpapers.grammar;

public class CodeSpan extends SimpleNode {
    private String text;

    public CodeSpan(int id) {
        super(id);
    }

    public CodeSpan(Parser p, int id) {
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