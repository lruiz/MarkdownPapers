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

import static org.tautua.markdownpapers.util.Utils.isBlank;

/**
 * @author Larry Ruiz
 */
public class Link extends SimpleNode implements ResourceReference {
    private boolean inline = false;
    private String reference;
    private Resource resource;
    private boolean whitespaceAtMiddle = false;

    public Link(int id) {
        super(id);
    }

    public String getText() {
        StringBuilder buff = new StringBuilder();
        for (Node child : children) {
            if (child instanceof Text) {
                String val = ((Text) child).getValue();
                if ("\n".equals(val)) {
                    if (buff.length() > 0 && ' ' !=  buff.charAt(buff.length() - 1)) {
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Resource getResource() {
        if (resource == null) {
            if (isBlank(reference)) {
                resource = getDocument().findResource(getText());
            } else {
                resource = getDocument().findResource(reference);
            }
        }

        return resource;
    }

    public void setResource(Resource resource) {
        inline = true;
        this.resource = resource;
    }

    public boolean isReferenced() {
        return !inline;
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