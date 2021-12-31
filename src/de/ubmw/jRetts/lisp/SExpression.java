package de.ubmw.jRetts.lisp;

import java.util.List;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.U;
import de.ubmw.jRetts.lisp.Literal.LiteralType;
import de.ubmw.jRetts.lisp.fn.LispFunction;

public interface SExpression {
	
	enum SExpressionE {
		FUNCTION,
		VARIABLE,
		LITERAL;
	}

	public SExpressionE getSexpType();
	
	public boolean isFunction();
	public boolean isVariable();
	public boolean isLiteral();
	
	public String toString(int indent);
	
	public Literal eval(Env env) throws JRettsError; 
	public LiteralType typeCheck(Env env) throws JRettsError;

	public record LiteralExp(Literal lit, int line, int col) implements SExpression {

		@Override
		public SExpressionE getSexpType() { return SExpressionE.LITERAL; }

		@Override
		public Literal eval(Env env) throws JRettsError { 
			return this.lit; 
		}

		@Override
		public LiteralType typeCheck(Env env) throws JRettsError {
			return this.lit.getType();
		};

		@Override
		public boolean isFunction() {
			return false;
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return true;
		}
		
		@Override
		public String toString(int i) {
			return U.indent(i) + this.lit.toString();
		}

	}
	
	public record VariableExp(String name, int line, int col) implements SExpression {

		@Override
		public SExpressionE getSexpType() {
			return SExpressionE.VARIABLE;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			return env.resolve(this.name);
		}

		@Override
		public LiteralType typeCheck(Env env) throws JRettsError {
			return env.resolve(this.name).getType();
		};


		@Override
		public boolean isFunction() {
			return false;
		}

		@Override
		public boolean isVariable() {
			return true;
		}

		@Override
		public boolean isLiteral() {
			return false;
		}
		
		@Override
		public String toString(int i) {
			return U.indent(i) + this.name;
		}
		
	}
	
	public record FunctionExp(LispFunction fn, List<SExpression> params, int line, int col) implements SExpression {

		@Override
		public SExpressionE getSexpType() {
			return SExpressionE.FUNCTION;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			return fn.eval(this.params, env);
		}

		@Override
		public LiteralType typeCheck(Env env) throws JRettsError {
			return fn.typeCheck(this.params, env);
		}
		

		@Override
		public boolean isFunction() {
			return true;
		}

		@Override
		public boolean isVariable() {
			return false;
		}

		@Override
		public boolean isLiteral() {
			return false;
		}
		
		@Override
		public String toString(final int indent) {
			StringBuilder b = new StringBuilder();
			b.append(U.indent(indent));
			b.append("(" + this.fn.symbol() + "\n");
			b.append(String.join(" ", this.params.stream().map(s -> s.toString(indent + 1)).toArray(String[]::new)));
			b.append("\n");
			b.append(U.indent(indent));
			b.append(")");
			return b.toString();
		}

	}

}
