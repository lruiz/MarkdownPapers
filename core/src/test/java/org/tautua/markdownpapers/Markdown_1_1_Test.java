/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers;

import org.custommonkey.xmlunit.HTMLDocumentBuilder;
import org.custommonkey.xmlunit.TolerantSaxDocumentBuilder;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.tautua.markdownpapers.generators.HtmlGenerator;
import org.tautua.markdownpapers.grammar.Document;
import org.tautua.markdownpapers.grammar.ParseException;
import org.tautua.markdownpapers.grammar.Parser;
import org.tautua.markdownpapers.Markdown;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Larry Ruiz
 */
@RunWith(LabelledParameterized.class)
public class Markdown_1_1_Test {
    private static final File OUTPUT_DIR = new File("target/output/1.1/basics");
    private static final File INPUT_DIR = new File("target/test-classes/1.1/basics");
    private static final String INPUT_SUFFIX = ".text";
    private static final String OUTPUT_SUFFIX = ".xhtml";

    private String fileName;

    public Markdown_1_1_Test(String fileName) {
        this.fileName = fileName;
    }

    @BeforeClass
    public static void setup() {
        if (!OUTPUT_DIR.exists()) {
            OUTPUT_DIR.mkdirs();
        }
    }

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Amps and angle encoding"},
                {"Auto links"},
                {"Backslash escapes"},
                {"Blockquotes with code blocks"},
                {"Code Blocks"},
                {"Code Spans"},
                {"Hard-wrapped paragraphs with list-like lines"},
                {"Horizontal rules"},
                {"Images"},
                {"Inline HTML (Advanced)"},
                {"Inline HTML (Simple)"},
                {"Inline HTML comments"},
                {"Links, inline style"},
                {"Links, reference style"},
                {"Links, shortcut references"},
                {"Literal quotes in titles"},
                {"Markdown Documentation - Basics"},
                {"Markdown Documentation - Syntax"},
                {"Nested blockquotes"},
                {"Ordered and unordered lists"},
                {"Strong and em together"},
                {"Tabs"},
                {"Tidyness"}
            });
    }

    @Test
    public void execute() throws Exception {
        File input = new File(INPUT_DIR, fileName + INPUT_SUFFIX);
        File expected = new File(INPUT_DIR, fileName + OUTPUT_SUFFIX);
        File output = new File(OUTPUT_DIR, fileName + OUTPUT_SUFFIX);
        transform(input, output);
        compare(expected, output);
    }

    private static void transform(File in, File out) throws Exception {
        Markdown md = new Markdown();
        Reader r = new FileReader(in);
        Writer w = new FileWriter(out);
        md.transform(r, w);
        r.close();
        w.close();
    }

    /**
     * <p>Compare the two xml files, ignoring whitespace.</p>
     * @param expected
     * @param output
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private static void compare(File expected, File output) throws IOException, SAXException, ParserConfigurationException {
        XMLUnit.setIgnoreWhitespace(true);
        TolerantSaxDocumentBuilder tolerantSaxDocumentBuilder = new TolerantSaxDocumentBuilder(XMLUnit.newTestParser());
        HTMLDocumentBuilder htmlDocumentBuilder = new HTMLDocumentBuilder(tolerantSaxDocumentBuilder);
        org.w3c.dom.Document e = htmlDocumentBuilder.parse(new FileReader(expected));
        org.w3c.dom.Document o = htmlDocumentBuilder.parse(new FileReader(output));
        XMLAssert.assertXMLEqual(e,o);
    }
}
