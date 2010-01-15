package org.tautua.markdownpapers.grammar;

public class InlineLink extends SimpleNode {
    private String text;
    private String refId;
    private LinkAttr attr;

    public InlineLink(int id) {
        super(id);
    }

    public InlineLink(Parser p, int id) {
        super(p, id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
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