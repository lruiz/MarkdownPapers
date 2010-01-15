package org.tautua.markdownpapers.html;

import org.tautua.markdownpapers.grammar.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 */
public class HtmlGenerator implements Visitor {
    private static final Map<String, String> ESCAPED_CHARS;
    private Appendable buffer;
    private Document document;

    static {
        ESCAPED_CHARS = new HashMap();
        ESCAPED_CHARS.put("&", "&amp;");
        ESCAPED_CHARS.put("<", "&lt;");
        ESCAPED_CHARS.put(">", "&gt;");
    }

    public HtmlGenerator(Appendable buffer) {
        this.buffer = buffer;
    }

    public void visit(CharRef node) {
        append(node.getValue());
    }

    public void visit(Code node) {
        append("<pre><code>");
        int count = node.jjtGetNumChildren();
        for(int i = 0; i < count; i++) {
            node.jjtGetChild(i).accept(this);
            if(i < count - 1) {
                append("</br>");
            }
        }
        append("</code></pre>");
    }

    public void visit(Codespan node) {
        append("<code>");
        append(node.getText());
        append("</code>");
    }

    public void visit(Document node) {
        document = node;
        node.childrenAccept(this);
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

    public void visit(EmptyLine node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Header node) {
        append("<h>");
        node.childrenAccept(this);
        append("</h>");
    }

    public void visit(InlineLink node) {
        LinkAttr attr = resolve(node);
        if (attr == null) {
            append("[");
            appendAndEscape(node.getText());
            append("]");
        } else {
            append("<a");
            append(" href=\"");
            append(attr.getUrl());
            append("\">");
            appendAndEscape(node.getText());
            append("</a>");
        }
    }

    public void visit(InlineUrl node) {
        append("<a href=\"");
        appendAndEscape(node.getUrl());
        append("\">");
        appendAndEscape(node.getUrl());
        append("</a>");
    }

    public void visit(Item node) {
        append("<li>");
        node.childrenAccept(this);
        append("</li>");
    }

    public void visit(Line node) {
        node.childrenAccept(this);
    }
    
    public void visit(LinkRef node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(List node) {
        append("<ul>");
        node.childrenAccept(this);
        append("</ul>");
    }

    public void visit(Paragraph node) {
        append("<p>");
        node.childrenAccept(this);
        append("</p>");
    }

    public void visit(Ruler node) {
        append("<hr/>");
    }

    public void visit(Quote node) {
        append("<blockquote>");
        node.childrenAccept(this);
        append("</blockquote>");
    }

    public void visit(SimpleNode node) {
        throw new IllegalArgumentException("can not process this element");
    }

    public void visit(Text node) {
        appendAndEscape(node.getValue());
    }

    LinkAttr resolve(InlineLink link) {
        if (link.getAttr() == null) {
            LinkRef linkRef = null;
            if (link.getRefId() != null) {
                linkRef = document.getLinkRef(link.getRefId());
            } else {
                linkRef = document.getLinkRef(link.getText());
            }
            return linkRef != null ? linkRef.getAttr() : null;
        }

        return link.getAttr();
    }

    void appendAndEscape(String val) {
        for(Map.Entry<String, String> e : ESCAPED_CHARS.entrySet()) {
            val = val.replace(e.getKey(), e.getValue());
        }
        append(val);
    }

    void append(String val) {
        try {
            buffer.append(val);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
