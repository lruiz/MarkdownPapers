package org.tautua.markdownpapers.generators;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Larry Ruiz, Sep 3, 2010
 */
public final class Chars {
    public static final char TAB = '\t';
    public static final char EOL = '\n';
    public static final char SPACE = ' ';
    public static final char QUOTE = '\"';
    public static final char AMPERSAND = '&';
    public static final char LT = '<';
    public static final String EMPTY_STRING = "";
    
    private static final Map<Character, String> ESCAPED_CHARS;



    static {
        ESCAPED_CHARS = new HashMap<Character, String>();
        ESCAPED_CHARS.put(AMPERSAND, "&amp;");
        ESCAPED_CHARS.put(LT, "&lt;");
        ESCAPED_CHARS.put('>', "&gt;");
        ESCAPED_CHARS.put(QUOTE, "&quot;");
    }

    public static String escape(char character) {
        String escaped = ESCAPED_CHARS.get(character);
        return escaped == null ? String.valueOf(character) : escaped;
    }
}
