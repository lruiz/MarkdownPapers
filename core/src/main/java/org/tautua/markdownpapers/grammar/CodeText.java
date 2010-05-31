package org.tautua.markdownpapers.grammar;

public class CodeText extends SimpleNode {
    private StringBuilder buffer = new StringBuilder();

    public CodeText(int id) {
        super(id);
    }

    public CodeText(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void append(String str) {
        buffer.append(str);
    }

    public void append(char c) {
        buffer.append(c);
    }

    public String getValue() {
        return buffer.toString();
    }
}