package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.doxia.sink.SinkEventAttributeSet;
import org.apache.maven.doxia.sink.SinkEventAttributes;
import org.tautua.markdownpapers.ast.*;

import java.util.Stack;

/**
 * Generate doxia events that are going to be consume by a corresponding sink
 * @author Larry Ruiz, Aug 25, 2010
 */
public class SinkEventEmitter implements Visitor {
    private Stack<Header> stack = new Stack<Header>();
    private Sink sink;

    public SinkEventEmitter(Sink sink) {
        this.sink = sink;
    }

    public void visit(CharRef node) {
        sink.text(node.getValue());
    }

    public void visit(ClosingTag node) {
        sink.rawText("</");
        sink.rawText(node.getName());
        sink.rawText(">");
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
        while(!stack.isEmpty()) {
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

    public void visit(EmptyTag node) {
        sink.rawText("<");
        sink.rawText(node.getName());
        appendAttributes(node.getAttributes());
        sink.rawText("/>");
    }

    public void visit(Header node) {
        if(stack.isEmpty()) {
            stack.push(node);
        } else if(stack.peek().getLevel() < node.getLevel()) {
            stack.push(node);
        } else {
            do {
                sink.section_(stack.pop().getLevel());
            } while(!stack.isEmpty() && stack.peek().getLevel() >= node.getLevel());
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
        attr.addAttribute(SinkEventAttributeSet.TITLE, resource.getHint());

        if (node.jjtGetParent() instanceof Line && node.jjtGetParent().jjtGetNumChildren() == 1) {
            sink.figure();
            sink.figureGraphics(resource.getLocation(), attr);
            sink.figureCaption();
            sink.text(resource.getHint());
            sink.figureCaption_();
            sink.figure_();
        } else {
            // inline graphics
            sink.figureGraphics(resource.getLocation(), attr);
        }
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

    public void visit(OpeningTag node) {
        sink.rawText("<");
        sink.rawText(node.getName());
        appendAttributes(node.getAttributes());
        sink.rawText(">");
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

    public void visit(Text node) {
        sink.text(node.getValue());
    }
}
