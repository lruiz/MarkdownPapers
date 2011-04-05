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
 * <p>Represents a resource which include location and an additional hint information,
 * it could be a webpage or image</p>
 * 
 * @author Larry Ruiz
 */
public class Resource {
    private String location;
    private String hint;

    public Resource(String location) {
        this.location = location;
    }

    public Resource(String location, String hint) {
        this.location = location;
        this.hint = hint;
    }

    public String getLocation() {
        return location;
    }

    public String getHint() {
        return hint;
    }
}
