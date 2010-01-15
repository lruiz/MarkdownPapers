package org.tautua.markdownpapers.grammar;

public class InlineUrl extends SimpleNode {
    private String url;

    public InlineUrl(int id) {
        super(id);
    }

    public InlineUrl(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}