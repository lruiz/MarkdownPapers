package org.tautua.markdownpapers.html;

import org.custommonkey.xmlunit.HTMLDocumentBuilder;
import org.custommonkey.xmlunit.TolerantSaxDocumentBuilder;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
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
@RunWith(Parameterized.class)
public class MarkdownBasicsTest {
    private static final File outputDir = new File("target/output/basics");
    private static final File basicsAssetsDir = new File("target/test-classes/basics");
    private String fileName;

    public MarkdownBasicsTest(String fileName) {
        this.fileName = fileName;
    }

    @BeforeClass
    public static void setup() {
        if (!outputDir.exists()) {
            outputDir.mkdirs();
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
        File input = new File(basicsAssetsDir, fileName + ".text");
        File expected = new File(basicsAssetsDir, fileName + ".xhtml");
        File output = new File(outputDir, fileName + ".xhtml");
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
