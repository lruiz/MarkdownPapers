package org.tautua.markdownpapers;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;
import org.tautua.markdownpapers.grammar.MarkdownParser;
import org.tautua.markdownpapers.grammar.ParseException;

//TODO: Transform and compare against the expected result.
public class TransformerTest {

    @Test
    public void test() throws IOException, ParseException {
        File directory = new File("target/test-classes/v1.0");
        File[] tests = directory.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("text");
            }
        });

        for (File test : tests) {
            try {
                Object doc = parse(test);
                System.out.println("\"" + test.getName() + "\" Ok");
            } catch (ParseException e) {
                System.out.println("#------------------------#");
                System.out.println("Error in file \"" + test.getName() + "\"");
                e.printStackTrace(System.out);
                System.out.println("#------------------------#");
                throw e;
            }
        }
    }

    public Object parse(File file) throws ParseException, FileNotFoundException {
        Reader reader = new FileReader(file);
        MarkdownParser parser = new MarkdownParser(reader);
        return parser.parse();
    }
}
