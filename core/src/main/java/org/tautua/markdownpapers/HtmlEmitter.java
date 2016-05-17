/*
 * Copyright 2011, TAUTUA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers;

import org.tautua.markdownpapers.ast.*;

import java.io.IOException;

import static org.tautua.markdownpapers.util.Utils.*;
/**
 * <p>HTML generator.</p>
 *
 * @author Larry Ruiz
 */
public class HtmlEmitter implements Visitor {
    private Appendable buffer;

    public HtmlEmitter(Appendable buffer) {
        this.buffer = buffer;
    }

    public void visit(CharRef node) {
        append(node.getValue());
    }

    public void visit(Code node) {
        append("<pre><code>");
        visitChildrenAndAppendSeparator(node, EOL);
        append("</code></pre>");
        append(EOL);
    }

    public void visit(CodeSpan node) {
        append("<code>");
        escapeAndAppend(node.getText());
        append("</code>");
    }

    public void visit(CodeText node) {
        escapeAndAppend(node.getValue());
    }

    public void visit(Comment node) {
        append("<!--");
        append(node.getText());
        append("-->");
    }

    public void visit(Document node) {
        visitChildrenAndAppendSeparator(node, EOL);
    }

    public void visit(Emphasis node) {
        switch (node.getType()) {
            case ITALIC:
                append("<em>");
                node.childrenAccept(this);
                append("</em>");
                break;
            case BOLD:
                append("<strong>");
                node.childrenAccept(this);
                append("</strong>");
                break;
            case ITALIC_AND_BOLD:
                append("<strong><em>");
                node.childrenAccept(this);
                append("</em></strong>");
                break;
        }
    }

    public void visit(EmptyTag node) {
        TagAttributeList attributes = node.getAttributeList();
        append("<");
        append(node.getName());

        if(attributes != null) {
            attributes.accept(this);
        }
        append("/>");
    }

    public void visit(EndTag node) {
        append("</");
        append(node.getName());
        append(">");
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
        append(EOL);
    }

    public void visit(Image node) {
        Resource resource = node.getResource();
        if (resource == null) {
            if(node.getReference() != null){
                append("![");
                if (node.getText() != null) {
                    escapeAndAppend(node.getText());
                }
                append("][");
                escapeAndAppend(node.getReference());
                append("]");
            } else {
                append("<img src=\"\" alt=\"");
                if (node.getText() != null) {
                    escapeAndAppend(node.getText());
                }
                append("\"/>");
            }
        } else {
            append("<img");
            append(" src=\"");
            escapeAndAppend(resource.getLocation());
            append("\" alt=\"");
            if (node.getText() != null) {
                escapeAndAppend(node.getText());
            }
            if (resource.getHint() != null) {
                append("\" title=\"");
                escapeAndAppend(resource.getHint());
            }
            append("\"/>");
        }
    }

    public void visit(InlineUrl node) {
        append("<a href=\"");
        escapeAndAppend(node.getUrl());
        append("\">");
        escapeAndAppend(node.getUrl());
        append("</a>");
    }

    public void visit(Item node) {
        append("<li>");
        node.childrenAccept(this);
        append("</li>");
        append(EOL);
    }

    public void visit(Line node) {
        node.childrenAccept(this);
    }

    @Override
    public void visit(LineBreak node) {
        Line l = (Line) node.jjtGetParent();
        if(!l.isEnding()) {
            append("<br/>");
        }
    }

    public void visit(Link node) {
        Resource resource = node.getResource();
        if (resource == null) {
            if (node.isReferenced()) {
                append("[");
                node.childrenAccept(this);
                append("]");
                if (node.getReference() != null) {
                    if (node.hasWhitespaceAtMiddle()) {
                        append(' ');
                    }
                    append("[");
                    append(node.getReference());
                    append("]");
                }
            } else {
                append("<a href=\"\">");
                node.childrenAccept(this);
                append("</a>");
            }
        } else {
            append("<a");
            append(" href=\"");
            escapeAndAppend(resource.getLocation());
            if (resource.getHint() != null) {
                append("\" title=\"");
                escapeAndAppend(resource.getHint());
            }
            append("\">");
            node.childrenAccept(this);
            append("</a>");
        }
    }

    public void visit(ResourceDefinition node) {
        // do nothing
    }

    public void visit(List node) {
        if (node.isOrdered()) {
            append("<ol>");
            append(EOL);
            node.childrenAccept(this);
            append("</ol>");
        } else {
            append("<ul>");
            append(EOL);
            node.childrenAccept(this);
            append("</ul>");
        }
        append(EOL);
    }

    public void visit(Paragraph node) {
        Node parent = node.jjtGetParent();
        if(parent instanceof Item) {
            if (!((Item)parent).isLoose()) {
                visitChildrenAndAppendSeparator(node, EOL);
                return;
            }
        }
        append("<p>");
        visitChildrenAndAppendSeparator(node, EOL);
        append("</p>");
        append(EOL);
    }

    public void visit(Ruler node) {
        append("<hr/>");
        append(EOL);
    }

    public void visit(Quote node) {
        append("<blockquote>");
        append(EOL);
        node.childrenAccept(this);
        append("</blockquote>");
        append(EOL);
    }

    public void visit(SimpleNode node) {
        throw new IllegalArgumentException("can not process this element");
    }

    public void visit(Tag node) {
        TagAttributeList attributes = node.getAttributeList();
        TagBody body = node.getBody();

        append("<");
        append(node.getName());

        if(attributes != null) {
            attributes.accept(this);
        }

        if(body == null) {
            if(isEmptyTag(node.getName())) {
                append("/>");
            } else {
                append("></");
                append(node.getName());
                append(">");
            }
        } else {
            append(">");
            body.accept(this);
            append("</");
            append(node.getName());
            append(">");
        }
    }

    @Override
    public void visit(TagAttribute node) {
        append(SPACE);
        append(node.getName());
        append("=\"");
        append(node.getValue());
        append("\"");
    }

    @Override
    public void visit(TagAttributeList node) {
        node.childrenAccept(this);
    }

    @Override
    public void visit(TagBody node) {
        node.childrenAccept(this);
    }

    public void visit(Text node) {
        if(node.jjtGetParent() instanceof TagBody) {
            append(node.getValue());
        } else {
            escapeAndAppend(node.getValue());
        }
    }

    @Override
    public void visit(StartTag node) {
        TagAttributeList attributes = node.getAttributeList();
        append("<");
        append(node.getName());

        if(attributes != null) {
            attributes.accept(this);
        }
        append(">");
    }

    protected void visitChildrenAndAppendSeparator(Node node, char separator){
        int count = node.jjtGetNumChildren();
        for(int i = 0; i < count; i++) {
            node.jjtGetChild(i).accept(this);
            if(i < count - 1) {
                append(separator);
            }
        }
    }

    protected void visit(Node[] nodes) {
        for (Node n : nodes) {
            n.accept(this);
        }
    }

    protected void escapeAndAppend(String val) {
        for(char character : val.toCharArray()) {
            append(escape(character));
        }
    }

    protected void append(String val) {
        try {
            buffer.append(val);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void append(char val) {
        try {
            buffer.append(val);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
