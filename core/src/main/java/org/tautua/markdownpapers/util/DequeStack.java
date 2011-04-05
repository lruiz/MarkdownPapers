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


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <p>Stack implementation based on java.util.Deque</p>
 *
 * @author Larry Ruiz
 */
public class DequeStack<E> implements Stack<E> {
    private Deque<E> deque = new ArrayDeque<E>();
    public E peek() {
        return deque.peekLast();
    }

    public void push(E e) {
        deque.addLast(e);
    }

    public E pop() {
        return deque.pollLast();
    }

    public int size() {
        return deque.size();
    }

    public void clear() {
        deque.clear();
    }
}