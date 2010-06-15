package org.tautua.markdownpapers.grammar;

/**
 * Created by IntelliJ IDEA.
 * User: lruiz
 * Date: Apr 29, 2010
 * Time: 12:08:50 AM
 * To change this template use File | Settings | File Templates.
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
