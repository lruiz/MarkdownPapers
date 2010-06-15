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

    public void visit(Comment node) {
        append("<!--");
        append(node.getText());
        append("-->");
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
        String level = String.valueOf(node.getLevel());
        append("<h");
        append(level);
        append(">");
        node.childrenAccept(this);
        append("</h");
        append(level);
        append(">");
    }

    public void visit(Image node) {
        Resource resource = resolve(node);
        if (resource == null) {
            append("<img src=\"\" alt=\"");
            appendAndEscape(node.getText());
            append("\"/>");
        } else {
            append("<img");
            append(" src=\"");
            appendAndEscape(resource.getLocation());
            if (node.getText() != null) {
                append("\" alt=\"");
                appendAndEscape(node.getText());
            }
            if (resource.getName() != null) {
                append("\" title=\"");
                appendAndEscape(resource.getName());
            }
            append("\"/>");
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

    public void visit(Link node) {
        Resource resource = resolve(node);
        if (resource == null) {
            if (node.isReferenced()) {
                append("[");
                appendAndEscape(node.getText());
                append("]");
                if (node.getReferenceName() != null) {
                    append("[");
                    append(node.getReferenceName());
                    append("]");
                }
            } else {
                append("<a href=\"\">");
                appendAndEscape(node.getText());
                append("</a>");
            }
        } else {
            append("<a");
            append(" href=\"");
            appendAndEscape(resource.getLocation());
            if (resource.getName() != null) {
                append("\" title=\"");
                appendAndEscape(resource.getName());
            }
            append("\">");
            appendAndEscape(node.getText());
            append("</a>");
        }
    }

    public void visit(LinkRef node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(List node) {
        if (node.isOrdered()) {
            append("<ol>");
            node.childrenAccept(this);
            append("</ol>");
        } else {
            append("<ul>");
            node.childrenAccept(this);
            append("</ul>");
        }
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
        Node parent = node.jjtGetParent();
        if(parent instanceof Item) {
            if (!((Item)parent).isLoose()) {
                visitChildrenAndAppendSeparator(node," ");
                return;
            }
        }
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

    Resource resolve(Image node) {
        if (node.getResource() == null) {
            LinkRef linkRef;
            if (node.getRefId() == null) {
                linkRef = document.getLinkRef(node.getText());
            } else {
                linkRef = document.getLinkRef(node.getRefId());
            }
            return linkRef != null ? linkRef.getResource() : null;
        }

        return node.getResource();
    }


    Resource resolve(Link link) {
        if (link.isReferenced()) {
            LinkRef linkRef = null;
            if (link.getReferenceName() == null || link.getReferenceName().equals("")) {
                linkRef = document.getLinkRef(link.getText());
            } else {
                linkRef = document.getLinkRef(link.getReferenceName());
            }
            return linkRef != null ? linkRef.getResource() : null;
        }

        return link.getResource();
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

    boolean isLast(Node parent, Node child) {
        int count = parent.jjtGetNumChildren();
        for (int i = 0; i < count; i++) {
            if(parent.jjtGetChild(i).equals(child)) {
                return i == count - 1;
            }
        }
        return false;
    }
}
