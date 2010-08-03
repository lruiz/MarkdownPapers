/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers.grammar;

/**
 * @author Larry Ruiz
 */
public class Link extends SimpleNode {
    private Type type = Type.REFERENCED;
    private String referenceName;
    private Resource resource;
    private boolean whitespaceAtMiddle = false;

    public enum Type {
        INLINE,
        REFERENCED
    }

    public Link(int id) {
        super(id);
    }

    public Link(Parser p, int id) {
        super(p, id);
    }

    public String getText() {
        StringBuilder buff = new StringBuilder();
        for (Node child : children) {
            if (child instanceof Text) {
                String val = ((Text) child).getValue();
                if ("\n".equals(val)) {
                    if (' ' !=  buff.charAt(buff.length() - 1)) {
                        buff.append(" ");
                    }
                } else {
                    buff.append(val);
                }
            } else if (child instanceof Link) {
                buff.append(((Link)child).getText());
            }
        }
        return buff.toString();
    }

    public String getReferenceName() {
        return referenceName;
    }

    public Resource getResource() {
        return resource;
    }

    public void makeReferenced(String referenceName) {
        type = Type.REFERENCED;
        this.referenceName = referenceName;
    }

    public void makeInline(Resource resource) {
        type = Type.INLINE;
        this.resource = resource;
    }

    public boolean isReferenced() {
        return type == Type.REFERENCED;
    }

    public boolean hasWhitespaceAtMiddle() {
        return whitespaceAtMiddle;
    }

    public void setWhitespaceAtMiddle() {
        whitespaceAtMiddle = true;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}