package org.tautua.markdownpapers.grammar;

public class Header extends SimpleNode {
    private int level = 1;

    public Header(int id) {
        super(id);
    }

    public Header(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
