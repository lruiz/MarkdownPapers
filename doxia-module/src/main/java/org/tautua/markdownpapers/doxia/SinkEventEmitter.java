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

package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributeSet;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.tautua.markdownpapers.ast.*;
import org.tautua.markdownpapers.util.DequeStack;
import org.tautua.markdownpapers.util.Stack;


import static org.tautua.markdownpapers.util.Utils.SPACE;

/**
 * Generate doxia events that are going to be consume by a corresponding sink
 * @author Larry Ruiz, Aug 25, 2010
 */
public class SinkEventEmitter implements Visitor {
    private Stack<Header> stack = new DequeStack<Header>();
    private Sink sink;

    public SinkEventEmitter(Sink sink) {
        this.sink = sink;
    }

    public void visit(CharRef node) {
        sink.text(node.getValue());
    }

    public void visit(Code node) {
        sink.verbatim(SinkEventAttributeSet.BOXED);
        node.childrenAccept(this);
        sink.verbatim_();
    }

    public void visit(CodeSpan node) {
        sink.text(node.getText(), SinkEventAttributeSet.MONOSPACED);
    }

    public void visit(CodeText node) {
        sink.text(node.getValue());
    }

    public void visit(Comment node) {
        sink.comment(node.getText());
    }

    public void visit(Document node) {
        sink.body();
        node.childrenAccept(this);
        while(!(stack.size() == 0)) {
            sink.section_(stack.pop().getLevel());
        }
        sink.body_();
    }

    public void visit(Emphasis node) {
        switch (node.getType()) {
            case BOLD:
                sink.bold();
                sink.text(node.getText());
                sink.bold_();
                break;
            case ITALIC:
                sink.italic();
                sink.text(node.getText());
                sink.italic_();
                break;
            case ITALIC_AND_BOLD:
                sink.italic();
                sink.bold();
                sink.text(node.getText());
                sink.bold_();
                sink.italic_();
                break;
        }
    }

    public void visit(Header node) {
        if(stack.size() == 0) {
            stack.push(node);
        } else if(stack.peek().getLevel() < node.getLevel()) {
            stack.push(node);
        } else {
            do {
                sink.section_(stack.pop().getLevel());
            } while(!(stack.size() == 0) && stack.peek().getLevel() >= node.getLevel());
            stack.push(node);
        }
        sink.section(node.getLevel(), null);
        sink.sectionTitle(node.getLevel(), null);
        node.childrenAccept(this);
        sink.sectionTitle_(node.getLevel());
    }

    public void visit(Image node) {
        Resource resource = node.getResource();
        SinkEventAttributes attr = new SinkEventAttributeSet();
        attr.addAttribute(SinkEventAttributeSet.ALT, node.getText());
        if(resource.getHint() != null) {
            attr.addAttribute(SinkEventAttributeSet.TITLE, resource.getHint());
        }

        sink.figureGraphics(resource.getLocation(), attr);

    }

    public void visit(Link node) {
        Resource resource = node.getResource();
        if(resource != null) {
            sink.link(resource.getLocation());
            node.childrenAccept(this);
            sink.link_();
        } else {
            sink.text("[");
            node.childrenAccept(this);
            sink.text("]");
        }
    }

    public void visit(InlineUrl node) {
        sink.link(node.getUrl());
        sink.text(node.getUrl());
        sink.link_();
    }

    public void visit(Item node) {
        sink.listItem();
        node.childrenAccept(this);
        sink.listItem_();
    }

    public void visit(Line node) {
        node.childrenAccept(this);
        sink.text("\n");
    }

    public void visit(LineBreak node) {
        sink.lineBreak();
    }

    public void visit(ResourceDefinition node) {
        //do nothing
    }

    public void visit(List node) {
        if (node.isOrdered()) {
            sink.numberedList(Sink.NUMBERING_DECIMAL);
            node.childrenAccept(this);
            sink.numberedList_();
        } else {
            sink.list();
            node.childrenAccept(this);
            sink.list_();
        }
    }

    private void appendAttributes(java.util.List<TagAttribute> attributes) {
        for(TagAttribute a : attributes) {
            sink.rawText(" ");
            sink.rawText(a.getName());
            sink.rawText("=\"");
            sink.rawText(a.getValue());
            sink.rawText("\"");
        }
    }

    public void visit(Paragraph node) {
        Node parent = node.jjtGetParent();
        if(parent instanceof Item) {
            if (!((Item)parent).isLoose()) {
                node.childrenAccept(this);
                return;
            }
        }
        sink.paragraph();
        node.childrenAccept(this);
        sink.paragraph_();
    }

    public void visit(Quote node) {
    }

    public void visit(Ruler node) {
        sink.horizontalRule();
    }

    public void visit(SimpleNode node) {
        throw new UnsupportedOperationException();
    }

    public void visit(Tag node) {
        TagAttributeList attributes = node.getAttributeList();
        TagBody body = node.getBody();

        sink.rawText("<");
        sink.rawText(node.getName());

        if(attributes != null) {
            attributes.accept(this);
        }

        if(body == null) {
            sink.rawText("/>");
        } else {
            sink.rawText(">");
            body.accept(this);
            sink.rawText("</");
            sink.rawText(node.getName());
            sink.rawText(">");
        }
    }

    @Override
    public void visit(TagAttribute node) {
        sink.rawText(" ");
        sink.rawText(node.getName());
        sink.rawText("=\"");
        sink.rawText(node.getValue());
        sink.rawText("\"");
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
        sink.text(node.getValue());
    }
}
