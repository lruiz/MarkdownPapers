package org.tautua.markdownpapers.grammar;

public class LinkRef extends SimpleNode {
    private String id;
    private String url;
    private String title;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}