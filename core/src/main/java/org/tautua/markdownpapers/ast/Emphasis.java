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
public class Emphasis extends SimpleNode {
    private Type type = Type.ITALIC;

    public Emphasis(int id) {
        super(id);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void makeItalic() {
        type = Type.ITALIC;
    }

    public void makeBold() {
        type = Type.BOLD;
    }

    public void makeItalicAndBold() {
        type = Type.ITALIC_AND_BOLD;
    }

    public enum Type {
        ITALIC,
        BOLD,
        ITALIC_AND_BOLD,
    }
}