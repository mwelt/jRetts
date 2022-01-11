package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.parser.Parser;

import java.io.Serializable;

import static de.ubmw.jRetts.vocabulary.Term.TermType.CONSTANT;

public interface Term extends Serializable {

    static Term T(String s) {
        if (s.startsWith(":")) {
            return new Constant(s.substring(1));
        } else if (s.startsWith("?")) {
            return new Variable(s.substring(1));
        } else {
            return new Literal.StringLit(s);
        }
    }

    static Term T(double d) {
        return new Literal.DoubleLit(d);
    }

    static Term t(long l) {
        return new Literal.LongLit(l);
    }

    static Term t(boolean b) {
        return new Literal.BoolLit(b);
    }

    static Term t(Literal ...l) {
        return new Literal.ArrayLit(l);
    }

    enum TermType {
        VARIABLE, CONSTANT, LITERAL;
    }

    boolean isVariable();
    boolean isConstant();
    boolean isLiteral();

    Variable asVariable() throws JRettsError;
    Constant asConstant() throws JRettsError;
    Literal asLiteral() throws JRettsError;

    TermType getTermType();

    boolean equals(Term t) throws JRettsError;

    record Constant(String s) implements Term {

        @Override
        public boolean isVariable() {
            return false;
        }

        @Override
        public boolean isConstant() {
            return true;
        }

        @Override
        public boolean isLiteral() {
            return false;
        }

        @Override
        public Variable asVariable() throws JRettsError {
            throw new JRettsError("Can not return Constant as Variable.");
        }

        @Override
        public Constant asConstant() {
            return this;
        }

        @Override
        public Literal asLiteral() throws JRettsError {
            throw new JRettsError("Can not return Constant as Literal.");
        }

        @Override
        public TermType getTermType() {
            return CONSTANT;
        }

        @Override
        public String toString() {
            return ":" + s;
        }

        public boolean equals(Term term) throws JRettsError {
            if (! term.isConstant()) {
                return false;
            }
            return term.asConstant().s().equals(s);
        }
    }

    record Variable(String s) implements Term {
        @Override
        public boolean isVariable() {
            return true;
        }

        @Override
        public boolean isConstant() {
            return false;
        }

        @Override
        public boolean isLiteral() {
            return false;
        }

        @Override
        public Variable asVariable() throws JRettsError {
           return this;
        }

        @Override
        public Constant asConstant() throws JRettsError {
            throw new JRettsError("Can not return Variable as Constant.");
        }

        @Override
        public Literal asLiteral() throws JRettsError {
            throw new JRettsError("Can not return Variable as Literal.");
        }

        @Override
        public TermType getTermType() {
            return TermType.VARIABLE;
        }

        @Override
        public String toString() {
            return "?" + s;
        }

        public boolean equals(Term term) throws JRettsError {
            if (! term.isVariable()) {
                return false;
            }
            return term.asVariable().s().equals(s);
        }
    }


    // -- Literal external definition -- //

}
