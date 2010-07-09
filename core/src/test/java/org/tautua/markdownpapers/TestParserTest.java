package org.tautua.markdownpapers;

import org.junit.Test;
import org.tautua.markdownpapers.test.ParseException;
import org.tautua.markdownpapers.test.TestParser;

import java.io.StringReader;

/**
 * Created by IntelliJ IDEA.
 * User: lruiz
 * Date: Feb 5, 2010
 * Time: 2:03:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestParserTest {

    @Test
    public void test() throws ParseException {
        StringReader r = new StringReader("<a> <u> x </ul> </a>");
        TestParser p = new TestParser(r);
        p.parse();
    }
}
