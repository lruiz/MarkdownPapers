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
public interface Visitor {
    void visit(CharRef node);

    void visit(Code node);

    void visit(CodeSpan node);

    void visit(CodeText node);

    void visit(Comment node);

    void visit(Document node);

    void visit(Emphasis node);

    void visit(EmptyTag node);

    void visit(EndTag node);

    void visit(Header node);

    void visit(Image node);

    void visit(Line node);
    
    void visit(LineBreak node);

    void visit(Link node);
    
    void visit(List node);
    
    void visit(InlineUrl node);

    void visit(Item node);

    void visit(Paragraph node);

    void visit(Quote node);

    void visit(ResourceDefinition node);

    void visit(Ruler node);

    void visit(SimpleNode node);

    void visit(Tag node);

    void visit(TagAttribute node);

    void visit(TagAttributeList node);

    void visit(TagBody node);
    
    void visit(Text node);

    void visit(StartTag node);
}
