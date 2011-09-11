package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.parser.AbstractParserTest;
import org.apache.maven.doxia.parser.Parser;
import org.apache.maven.doxia.sink.Sink;

import java.io.Reader;

import static org.apache.maven.doxia.sink.Sink.NUMBERING_DECIMAL;
import static org.mockito.Mockito.*;

/**
 * @author Larry Ruiz, Aug 26, 2010
 */
public class SinkEventEmitterTest extends AbstractParserTest {
    private MarkdownParser parser;

    protected void setUp() throws Exception {
        super.setUp();
        parser = (MarkdownParser) lookup( Parser.ROLE, "markdown" );
    }

    protected Parser createParser() {
        return parser;
    }

    protected String outputExtension() {
        return "md";
    }


    public void testList() throws org.apache.maven.doxia.parser.ParseException {
        Sink sink = mock(Sink.class);
        Reader reader = getTestReader("list");
        Parser p = createParser();

        p.parse(reader, sink);

        verify(sink).body();
        verify(sink, times(2)).numberedList(NUMBERING_DECIMAL);
        verify(sink, times(2)).numberedList_();
        verify(sink, times(3)).listItem();
        verify(sink, times(3)).listItem_();
        verify(sink).body_();
    }

    public void testParagraph() throws org.apache.maven.doxia.parser.ParseException {
        Sink sink = mock(Sink.class);
        Reader reader = getTestReader("paragraph");
        Parser p = createParser();

        p.parse(reader, sink);

        verify(sink).body();
        verify(sink, times(2)).paragraph();
        verify(sink, times(2)).paragraph_();
        verify(sink).body_();
    }

    public void testHeaders() throws org.apache.maven.doxia.parser.ParseException {
        Sink sink = mock(Sink.class);
        Reader reader = getTestReader("headers");
        Parser p = createParser();

        p.parse(reader, sink);

        verify(sink).body();
        verify(sink, times(2)).sectionTitle(1, null);
        verify(sink, times(2)).sectionTitle_(1);
        verify(sink, times(2)).sectionTitle(2, null);
        verify(sink, times(2)).sectionTitle_(2);
        verify(sink, times(1)).sectionTitle(3, null);
        verify(sink, times(1)).sectionTitle_(3);
        verify(sink).body_();
    }
}
