package org.tautua.markdownpapers.grammar.util;


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