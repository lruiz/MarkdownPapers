package org.tautua.markdownpapers.grammar;

import java.util.HashMap;
import java.util.Map;

public class Document extends SimpleNode {
    private Map<String, LinkRef> linkRefs = new HashMap();

    public Document(int id) {
        super(id);
    }

    public Document(Parser p, int id) {
        super(p, id);
    }

    public LinkRef getLinkRef(String id) {
        return linkRefs.get(id);
    }

    @Override
    public void jjtAddChild(Node n, int i) {
        if (n instanceof LinkRef) {
            linkRefs.put(((LinkRef)n).getId(), (LinkRef)n);
        } else {
            super.jjtAddChild(n, i);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

