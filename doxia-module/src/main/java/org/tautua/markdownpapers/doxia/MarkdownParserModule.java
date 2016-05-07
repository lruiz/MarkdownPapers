/*
 * Copyright 2011, TAUTUA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.parser.module.AbstractParserModule;

public class MarkdownParserModule extends AbstractParserModule {
    /**
     * The extension for Markdown files.
     */
    public static final String FILE_EXTENSION = "md";

    /**
     * Alternate extension for Markdown files.
     */
    public static final String ALTERNATE_FILE_EXTENSION = "markdown";

    public MarkdownParserModule() {
        super(MarkdownParser.ROLE_HINT, FILE_EXTENSION);
    }
}
