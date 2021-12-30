package de.ubmw.jRetts.lisp;

import java.util.List;

import de.ubmw.jRetts.JRettsError;
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
		
	}
	
	public record FunctIonExp(LispFunction fn, List<SExpression> params, int line, int col) implements SExpression {

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

	}

}
