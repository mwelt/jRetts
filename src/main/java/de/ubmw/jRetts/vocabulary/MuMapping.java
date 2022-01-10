package de.ubmw.jRetts.vocabulary;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MuMapping {
    private Map<String, Literal> mappings;

    public MuMapping() {
        mappings = new HashMap<>();
    }

    public Optional<Literal> apply(Term.Variable var) {
        return Optional.ofNullable(mappings.get(var.s()));
    }
}
