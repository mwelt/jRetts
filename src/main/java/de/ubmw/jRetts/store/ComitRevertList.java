package de.ubmw.jRetts.store;

import de.ubmw.jRetts.util.ArrayDequeStack;

import de.ubmw.jRetts.util.Stack;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ComitRevertList<E> implements Collection<E> {
    private final List<E> list;
    private final Stack<Integer> versions;

    public ComitRevertList() {
        list = new ArrayList<>();
        versions = new ArrayDequeStack<>();
        versions.push(0);
    }

    public ComitRevertList(int size) {
        list = new ArrayList<>();
        versions = new ArrayDequeStack<>();
        versions.push(0);
    }

    public void commit() {
        versions.push(list.size());
    }

    public void revert() {
        int k = versions.pop();
        list.subList(k, list.size()).clear();
        // -- never delete zero size version -- //
        if(k == 0) { versions.push(0); }
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public int size() {
        return list.size();
    }

    public boolean add(E e) {
        return list.add(e);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return list.addAll(index, c);
    }

    public void clear() {
        list.clear();
    }

    public E get(int index) {
        return list.get(index);
    }

    public E set(int index, E element) {
        return list.set(index, element);
    }

    public void add(int index, E element) {
        list.add(index, element);
    }

    public E remove(int index) {
        return list.remove(index);
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<E> listIterator(int index) {
        return list.listIterator(index);
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public Spliterator<E> spliterator() {
        return list.spliterator();
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    public <T> T[] toArray(IntFunction<T[]> generator) {
        return list.toArray(generator);
    }

    public boolean removeIf(Predicate<? super E> filter) {
        return list.removeIf(filter);
    }

    public Stream<E> stream() {
        return list.stream();
    }

    public Stream<E> parallelStream() {
        return list.parallelStream();
    }

    public void forEach(Consumer<? super E> action) {
        list.forEach(action);
    }

    public void replaceAll(UnaryOperator<E> operator) {
        list.replaceAll(operator);
    }

    public void sort(Comparator<? super E> c) {
        list.sort(c);
    }

    public Stack<Integer> getVersionStack() {
        return versions;
    }
}
