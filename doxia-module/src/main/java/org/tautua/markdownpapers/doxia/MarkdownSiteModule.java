package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.module.site.AbstractSiteModule;

public class MarkdownSiteModule extends AbstractSiteModule {
    public MarkdownSiteModule() {
        super("markdown", "md", "markdown");
    }
}
