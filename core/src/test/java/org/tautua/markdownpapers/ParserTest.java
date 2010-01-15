package org.tautua.markdownpapers;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tautua.markdownpapers.grammar.Node;
import org.tautua.markdownpapers.grammar.Parser;
import org.tautua.markdownpapers.grammar.ParseException;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class ParserTest {
    private String fileName;
    private static final File assetsDir = new File("target/test-classes/");

    public ParserTest(String fileName) {
        this.fileName = fileName;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"rulers"},
                {"headers"},
                {"list"},
                {"paragraphs"},
                {"snippets"},
            });
    }

    @Test
    public void execute() throws ParseException, FileNotFoundException {
        File input = new File(assetsDir, fileName + ".text");
        Parser parser = new Parser(new FileReader(input));
        Object obj = parser.parse();
        assertNotNull(obj);
    }

    public Node compile(String part) throws ParseException {
        Reader reader = new StringReader(part);
        Parser parser = new Parser(reader);
        return (Node)parser.parse();
    }
}
