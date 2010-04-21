package org.tautua.markdownpapers.grammar;

public interface Visitor {
    void visit(CharRef node);

    void visit(CloseTag node);

    void visit(Code node);

    void visit(CodeSpan node);

    void visit(Document node);

    void visit(Emphasis node);

    void visit(EmptyTag node);

    void visit(BlankLine node);

    void visit(Header node);

    void visit(Image node);

    void visit(InlineLink node);

    void visit(InlineUrl node);

    void visit(Item node);

    void visit(Line node);

    void visit(LinkRef node);

    void visit(List node);

    void visit(OpenTag node);

    void visit(Paragraph node);

    void visit(Quote node);

    void visit(Ruler node);

    void visit(SimpleNode node);

    void visit(Text node);
}
