package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Paragraph extends SimpleNode {
    public Paragraph(int id) {
        super(id);
    }

    public Paragraph(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}