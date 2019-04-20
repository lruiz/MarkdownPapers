package org.tautua.markdownpapers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.tautua.markdownpapers.parser.ParseException;
import org.tautua.markdownpapers.parser.Parser;
import org.xml.sax.SAXException;
import org.xmlunit.assertj.XmlAssert;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommonMarkSpecTests {
    private static final String SPEC_VERSION = "0.29";
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void testcase(TestCase spec) throws ParseException, ParserConfigurationException, SAXException, IOException {
        Markdown md = new Markdown();
        Reader in = new StringReader(spec.getMarkdown());
        Writer out = new StringWriter();
        md.transform(in, out);
        compare(out.toString(), spec.getHtml());
    }

    public static Stream<Arguments> getArguments() throws IOException {
        Reader r = new InputStreamReader(CommonMarkSpecTests.class.getResourceAsStream("/commonmark/spec-" + SPEC_VERSION + ".json"));
        TestCase[] specs = MAPPER.readValue(r, TestCase[].class);
        r.close();
        return Arrays.stream(specs).map(spec -> arguments(spec));
    }

    /**
     * <p>Compare two xml files, whitespace and attribute order are ignored.</p>
     * @param expected
     * @param actual
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    protected static void compare(String expected, String actual) throws IOException, SAXException, ParserConfigurationException {
        XmlAssert.assertThat(toHtml(actual)).and(toHtml(expected))
                .ignoreComments()
                .ignoreWhitespace()
                .ignoreElementContentWhitespace()
                .normalizeWhitespace()
                .areSimilar();
    }

    protected static String toHtml(String html) throws IOException {
        Document doc = Jsoup.parse(html);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml);;
        return doc.toString();
    }
}
