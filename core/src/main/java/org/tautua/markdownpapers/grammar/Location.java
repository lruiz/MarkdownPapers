package org.tautua.markdownpapers.grammar;

/**
 * <p>A Location represents resource location.</p>
 */
public class Location {
    private String url;
    private String title;

    public Location(String url) {
        this.url = url;
    }

    public Location(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
