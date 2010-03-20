package org.tautua.markdownpapers.grammar.util;

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
}
