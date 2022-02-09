package de.ubmw.jRetts.util;

import de.ubmw.jRetts.JRettsError;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Omega implements List<Mu>, Collection<Mu>, Iterable<Mu> {

    public static Omega emptyOmega() {
        return new Omega(Collections.emptyList());
    }

    private final List<Mu> omega;

    private Omega(List<Mu> omega) {
        this.omega = omega;
    }

    public Omega() {
        this(new ArrayList<>());
    }

    public Omega join(Omega o2) throws JRettsError {
        Omega o3 = new Omega();
        for (Mu mu1 : this) {
            for (Mu mu2 : o2) {
                if (mu1.isCompatible(mu2)) {
                    o3.add(mu1.join(mu2));
                }
            }
        }
        return o3;
    }

//    public String toString() {
//
//        StringBuilder b = new StringBuilder();
//
//        b.append(cols.keySet().stream()
//                .map(Term.Variable::name)
//                .collect(Collectors.joining("\t")));
//
//        int colLen = cols.values().iterator().next().size();
//        for (int i = 0; i < colLen; i++) {
//            final int j = i;
//            b.append(
//                    cols.keySet().stream()
//                            .map(k -> cols.get(k).get(j))
//                            .map(Term::toString)
//                            .collect(Collectors.joining("\t")));
//        }
//
//        return b.toString();
//    }


    @Override
    public int size() {
        return omega.size();
    }

    @Override
    public boolean isEmpty() {
        return omega.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return omega.contains(o);
    }

    @Override
    public Iterator<Mu> iterator() {
        return omega.iterator();
    }

    @Override
    public Object[] toArray() {
        return omega.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return omega.toArray(a);
    }

    @Override
    public boolean add(Mu mu) {
        return omega.add(mu);
    }

    @Override
    public boolean remove(Object o) {
        return omega.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return omega.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Mu> c) {
        return omega.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Mu> c) {
        return omega.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return omega.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return omega.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<Mu> operator) {
        omega.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super Mu> c) {
        omega.sort(c);
    }

    @Override
    public void clear() {
        omega.clear();
    }

    @Override
    public boolean equals(Object o) {
        return omega.equals(o);
    }

    @Override
    public int hashCode() {
        return omega.hashCode();
    }

    @Override
    public Mu get(int index) {
        return omega.get(index);
    }

    @Override
    public Mu set(int index, Mu element) {
        return omega.set(index, element);
    }

    @Override
    public void add(int index, Mu element) {
        omega.add(index, element);
    }

    @Override
    public Mu remove(int index) {
        return omega.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return omega.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return omega.lastIndexOf(o);
    }

    @Override
    public ListIterator<Mu> listIterator() {
        return omega.listIterator();
    }

    @Override
    public ListIterator<Mu> listIterator(int index) {
        return omega.listIterator(index);
    }

    @Override
    public List<Mu> subList(int fromIndex, int toIndex) {
        return omega.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<Mu> spliterator() {
        return omega.spliterator();
    }

    public static <E> List<E> of() {
        return List.of();
    }

    public static <E> List<E> of(E e1) {
        return List.of(e1);
    }

    public static <E> List<E> of(E e1, E e2) {
        return List.of(e1, e2);
    }

    public static <E> List<E> of(E e1, E e2, E e3) {
        return List.of(e1, e2, e3);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4) {
        return List.of(e1, e2, e3, e4);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {
        return List.of(e1, e2, e3, e4, e5);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        return List.of(e1, e2, e3, e4, e5, e6);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        return List.of(e1, e2, e3, e4, e5, e6, e7);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    @SafeVarargs
    public static <E> List<E> of(E... elements) {
        return List.of(elements);
    }

    public static <E> List<E> copyOf(Collection<? extends E> coll) {
        return List.copyOf(coll);
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return omega.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super Mu> filter) {
        return omega.removeIf(filter);
    }

    @Override
    public Stream<Mu> stream() {
        return omega.stream();
    }

    @Override
    public Stream<Mu> parallelStream() {
        return omega.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super Mu> action) {
        omega.forEach(action);
    }
}
