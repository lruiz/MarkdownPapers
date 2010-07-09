package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Link extends SimpleNode {
    private String text;
    private Type type = Type.REFERENCED;
    private String referenceName;
    private Resource resource;

    public enum Type {
        INLINE,
        REFERENCED
    }

    public Link(int id) {
        super(id);
    }

    public Link(Parser p, int id) {
        super(p, id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public Resource getResource() {
        return resource;
    }

    public void makeReferenced(String referenceName) {
        type = Type.REFERENCED;
        this.referenceName = referenceName;
    }

    public void makeInline(Resource resource) {
        type = Type.INLINE;
        this.resource = resource;
    }

    public boolean isReferenced() {
        return type == Type.REFERENCED;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}