package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Comment extends SimpleNode {
    private String text;

    public Comment(int i) {
        super(i);
    }

    public Comment(Parser p, int i) {
        super(p, i);
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
