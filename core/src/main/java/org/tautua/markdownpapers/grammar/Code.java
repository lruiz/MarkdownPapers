package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Code extends SimpleNode {
    public Code(int id) {
        super(id);
    }

    public Code(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
