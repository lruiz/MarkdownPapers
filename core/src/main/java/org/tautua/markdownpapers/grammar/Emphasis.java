package org.tautua.markdownpapers.grammar;

public class Emphasis extends SimpleNode {
    private String text;
    private Type type = Type.ITALIC;

    public Emphasis(int id) {
        super(id);
    }

    public Emphasis(Parser p, int id) {
        super(p, id);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void makeItalic() {
        type = Type.ITALIC;
    }

    public void makeBold() {
        type = Type.BOLD;
    }

    public void makeItalicAndBold() {
        type = Type.ITALIC_AND_BOLD;
    }

    public static enum Type {
        ITALIC,
        BOLD,
        ITALIC_AND_BOLD,
    }
}