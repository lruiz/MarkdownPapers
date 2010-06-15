package org.tautua.markdownpapers.grammar;

public class Item extends SimpleNode {
    private Item previous;
    private int indentation;
    private boolean ordered;
    private boolean loose;

    public Item(int id) {
        super(id);
    }

    public Item(Parser p, int id) {
        super(p, id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setPrevious(Item previous) {
        this.previous = previous;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void makeOrdered() {
        this.ordered = true;
    }

    public void makeUnordered() {
        this.ordered = false;
    }

    public void makeLoose() {
        this.loose = true;
    }

    public boolean isLoose() {
        if (loose) {
            return true;
        } else {
            return previous != null && previous.loose;
        }
    }
}
