package org.tautua.markdownpapers.html;

import org.junit.*;
import org.junit.runner.Describable;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.tautua.markdownpapers.grammar.Document;
import org.tautua.markdownpapers.grammar.ParseException;
import org.tautua.markdownpapers.grammar.Parser;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
public class MarkdownBasicsTest implements Describable {
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
                {"Backslash escapes"}
            });
    }

    @Test
    public void visit() throws IOException, ParseException {
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

    private static void compare(File expected, File output) {
    }

    public Description getDescription() {
        return Description.createTestDescription(getClass(), fileName);
    }
}
