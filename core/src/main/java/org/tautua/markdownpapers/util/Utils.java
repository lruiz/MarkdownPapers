/*
 * Copyright 2011, TAUTUA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
