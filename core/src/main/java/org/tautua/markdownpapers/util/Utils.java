package org.tautua.markdownpapers.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Larry Ruiz, Sep 3, 2010
 */
public final class Utils {
    public static final char TAB = '\t';
    public static final char EOL = '\n';
    public static final char SPACE = ' ';
    public static final char QUOTE = '\"';
    public static final char AMPERSAND = '&';
    public static final char LT = '<';
    public static final char GT = '>';
    public static final String EMPTY_STRING = "";


    private static final Map<Character, String> ESCAPED_CHARS;


    static {
        ESCAPED_CHARS = new HashMap<Character, String>();
        ESCAPED_CHARS.put(AMPERSAND, "&amp;");
        ESCAPED_CHARS.put(LT, "&lt;");
        ESCAPED_CHARS.put(GT, "&gt;");
        ESCAPED_CHARS.put(QUOTE, "&quot;");
    }

    public static String escape(char character) {
        String escaped = ESCAPED_CHARS.get(character);
        return escaped == null ? String.valueOf(character) : escaped;
    }

    public static boolean isBlank(String val) {
        return val == null || val.trim().equals(EMPTY_STRING);
    }
}
