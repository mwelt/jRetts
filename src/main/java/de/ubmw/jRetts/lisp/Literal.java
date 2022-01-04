package de.ubmw.jRetts.lisp;

import java.util.Arrays;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.U;

public interface Literal {

	public static Literal newNil() {
		return new NilLit();
	}
	
	public static Literal newDoubleLit(double d) {
		return new DoubleLit(d);
	}
	
	public static Literal newLongLit(long l) {
		return new LongLit(l);
	}
	
	public static Literal newStringLit(String s) throws JRettsError{
		U.nonNull(s, "s");
		return new StringLit(s);
	}
	
	public static Literal newArrayLit(Literal[] ar) throws JRettsError {
		U.nonNull(ar, "ar");
		return new ArrayLit(ar);
	}

	public enum LiteralType {
		NIL, LONG, DOUBLE, STRING, ARRAY;
	};
	
	public LiteralType getType();
	
	public boolean isNil();
	public boolean isLong();
	public boolean isDouble();
	public boolean isString();
	public boolean isArray();

	public class NilLit implements Literal {
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
		public String toString() {
			return "nil";
		}
	}

	public record LongLit(long l) implements Literal {
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
		public String toString() {
			return Long.toString(this.l);
		}
	};

	public record DoubleLit(double d) implements Literal {
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
		public String toString() {
			return Double.toString(this.d);
		}
	};

	public record StringLit(String s) implements Literal {
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
		public String toString() {
			return "\"" + this.s + "\"";
		}
	};

	public record ArrayLit(Literal[] a) implements Literal {
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
		public String toString() {
			StringBuffer b = new StringBuffer();
			b.append("[");
			String.join(", ", Arrays.stream(this.a).map(l -> l.toString()).toArray(String[]::new));
			b.append("]");
			return b.toString();
		}
	};
}
