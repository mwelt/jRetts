package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;

import java.util.HashMap;
import java.util.Map;

public class MuMapping {
    private Map<String, Literal> mappings;

    public MuMapping() {
        mappings = new HashMap<>();
    }

    public Literal apply(Term.Variable var) throws JRettsError {
        if (! mappings.containsKey(var.name())) {
            throw new JRettsError("No mapping for '" + var + "'.");
        }
        return mappings.get(var.name());
    }
}
