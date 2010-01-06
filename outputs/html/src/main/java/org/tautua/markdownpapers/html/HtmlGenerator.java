package org.tautua.markdownpapers.html;

import org.tautua.markdownpapers.grammar.*;
import java.io.IOException;

/**
 * TODO:
 */
public class HtmlGenerator implements Visitor {
    private Appendable buffer;

    public HtmlGenerator(Appendable buffer) {
        this.buffer = buffer;
    }

    public void visit(SimpleNode node) {
        throw new IllegalArgumentException("can not process this element");
    }

    public void visit(Document node) {
        node.childrenAccept(this);
    }

    public void visit(EmptyLine node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Header node) {
        append("<h>");
        append("</h>");
    }

    public void visit(Ruler node) {
        append("<hr/>");
    }

    public void visit(Quote node) {
        append("<blockquote>");
        append("</blockquote>");
    }

    public void visit(Code node) {
        append("<pre><code>");
        node.childrenAccept(this);
        append("</code></pre>");
    }

    public void visit(LinkRef node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Item node) {
        append("<li>");
        node.childrenAccept(this);
        append("</li>");
    }

    public void visit(Line node) {
        node.childrenAccept(this);
    }

    public void visit(Text node) {
        append(node.getValue());
    }

    public void visit(Emphasis node) {
        switch (node.getType()) {
            case ITALIC:
                append("<em>");
                append(node.getText());
                append("</em>");
                break;
            case BOLD:
                append("<strong>");
                append(node.getText());
                append("</strong>");
                break;
            case ITALIC_AND_BOLD:
                append("<strong><em>");
                append(node.getText());
                append("</em></strong>");
                break;
        }
    }

    public void visit(Codespan node) {
        append("<code>");
        append(node.getText());
        append("</code>");
    }

    void append(String val) {
        try {
            buffer.append(val);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
