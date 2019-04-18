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

/**
 * @author Larry Ruiz
 */
public class Markdown_1_0_Test extends BaseTest {
    private static final File OUTPUT_DIR = new File("target/generated-test-sources/1.0");
    private static final File INPUT_DIR = new File("target/test-classes/1.0");

    public Markdown_1_0_Test() {
        super(INPUT_DIR, OUTPUT_DIR);
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(
                arguments("Amps and angle encoding"),
                arguments("Auto links"),
                arguments("Backslash escapes"),
                arguments("Blockquotes with code blocks"),
                arguments("Hard-wrapped paragraphs with list-like lines"),
                arguments("Horizontal rules"),
                arguments("Inline HTML (Advanced)"),
                arguments("Inline HTML (Simple)"),
                arguments("Inline HTML comments"),
                arguments("Links, inline style"),
                arguments("Links, reference style"),
                arguments("Literal quotes in titles"),
                arguments("Markdown Documentation - Basics"),
                arguments("Markdown Documentation - Syntax"),
                arguments("Nested blockquotes"),
                arguments("Ordered and unordered lists"),
                arguments("Strong and em together"),
                arguments("Tabs"),
                arguments("Tidyness")
            );
    }
}
