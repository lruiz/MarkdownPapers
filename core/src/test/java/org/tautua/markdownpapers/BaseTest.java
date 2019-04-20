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

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Whitelist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.xml.sax.SAXException;
import org.xmlunit.assertj.XmlAssert;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Larry Ruiz
 */
public abstract class BaseTest {

    private static final String DEFAULT_OUTPUT_EXTENSION = ".html";
    private static final String DEFAULT_INPUT_EXTENSION = ".text";
    
    private String inputExtension;
    private String outputExtension; 
    private File inputDirectory;
    private File outputDirectory;

    protected BaseTest(File inputDirectory, File outputDirectory) {
        this(inputDirectory, outputDirectory, DEFAULT_INPUT_EXTENSION, DEFAULT_OUTPUT_EXTENSION);
    }
    
    protected BaseTest(File inputDirectory, File outputDirectory, String inputExtension, String outputExtension) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.inputExtension = inputExtension;
        this.outputExtension = outputExtension;
    }


    protected static void transform(File in, File out) throws Exception {
        Markdown md = new Markdown();
        Reader r = new FileReader(in);
        Writer w = new FileWriter(out);
        md.transform(r, w);
        r.close();
        w.close();
    }

    /**
     * <p>Compare two xml files, whitespace and attribute order are ignored.</p>
     * @param expected
     * @param actual
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    protected static void compare(File expected, File actual) throws IOException, SAXException, ParserConfigurationException {
        XmlAssert.assertThat(toHtml(actual)).and(toHtml(expected))
                .ignoreComments()
                .ignoreWhitespace()
                .ignoreElementContentWhitespace()
                .normalizeWhitespace()
                .areSimilar();
    }

    protected static String toHtml(File file) throws IOException {
        Document doc = Jsoup.parse(file, "UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml);
        return doc.toString();
    }
    
    @BeforeEach
    public void setup() {
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void execute(String fileName) throws Exception {
        File input = new File(inputDirectory, fileName + inputExtension);
        File expected = new File(inputDirectory, fileName + outputExtension);
        File output = new File(outputDirectory, fileName + outputExtension);
        transform(input, output);
        if(expected.exists()) {
            compare(expected, output);
        }
    }
}
