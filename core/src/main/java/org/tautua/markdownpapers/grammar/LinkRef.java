package org.tautua.markdownpapers.grammar;

public class LinkRef extends SimpleNode {
    private String id;
    private LinkAttr attr;

    public LinkRef(int id) {
        super(id);
    }

    public LinkRef(Parser p, int id) {
        super(p, id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkAttr getAttr() {
        return attr;
    }

    public void setAttr(LinkAttr attr) {
        this.attr = attr;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}