package org.tautua.markdownpapers;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.tautua.markdownpapers.grammar.Document;
import org.tautua.markdownpapers.grammar.Parser;
import org.tautua.markdownpapers.grammar.ParseException;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class ParserTest {
    private Reader reader;
    private static final File INPUT_DIR = new File("target/test-classes/");

    public ParserTest(Reader reader) {
        this.reader = reader;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() throws FileNotFoundException {
        return Arrays.asList(new Object[][]{
                {getStringReader("")},
                {getAssetReader("code")},
                {getAssetReader("headers")},
                {getAssetReader("inline")},
                {getAssetReader("list")},
                {getAssetReader("paragraphs")},
                {getAssetReader("quoteAndList")},
                {getAssetReader("quotes")},
                {getAssetReader("rulers")},
                {getAssetReader("tags")}
            });
    }

    @Test
    public void execute() throws ParseException, FileNotFoundException {
        Object obj = parse(reader);
        assertNotNull(obj);
    }

    static Document parse(Reader reader) throws ParseException {
        Parser parser = new Parser(reader);
        return (Document)parser.parse();
    }

    static Reader getAssetReader(String assetName) throws FileNotFoundException {
        return new FileReader(new File(INPUT_DIR, assetName + ".text"));
    }

    static Reader getStringReader(String string) {
        return new StringReader(string);
    }
}
