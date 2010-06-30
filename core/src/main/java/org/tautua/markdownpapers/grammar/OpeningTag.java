package org.tautua.markdownpapers.grammar;

import java.util.List;
import java.util.ArrayList;

public class OpeningTag extends Tag {
    private java.util.List<TagAttr> attributes = new ArrayList<TagAttr>();
    private ClosingTag closingTag;


    public OpeningTag(int id) {
        super(id);
    }

    public OpeningTag(Parser p, int id) {
        super(p, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<TagAttr> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TagAttr> attributes) {
        this.attributes = attributes;
    }

    public void addAttr(TagAttr attr) {
        attributes.add(attr);
    }

    public ClosingTag getClosingTag() {
        return closingTag;
    }

    public void setClosingTag(ClosingTag closingTag) {
        this.closingTag = closingTag;
    }

    public boolean isBalanced() {
        return closingTag != null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}