package org.tautua.markdownpapers.grammar;

public class Text extends SimpleNode {
    private StringBuilder buffer = new StringBuilder();

    public Text(int id) {
        super(id);
    }

    public Text(Parser p, int id) {
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

    public boolean isWhitespace() {
        return buffer.toString().trim().length() == 0;
    }
}