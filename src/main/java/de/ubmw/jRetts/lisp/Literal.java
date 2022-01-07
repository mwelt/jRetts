package de.ubmw.jRetts.lisp;

import java.util.Arrays;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.U;

public interface Literal {

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

	enum LiteralType {
		NIL, LONG, DOUBLE, STRING, ARRAY, CONSTANT;
	};
	
	LiteralType getType();
	
	boolean isNil();
	boolean isLong();
	boolean isDouble();
	boolean isString();
	boolean isArray();
	boolean isConstant();

	class NilLit implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.NIL; }

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
		public boolean isConstant() {
			return false;
		}

		@Override
		public String toString() {
			return "nil";
		}
	}

	record LongLit(long l) implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.LONG; }

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
		public boolean isConstant() {
			return false;
		}

		@Override
		public String toString() {
			return Long.toString(this.l);
		}
	}

	record DoubleLit(double d) implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.DOUBLE; }

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
		public boolean isConstant() {
			return false;
		}

		@Override
		public String toString() {
			return Double.toString(this.d);
		}
	}

	record StringLit(String s) implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.STRING; }

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
		public boolean isConstant() {
			return false;
		}

		@Override
		public String toString() {
			return "\"" + this.s + "\"";
		}
	}

	record ConstantLit(String s) implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.CONSTANT; }

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
		public boolean isConstant() {
			return true;
		}

		@Override
		public String toString() {
			return ":" + this.s + "";
		}
	}

	record ArrayLit(Literal[] a) implements Literal {
		@Override
		public LiteralType getType() { return LiteralType.ARRAY; }

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
		public boolean isConstant() {
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
	}
}
