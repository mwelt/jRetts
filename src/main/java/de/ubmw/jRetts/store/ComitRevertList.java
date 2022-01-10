package de.ubmw.jRetts.store;

import de.ubmw.jRetts.util.ArrayDequeStack;

import de.ubmw.jRetts.util.Stack;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ComitRevertList<E> {
    private final LinkedList<E> queue;
    private final Stack<Integer> versions;

    public ComitRevertList() {
        queue  = new LinkedList<>();
        versions = new ArrayDequeStack<>();
        versions.push(0);
    }

    public ComitRevertList(int size) {
        queue  = new LinkedList<>();
        versions = new ArrayDequeStack<>();
        versions.push(0);
    }

    public void commit() {
        versions.push(queue.size());
    }

    public void revert() {
        int k = versions.pop();
        queue.subList(k, queue.size()).clear();
        // -- never delete zero size version -- //
        if(k == 0) { versions.push(0); }
    }

    public E getFirst() {
        return queue.getFirst();
    }

    public E getLast() {
        return queue.getLast();
    }

    public E removeFirst() {
        return queue.removeFirst();
    }

    public E removeLast() {
        return queue.removeLast();
    }

    public void addFirst(E e) {
        queue.addFirst(e);
    }

    public void addLast(E e) {
        queue.addLast(e);
    }

    public boolean contains(Object o) {
        return queue.contains(o);
    }

    public int size() {
        return queue.size();
    }

    public boolean add(E e) {
        return queue.add(e);
    }

    public boolean remove(Object o) {
        return queue.remove(o);
    }

    public boolean addAll(Collection<? extends E> c) {
        return queue.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return queue.addAll(index, c);
    }

    public void clear() {
        queue.clear();
    }

    public E get(int index) {
        return queue.get(index);
    }

    public E set(int index, E element) {
        return queue.set(index, element);
    }

    public void add(int index, E element) {
        queue.add(index, element);
    }

    public E remove(int index) {
        return queue.remove(index);
    }

    public int indexOf(Object o) {
        return queue.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return queue.lastIndexOf(o);
    }

    public E peek() {
        return queue.peek();
    }

    public E element() {
        return queue.element();
    }

    public E poll() {
        return queue.poll();
    }

    public E remove() {
        return queue.remove();
    }

    public boolean offer(E e) {
        return queue.offer(e);
    }

    public boolean offerFirst(E e) {
        return queue.offerFirst(e);
    }

    public boolean offerLast(E e) {
        return queue.offerLast(e);
    }

    public E peekFirst() {
        return queue.peekFirst();
    }

    public E peekLast() {
        return queue.peekLast();
    }

    public E pollFirst() {
        return queue.pollFirst();
    }

    public E pollLast() {
        return queue.pollLast();
    }

    public void push(E e) {
        queue.push(e);
    }

    public E pop() {
        return queue.pop();
    }

    public boolean removeFirstOccurrence(Object o) {
        return queue.removeFirstOccurrence(o);
    }

    public boolean removeLastOccurrence(Object o) {
        return queue.removeLastOccurrence(o);
    }

    public ListIterator<E> listIterator(int index) {
        return queue.listIterator(index);
    }

    public Iterator<E> descendingIterator() {
        return queue.descendingIterator();
    }

    public Object[] toArray() {
        return queue.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return queue.toArray(a);
    }

    public Spliterator<E> spliterator() {
        return queue.spliterator();
    }

    public Iterator<E> iterator() {
        return queue.iterator();
    }

    public ListIterator<E> listIterator() {
        return queue.listIterator();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return queue.subList(fromIndex, toIndex);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean containsAll(Collection<?> c) {
        return queue.containsAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return queue.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return queue.retainAll(c);
    }

    public <T> T[] toArray(IntFunction<T[]> generator) {
        return queue.toArray(generator);
    }

    public boolean removeIf(Predicate<? super E> filter) {
        return queue.removeIf(filter);
    }

    public Stream<E> stream() {
        return queue.stream();
    }

    public Stream<E> parallelStream() {
        return queue.parallelStream();
    }

    public void forEach(Consumer<? super E> action) {
        queue.forEach(action);
    }

    public void replaceAll(UnaryOperator<E> operator) {
        queue.replaceAll(operator);
    }

    public void sort(Comparator<? super E> c) {
        queue.sort(c);
    }

    public Stack<Integer> getVersionStack() {
        return versions;
    }
}
