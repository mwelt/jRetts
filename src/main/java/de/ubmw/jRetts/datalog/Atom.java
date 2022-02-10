package de.ubmw.jRetts.datalog;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.util.Mu;

import java.io.Serializable;
import java.util.List;

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

    public static Atom fromSExpression(List<SExpression> sexp) throws JRettsError {

        if ( sexp.size() != 3 ) {
            throw new JRettsError("Atom Expression must be of lenght 3.");
        }

        Atom.AtomBuilder aBuilder = new Atom.AtomBuilder();

        SExpression s = sexp.get(0);

        if (s.isVariable()) {
            aBuilder.s(s.asVariableExp().var());
        } else if (s.isConstant()) {
            aBuilder.s(s.asConstantExp().constant());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant in name position.", s);
        }

        SExpression p = sexp.get(1);

        if (p.isVariable()) {
            aBuilder.p(p.asVariableExp().var());
        } else if (p.isConstant()) {
            aBuilder.p(p.asConstantExp().constant());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant in p position.", s);
        }

        SExpression o = sexp.get(2);

        if (o.isVariable()) {
            aBuilder.o(o.asVariableExp().var());
        } else if (o.isConstant()) {
            aBuilder.o(o.asConstantExp().constant());
        } else if (o.isLiteral()) {
            aBuilder.o(o.asLiteralExp().lit());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant or Literals in o position.", s);
        }

        return aBuilder.build();

    }
}
