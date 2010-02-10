package org.tautua.markdownpapers.grammar;

/**
 * Created by IntelliJ IDEA.
 * User: lruiz
 * Date: Jan 15, 2010
 * Time: 1:24:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkAttr {
    private String url;
    private String title;

    public LinkAttr(String url) {
        this.url = url;
    }

    public LinkAttr(String url, String title) {
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
