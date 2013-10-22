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
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

@RunWith(LabelledParameterized.class)
public class MarkdownPapersTest extends BaseTest {

    public MarkdownPapersTest(String fileName) {
        super(fileName, new File("target/test-classes/others"), new File("target/generated-test-sources/others"));
    }

    @Parameters
    public static List<Object[]> data() throws FileNotFoundException {
        return Arrays.asList(new Object[][]{
                {"code"},
                {"comments"},
                {"codespan"},
                {"emphasis"},
                {"headers"},
                {"images"},
                {"inline"},
                {"linebreak"},
                {"links"},
                {"list"},
                {"paragraphs"},
                {"quoteAndList"},
                {"quotes"},
                {"rulers"},
                {"snippets"},
                {"tags"},
                {"underscore"},
                {"inlineUrls"}
            });
    }
}
