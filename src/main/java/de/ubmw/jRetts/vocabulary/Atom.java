package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;

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

    public Atom assignMapping(MuMapping m) throws JRettsError {
        AtomBuilder builder = new AtomBuilder();
        if (s.isVariable()) {
            builder.s(m.apply(s.asVariable()));
        } else {
            builder.s(s);
        }

        if (p.isVariable()) {
            builder.s(m.apply(p.asVariable()));
        } else {
            builder.s(p);
        }

        if (o.isVariable()) {
            builder.s(m.apply(o.asVariable()));
        } else {
            builder.s(o);
        }

        return builder.build();
    }

    public String toString() {
        return  s.toString() + " " + p.toString() + " " + o.toString() + " .";
    }
}
