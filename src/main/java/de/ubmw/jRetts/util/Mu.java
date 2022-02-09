package de.ubmw.jRetts.util;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.vocabulary.Term;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Mu implements Map<Term.Variable, Term> {

    private final Map<Term.Variable, Term> mu = new HashMap<>();

    public boolean isCompatible(Mu mu2) throws JRettsError {
        for (var kv : this.entrySet()) {
            if (mu2.containsKey(kv.getKey())) {
                Term t2 = mu2.get(kv.getKey());
                if (! t2.equals(kv.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Mu join(Mu mu2) {

        Mu mu3 = new Mu();
        mu3.putAll(this);

        for (var kv : mu2.entrySet()) {
            if (! mu3.containsKey(kv.getKey())) {
                mu3.put(kv.getKey(), kv.getValue());
            }
        }

        return mu3;
    }

    @Override
    public int size() {
        return mu.size();
    }

    @Override
    public boolean isEmpty() {
        return mu.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return mu.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return mu.containsValue(value);
    }

    @Override
    public Term get(Object key) {
        return mu.get(key);
    }

    @Override
    public Term put(Term.Variable key, Term value) {
        return mu.put(key, value);
    }

    @Override
    public Term remove(Object key) {
        return mu.remove(key);
    }

    @Override
    public void putAll(Map<? extends Term.Variable, ? extends Term> m) {
        mu.putAll(m);
    }

    @Override
    public void clear() {
        mu.clear();
    }

    @Override
    public Set<Term.Variable> keySet() {
        return mu.keySet();
    }

    @Override
    public Collection<Term> values() {
        return mu.values();
    }

    @Override
    public Set<Entry<Term.Variable, Term>> entrySet() {
        return mu.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return mu.equals(o);
    }

    @Override
    public int hashCode() {
        return mu.hashCode();
    }

    @Override
    public Term getOrDefault(Object key, Term defaultValue) {
        return mu.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super Term.Variable, ? super Term> action) {
        mu.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super Term.Variable, ? super Term, ? extends Term> function) {
        mu.replaceAll(function);
    }

    @Override
    public Term putIfAbsent(Term.Variable key, Term value) {
        return mu.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return mu.remove(key, value);
    }

    @Override
    public boolean replace(Term.Variable key, Term oldValue, Term newValue) {
        return mu.replace(key, oldValue, newValue);
    }

    @Override
    public Term replace(Term.Variable key, Term value) {
        return mu.replace(key, value);
    }

    @Override
    public Term computeIfAbsent(Term.Variable key, Function<? super Term.Variable, ? extends Term> mappingFunction) {
        return mu.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public Term computeIfPresent(Term.Variable key, BiFunction<? super Term.Variable, ? super Term, ? extends Term> remappingFunction) {
        return mu.computeIfPresent(key, remappingFunction);
    }

    @Override
    public Term compute(Term.Variable key, BiFunction<? super Term.Variable, ? super Term, ? extends Term> remappingFunction) {
        return mu.compute(key, remappingFunction);
    }

    @Override
    public Term merge(Term.Variable key, Term value, BiFunction<? super Term, ? super Term, ? extends Term> remappingFunction) {
        return mu.merge(key, value, remappingFunction);
    }

    public static <K, V> Map<K, V> of() {
        return Map.of();
    }

    public static <K, V> Map<K, V> of(K k1, V v1) {
        return Map.of(k1, v1);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
        return Map.of(k1, v1, k2, v2);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return Map.of(k1, v1, k2, v2, k3, v3);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9);
    }

    public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) {
        return Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries) {
        return Map.ofEntries(entries);
    }

    public static <K, V> Entry<K, V> entry(K k, V v) {
        return Map.entry(k, v);
    }

    public static <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return Map.copyOf(map);
    }
}
