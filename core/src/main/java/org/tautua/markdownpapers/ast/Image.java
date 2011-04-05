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
public class Image extends SimpleNode implements ResourceHolder {
    private String resourceName;
    private String text;
    private Resource resource;

    public Image(int id) {
        super(id);
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Resource resolve() {
        if (resource == null) {
            if (isBlank(resourceName)) {
                resource = getDocument().findResourceByName(text);
            } else {
                resource = getDocument().findResourceByName(resourceName);
            }
        }

        return resource;
    }
}