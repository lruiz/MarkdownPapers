package org.tautua.markdownpapers.grammar;

public class LinkRef extends SimpleNode {
    private String id;
    private Resource resource;

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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}