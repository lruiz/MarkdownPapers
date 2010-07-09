package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Tag extends SimpleNode {
    protected String name;

    public Tag(Parser p, int i) {
        super(p, i);
    }

    public Tag(int i) {
        super(i);
    }

    public String getName() {
        return name;
    }
}
