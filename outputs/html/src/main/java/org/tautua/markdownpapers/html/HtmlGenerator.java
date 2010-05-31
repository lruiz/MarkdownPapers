package org.tautua.markdownpapers.html;

import org.tautua.markdownpapers.grammar.*;
import org.tautua.markdownpapers.grammar.util.DequeStack;
import org.tautua.markdownpapers.grammar.util.Stack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 */
public class HtmlGenerator implements Visitor {
    private static final Map<String, String> ESCAPED_CHARS;
    private static final String TAB = "\t";
    private static final String TAB_TO_WHITESPACE = "    ";
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

    public void visit(CloseTag node) {
        append("</");
        append(node.getName());
        append(">");
    }

    public void visit(Code node) {
        append("<pre><code>");
        visitChildrenAndAppendSeparator(node, "\n");
        append("</code></pre>");
    }

    public void visit(CodeSpan node) {
        append("<code>");
        appendAndEscape(node.getText());
        append("</code>");
    }

    public void visit(CodeText node) {
        appendAndEscape(node.getValue());
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

    public void visit(BlankLine node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Header node) {
        append("<h>");
        node.childrenAccept(this);
        append("</h>");
    }

    public void visit(Image node) {
        Location location = resolve(node);
        if (location == null) {
            append("<img src=\"\" alt=\"");
            appendAndEscape(node.getText());
            append("\"/>");
        } else {
            append("<img");
            append(" src=\"");
            appendAndEscape(location.getUrl());
            if (node.getText() != null) {
                append("\" alt=\"");
                appendAndEscape(node.getText());
            }
            if (location.getTitle() != null) {
                append("\" title=\"");
                appendAndEscape(location.getTitle());
            }
            append("\"/>");
        }
    }

    public void visit(InlineLink node) {
        Location attr = resolve(node);
        if (attr == null) {
            append("<a href=\"\">");
            appendAndEscape(node.getText());
            append("</a>");
        } else {
            append("<a");
            append(" href=\"");
            appendAndEscape(attr.getUrl());
            if (attr.getTitle() != null) {
                append("\" title=\"");
                appendAndEscape(attr.getTitle());
            }
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

    public void visit(OpenTag node) {
        append("<");
        append(node.getName());
        for(TagAttr attr : node.getAttributes()) {
            append(" ");
            append(attr.getName());
            append("=\"");
            append(attr.getValue());
            append("\"");
        }
        append(">");
    }

    public void visit(Paragraph node) {
        Node child = node.jjtGetChild(0).jjtGetChild(0);
        append("<p>");
        visitChildrenAndAppendSeparator(node," ");
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

    public void visit(EmptyTag node) {
        append("<");
        append(node.getName());
        for (TagAttr attr : node.getAttributes()) {
            append(" ");
            append(attr.getName());
            append("=\"");
            append(attr.getValue());
            append("\"");
        }
        append("/>");
    }

    public void visit(Text node) {
        appendAndEscape(node.getValue());
    }

    Location resolve(Image node) {
        if (node.getLocation() == null) {
            LinkRef linkRef = null;
            if (node.getRefId() != null) {
                linkRef = document.getLinkRef(node.getRefId());
            } else {
                linkRef = document.getLinkRef(node.getText());
            }
            return linkRef != null ? linkRef.getAttr() : null;
        }

        return node.getLocation();
    }


    Location resolve(InlineLink link) {
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

    void visitChildrenAndAppendSeparator(Node node, String separator){
        int count = node.jjtGetNumChildren();
        for(int i = 0; i < count; i++) {
            node.jjtGetChild(i).accept(this);
            if(i < count - 1) {
                append(separator);
            }
        }
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
