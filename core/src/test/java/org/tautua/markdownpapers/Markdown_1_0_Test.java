package org.tautua.markdownpapers;

import org.custommonkey.xmlunit.HTMLDocumentBuilder;
import org.custommonkey.xmlunit.TolerantSaxDocumentBuilder;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.tautua.markdownpapers.generators.HtmlGenerator;
import org.tautua.markdownpapers.grammar.Document;
import org.tautua.markdownpapers.grammar.ParseException;
import org.tautua.markdownpapers.grammar.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(LabelledParameterized.class)
public class Markdown_1_0_Test {
    private static final File OUTPUT_DIR = new File("target/output/1.0");
    private static final File INPUT_DIR = new File("target/test-classes/1.0");
    private static final String INPUT_SUFFIX = ".text";
    private static final String OUTPUT_SUFFIX = ".html";

    private String fileName;

    public Markdown_1_0_Test(String fileName) {
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
                {"Hard-wrapped paragraphs with list-like lines"},
                {"Horizontal rules"},
                {"Inline HTML (Advanced)"},
                {"Inline HTML (Simple)"},
                {"Inline HTML comments"},
                {"Links, inline style"},
                {"Links, reference style"},
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
        generate(output, (Document)parse(input));
        compare(expected, output);
    }

    private static Object parse(File input) throws FileNotFoundException, ParseException {
        Parser parser = new Parser(new FileReader(input));
        return parser.parse();
    }

    private static void generate(File output, Document doc) throws IOException {
        Writer writer = new FileWriter(output);
        HtmlGenerator generator = new HtmlGenerator(writer);
        doc.accept(generator);
        writer.flush();
        writer.close();
    }

    /**
     * <p>Compare two xml files, whitespace and attribute order are ignored.</p>
     * @param expected
     * @param output
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    private static void compare(File expected, File output) throws IOException, SAXException, ParserConfigurationException {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        TolerantSaxDocumentBuilder tolerantSaxDocumentBuilder = new TolerantSaxDocumentBuilder(XMLUnit.newTestParser());
        HTMLDocumentBuilder htmlDocumentBuilder = new HTMLDocumentBuilder(tolerantSaxDocumentBuilder);
        org.w3c.dom.Document e = htmlDocumentBuilder.parse(new FileReader(expected));
        org.w3c.dom.Document o = htmlDocumentBuilder.parse(new FileReader(output));
        XMLAssert.assertXMLEqual(e,o);
    }
}