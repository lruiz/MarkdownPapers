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

package org.tautua.markdownpapers;

import java.io.*;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MarkdownPapersTest extends BaseTest {

    public MarkdownPapersTest() {
        super(new File("target/test-classes/others"), new File("target/generated-test-sources/others"));
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(
                arguments("code"),
                arguments("comments"),
                arguments("codespan"),
                arguments("emphasis"),
                arguments("headers"),
                arguments("images"),
                arguments("inline"),
                arguments("linebreak"),
                arguments("links"),
                arguments("list"),
                arguments("paragraphs"),
                arguments("quoteAndList"),
                arguments("quotes"),
                arguments("rulers"),
                arguments("snippets"),
                arguments("tags"),
                arguments("underscore"),
                arguments("inlineUrls")
            );
    }
}
