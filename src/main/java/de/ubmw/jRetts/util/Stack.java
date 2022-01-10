package de.ubmw.jRetts.util;

import java.util.Collection;

public interface Stack<E> extends Collection<E> {
    void push(E item);
    E pop();
    E peek();
}
