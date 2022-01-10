package de.ubmw.jRetts.util;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ArrayDequeStack<E> implements Stack<E> {

    private final Deque<E> deque = new ArrayDeque<>();

    @Override
    public void push(E item) {
        deque.addFirst(item);
    }

    @Override
    public E pop() {
        return deque.removeFirst();
    }

    @Override
    public E peek() {
        return deque.peekFirst();
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return deque.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return deque.iterator();
    }

    @Override
    public Object[] toArray() {
        return deque.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return deque.toArray(a);
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return deque.toArray(generator);
    }

    @Override
    public boolean add(E e) {
        return deque.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return deque.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return deque.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return deque.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return deque.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return deque.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return deque.retainAll(c);
    }

    @Override
    public void clear() {
        deque.clear();
    }

    @Override
    public boolean equals(Object o) {
        return deque.equals(o);
    }

    @Override
    public int hashCode() {
        return deque.hashCode();
    }

    @Override
    public Spliterator<E> spliterator() {
        return deque.spliterator();
    }

    @Override
    public Stream<E> stream() {
        return deque.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return deque.parallelStream();
    }

}