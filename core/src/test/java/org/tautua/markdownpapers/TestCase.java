package org.tautua.markdownpapers;

public class TestCase {
    private int example;
    private String markdown;
    private String html;
    private String section;

    public int getExample() {
        return example;
    }

    public String getMarkdown() {
        return markdown;
    }

    public String getHtml() {
        return html;
    }

    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return "#" + section + " (" + example + ") -> " + markdown;
    }
}
