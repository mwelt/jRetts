package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;

public record Atom(Term s, Term p, Term o, boolean neg) {

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
        }

    }
}
