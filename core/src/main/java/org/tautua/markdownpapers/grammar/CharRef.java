package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class CharRef extends SimpleNode {
    private String value;

    public CharRef(int id) {
        super(id);
    }

    public CharRef(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}