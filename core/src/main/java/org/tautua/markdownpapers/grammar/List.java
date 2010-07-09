package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class List extends SimpleNode {
    private int indentation;

    public List(int id) {
        super(id);
    }

    public List(Parser p, int id) {
        super(p, id);
    }

    public int getIndentation() {
        return indentation;
    }
    
    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public boolean isOrdered() {
        return ((Item)children[0]).isOrdered();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void jjtAddChild(Node n, int i) {
        super.jjtAddChild(n, i);
        if (i < children.length - 1) {
            ((Item)children[i + 1]).setPrevious((Item)n);
        }
    }
}