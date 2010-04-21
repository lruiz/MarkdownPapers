package org.tautua.markdownpapers.grammar;

public class Image extends SimpleNode {
    private String refId;
    private String text;
    private Location location;

    public Image(int id) {
        super(id);
    }

    public Image(Parser p, int id) {
        super(p, id);
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}