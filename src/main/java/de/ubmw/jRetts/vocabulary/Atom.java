package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.util.Mu;

import java.io.Serializable;

public record Atom(Term s, Term p, Term o, boolean neg) implements Serializable {

    public static Atom A(Term s, Term p, Term o) {
        return new Atom(s, p, o);
    }


    public static class AtomBuilder {

        private Term s = null;
        private Term p = null;
        private Term o = null;

        public AtomBuilder() {}

        public AtomBuilder s(Term s) {
            this.s = s;
            return this;
        }

        public AtomBuilder p(Term p) {
            this.p = p;
            return this;
        }

        public AtomBuilder o(Term o) {
            this.o = o;
            return this;
        }

        public Atom build() {
            return new Atom(s, p, o);
        }
    }

    public Atom(Term s, Term p, Term o) {
        this(s, p, o, false);
    }

    public boolean isGround() {
        return ! (s.isVariable() || p.isVariable() || o.isVariable());
    }

    /**
     * Transforms a non ground bgp Atom (this) into a ground instance by applying
     * a corresponding mu mapping.
     *
     * @param mu the mapping to apply.
     * @return the ground instance of this.
     * @throws JRettsError
     */
    public Atom assignMapping(Mu mu) throws JRettsError {
        AtomBuilder builder = new AtomBuilder();
        if (s.isVariable()) {
            builder.s(mu.apply(s.asVariable()));
        } else {
            builder.s(s);
        }

        if (p.isVariable()) {
            builder.s(mu.apply(p.asVariable()));
        } else {
            builder.s(p);
        }

        if (o.isVariable()) {
            builder.s(mu.apply(o.asVariable()));
        } else {
            builder.s(o);
        }

        return builder.build();
    }

    public String toString() {
        return  s.toString() + " " + p.toString() + " " + o.toString() + " .";
    }
}
