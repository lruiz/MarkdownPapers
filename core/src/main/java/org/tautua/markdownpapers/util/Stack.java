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

/**
 * <p>Stack representation - LIFO.</p>
 *
 * @author Larry Ruiz
 */
public interface Stack<E> {
    /**
     * Retrieves, but does not remove, the last element of this deque,
     * or returns <tt>null</tt> if this deque is empty.
     * @return
     */
    E peek();

    void push(E e);

    E pop();

    int size();

    void clear();
}
