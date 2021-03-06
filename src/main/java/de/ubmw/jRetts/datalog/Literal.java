package de.ubmw.jRetts.datalog;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.util.U;

import java.io.Serializable;
import java.util.Arrays;

public interface Literal extends Term, Serializable {

	static Literal newNil() {
		return new NilLit();
	}
	
	static Literal newDoubleLit(double d) {
		return new DoubleLit(d);
	}
	
	static Literal newLongLit(long l) {
		return new LongLit(l);
	}
	
	static Literal newStringLit(String s) throws JRettsError{
		U.nonNull(s, "name");
		return new StringLit(s);
	}
	
	static Literal newArrayLit(Literal[] ar) throws JRettsError {
		U.nonNull(ar, "ar");
		return new ArrayLit(ar);
	}

	static Literal newBoolLit(boolean b) {
		return new BoolLit(b);
	}

	enum LiteralType {
		NIL, LONG, DOUBLE, STRING, ARRAY, BOOL;
	};
	
	LiteralType getLiteralType();
	
	boolean isNil();
	boolean isLong();
	boolean isDouble();
	boolean isString();
	boolean isArray();
	boolean isBool();

	long asLong() throws JRettsError;
	double asDouble() throws JRettsError;
	String asString() throws JRettsError;
	Literal[] asArray() throws JRettsError;
	boolean asBool() throws JRettsError;

	class NilLit implements Literal {

		@Override
		public LiteralType getLiteralType() { return LiteralType.NIL; }

		@Override
		public boolean isNil() { return true; }

		@Override
		public boolean isLong() { return false; }

		@Override
		public boolean isDouble() { return false; }

		@Override
		public boolean isString() { return false; }

		@Override
		public boolean isArray() { return false; }

		@Override
		public boolean isBool() {
			return false;
		}

		@Override
		public long asLong() throws JRettsError {
			throw new JRettsError("Can not return Nil as Long.");
		}

		@Override
		public double asDouble() throws JRettsError{
			throw new JRettsError("Can not return Nil as Double.");
		}

		@Override
		public String asString() throws JRettsError {
			throw new JRettsError("Can not return Nil as String.");
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			throw new JRettsError("Can not return Nil as Array.");
		}

		@Override
		public boolean asBool() throws JRettsError {
			throw new JRettsError("Can not return Nil as Bool.");
		}

		@Override
		public String toString() {
			return "nil";
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public NilLit() {
			super();
		}

		public boolean equals(Term t) throws JRettsError {
			return t.isLiteral() && t.asLiteral().isNil();
		}
	}

	record LongLit(long l) implements Literal {
		@Override
		public LiteralType getLiteralType() { return LiteralType.LONG; }

		@Override
		public boolean isNil() {
			return false;
		}

		@Override
		public boolean isLong() { return true; }

		@Override
		public boolean isDouble() { return false; }

		@Override
		public boolean isString() { return false; }

		@Override
		public boolean isArray() { return false; }

		@Override
		public boolean isBool() {
			return false;
		}

		@Override
		public long asLong() throws JRettsError {
			return l;
		}

		@Override
		public double asDouble() throws JRettsError {
			throw new JRettsError("Can not return Long as Double.");
		}

		@Override
		public String asString() throws JRettsError {
			throw new JRettsError("Can not return Long as String.");
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			throw new JRettsError("Can not return Long as Array.");
		}

		@Override
		public boolean asBool() throws JRettsError {
			throw new JRettsError("Can not return Long as Bool.");
		}

		@Override
		public String toString() {
			return Long.toString(this.l);
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public boolean equals(Term t) throws JRettsError {
			return t.isLiteral() && t.asLiteral().isLong() && t.asLiteral().asLong() == this.l;
		}
	}

	record DoubleLit(double d) implements Literal {
		@Override
		public LiteralType getLiteralType() { return LiteralType.DOUBLE; }

		@Override
		public boolean isNil() { return false; }

		@Override
		public boolean isLong() { return false; }

		@Override
		public boolean isDouble() { return true; }

		@Override
		public boolean isString() { return false; }

		@Override
		public boolean isArray() { return false; }

		@Override
		public boolean isBool() {
			return false;
		}

		@Override
		public long asLong() throws JRettsError {
			throw new JRettsError("Can not return Double as Long.");
		}

		@Override
		public double asDouble() throws JRettsError {
			return d;
		}

		@Override
		public String asString() throws JRettsError {
			throw new JRettsError("Can not return Double as String.");
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			throw new JRettsError("Can not return Double as Array.");
		}

		@Override
		public boolean asBool() throws JRettsError {
			throw new JRettsError("Can not return Double as Bool.");
		}

		@Override
		public String toString() {
			return Double.toString(this.d);
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public boolean equals(Term t) throws JRettsError {
			return t.isLiteral() && t.asLiteral().isDouble() && t.asLiteral().asDouble() == this.d;
		}
	}

	record StringLit(String s) implements Literal {

		@Override
		public LiteralType getLiteralType() { return LiteralType.STRING; }

		@Override
		public boolean isNil() { return false; }

		@Override
		public boolean isLong() { return false; }

		@Override
		public boolean isDouble() { return false; }

		@Override
		public boolean isString() { return true; }

		@Override
		public boolean isArray() { return false; }

		@Override
		public boolean isBool() {
			return false;
		}

		@Override
		public long asLong() throws JRettsError {
			throw new JRettsError("Can not return String as Long.");
		}

		@Override
		public double asDouble() throws JRettsError {
			throw new JRettsError("Can not return String as Double.");
		}

		@Override
		public String asString() throws JRettsError {
			return s;
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			throw new JRettsError("Can not return String as Array.");
		}

		@Override
		public boolean asBool() throws JRettsError {
			throw new JRettsError("Can not return String as Bool.");
		}

		@Override
		public String toString() {
			return "\"" + this.s + "\"";
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public boolean equals(Term t) throws JRettsError {
			return t.isLiteral() && t.asLiteral().isString() && t.asLiteral().asString().equals(this.s);
		}
	}

	record BoolLit(boolean b) implements Literal {

		@Override
		public LiteralType getLiteralType() { return LiteralType.BOOL; }

		@Override
		public boolean isNil() { return false; }

		@Override
		public boolean isLong() { return false; }

		@Override
		public boolean isDouble() { return false; }

		@Override
		public boolean isString() { return false; }

		@Override
		public boolean isArray() { return false; }

		@Override
		public boolean isBool() {
			return true;
		}

		@Override
		public long asLong() throws JRettsError {
			throw new JRettsError("Can not return Bool as Long.");
		}

		@Override
		public double asDouble() throws JRettsError {
			throw new JRettsError("Can not return Bool as Double.");
		}

		@Override
		public String asString() throws JRettsError {
			throw new JRettsError("Can not return Bool as String.");
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			throw new JRettsError("Can not return Bool as Array.");
		}

		@Override
		public boolean asBool() throws JRettsError {
			return this.b;
		}

		@Override
		public String toString() {
			return Boolean.toString(b);
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public boolean equals(Term t) throws JRettsError {
			return t.isLiteral() && t.asLiteral().isBool() && t.asLiteral().asBool() == this.b;
		}
	}

	record ArrayLit(Literal[] a) implements Literal {

		@Override
		public LiteralType getLiteralType() { return LiteralType.ARRAY; }

		@Override
		public boolean isNil() { return false; }

		@Override
		public boolean isLong() { return false; }

		@Override
		public boolean isDouble() { return false; }

		@Override
		public boolean isString() { return false; }

		@Override
		public boolean isArray() { return true; }

		@Override
		public boolean isBool() {
			return false;
		}

		@Override
		public long asLong() throws JRettsError {
			throw new JRettsError("Can not return Array as Long.");
		}

		@Override
		public double asDouble() throws JRettsError {
			throw new JRettsError("Can not return Array as Double.");
		}

		@Override
		public String asString() throws JRettsError {
			throw new JRettsError("Can not return Array as String.");
		}

		@Override
		public Literal[] asArray() throws JRettsError {
			return this.a;
		}

		@Override
		public boolean asBool() throws JRettsError {
			throw new JRettsError("Can not return Array as Bool.");
		}

		@Override
		public String toString() {
			return "[" +
					String.join(", ",
							Arrays.stream(this.a)
									.map(Object::toString)
									.toArray(String[]::new)) +
					"]";
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}

		@Override
		public Variable asVariable() throws JRettsError {
			throw new JRettsError("Can not return Literal as Variable.");
		}

		@Override
		public Constant asConstant() throws JRettsError {
			throw new JRettsError("Can not return Literal as Constant.");
		}

		@Override
		public Literal asLiteral() throws JRettsError {
			return this;
		}

		@Override
		public TermType getTermType() {
			return TermType.LITERAL;
		}

		public boolean equals(Term t) throws JRettsError {
			if (! t.isLiteral()) {
				return false;
			}

			Literal lit = t.asLiteral();

			if (! lit.isArray() || lit.asArray().length != this.a.length) {
				return false;
			}

			for (int i = 0; i < this.a.length; i++) {
				if (! lit.asArray()[i].equals(this.a[i])) {
					return false;
				}
			}

			return true;
		}

	}

}
