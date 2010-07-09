package org.tautua.markdownpapers.grammar;

/**
 * <p>A Resource represents resource location.</p>
 * 
 * @author Larry Ruiz
 */
public class Resource {
    private String location;
    private String name;

    public Resource(String location) {
        this.location = location;
    }

    public Resource(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
