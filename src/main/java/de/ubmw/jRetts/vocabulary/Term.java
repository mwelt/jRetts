package de.ubmw.jRetts.vocabulary;

import de.ubmw.jRetts.JRettsError;

import static de.ubmw.jRetts.vocabulary.Term.TermType.CONSTANT;

public interface Term {

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
    }


    // -- Literal external definition -- //

}
