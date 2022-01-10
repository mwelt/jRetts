package de.ubmw.jRetts.vocabulary;

import java.util.Arrays;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.util.U;

public interface Literal extends Term {

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
		U.nonNull(s, "s");
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
			return false;
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
	}

}
