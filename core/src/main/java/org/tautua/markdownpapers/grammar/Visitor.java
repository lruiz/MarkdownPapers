package org.tautua.markdownpapers.grammar;

public interface Visitor {
    public void visit(SimpleNode node);

    public void visit(Document node);

    public void visit(EmptyLine node);

    public void visit(Header node);

    public void visit(Ruler node);

    public void visit(Quote node);

    public void visit(Code node);

    public void visit(LinkRef node);

    public void visit(Item node);

    public void visit(Line node);

    public void visit(Text node);

    public void visit(Emphasis node);

    public void visit(Codespan node);
}
