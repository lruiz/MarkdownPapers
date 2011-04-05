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

package org.tautua.markdownpapers.ast;

/**
 * @author Larry Ruiz
 */
public class Text extends SimpleNode {
    private StringBuilder buffer = new StringBuilder();

    public Text(int id) {
        super(id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void append(String str) {
        buffer.append(str);
    }

    public void append(char c) {
        buffer.append(c);
    }

    public String getValue() {
        return buffer.toString();
    }

    public boolean isWhitespace() {
        return buffer.toString().trim().length() == 0;
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}