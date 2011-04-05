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
public class Item extends SimpleNode {
    private Item previous;
    private int indentation;
    private boolean ordered;
    private boolean loose;

    public Item(int id) {
        super(id);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setPrevious(Item previous) {
        this.previous = previous;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = indentation;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void makeOrdered() {
        this.ordered = true;
    }

    public void makeUnordered() {
        this.ordered = false;
    }

    public void makeLoose() {
        this.loose = true;
    }

    public boolean isLoose() {
        if (loose) {
            return true;
        } else {
            return previous != null && previous.loose;
        }
    }
}
