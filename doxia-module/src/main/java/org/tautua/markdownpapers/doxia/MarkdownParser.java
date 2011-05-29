package org.tautua.markdownpapers.doxia;

import org.apache.maven.doxia.parser.AbstractParser;
import org.apache.maven.doxia.parser.ParseException;
import org.apache.maven.doxia.sink.Sink;
import org.tautua.markdownpapers.ast.Document;
import org.tautua.markdownpapers.parser.Parser;

import java.io.Reader;

/**
 *
 */
public class MarkdownParser extends AbstractParser {

    public void parse(Reader reader, Sink sink) throws ParseException {
        Parser parser = new Parser(reader);
        Document document = null;
        try {
            document = parser.parse();
        } catch (org.tautua.markdownpapers.parser.ParseException e) {
            throw new ParseException(e, e.currentToken.beginLine, e.currentToken.beginColumn);
        }

        SinkEventEmitter emitter = new SinkEventEmitter(sink);
        document.accept(emitter);
    }
}
