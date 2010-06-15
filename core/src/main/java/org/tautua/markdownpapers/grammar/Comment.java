package org.tautua.markdownpapers.grammar;

/**
 * Created by IntelliJ IDEA.
 * User: lruiz
 * Date: Jun 4, 2010
 * Time: 12:19:25 AM
 * To change this template use File | Settings | File Templates.
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
