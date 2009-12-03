package org.tautua.markdownpapers;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.tautua.markdownpapers.grammar.MarkdownParser;
import org.tautua.markdownpapers.grammar.ParseException;

public class ParserTest {
    @Test
    public void test() throws ParseException {
        Reader reader = new InputStreamReader(getClass().getResourceAsStream(
                "/snippets.text"));
        MarkdownParser parser = new MarkdownParser(reader);
        parser.parse();
    }

    @Test
    public void parts() throws ParseException {
        String[] contentparts = new String[]{"# Header 1",
                "###### Header 6", "[id]: http://tautua.org \"Tautua\" ",
                "   [id]: http://tautua.org", "  ", "***", "* * *",
                " 1. item1", "2. item2", "- item3",};
        for (int i = 0; i < contentparts.length; i++) {
            String part = contentparts[i];
            try {
                compile(part);
            } catch (ParseException e) {
                System.out.println("Error in line #" + i + " - \"" + part
                        + "\"");
                throw e;
            }
        }
    }

    public void compile(String part) throws ParseException {
        Reader reader = new StringReader(part);
        MarkdownParser parser = new MarkdownParser(reader);
        parser.parse();
    }
}
