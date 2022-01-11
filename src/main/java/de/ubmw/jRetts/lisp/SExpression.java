package de.ubmw.jRetts.lisp;

import java.util.List;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.vocabulary.Atom;
import de.ubmw.jRetts.vocabulary.Literal;
import de.ubmw.jRetts.util.U;
import de.ubmw.jRetts.lisp.fn.LispFunction;
import de.ubmw.jRetts.vocabulary.Term.*;

public interface SExpression {
	
	enum SExpressionType {
		FUNCTION,
		VARIABLE,
		CONSTANT,
		LITERAL,
		ATOM,
		RULE;
	}

	SExpressionType getType();
	
	boolean isFunction();
	boolean isVariable();
	boolean isLiteral();
	boolean isConstant();
	boolean isAtom();
	boolean isRule();
	
	String toString(int indent);
	
	Literal eval(Env env) throws JRettsError;
	Literal.LiteralType typeCheck(Env env) throws JRettsError;

	record LiteralExp(Literal lit, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() { return SExpressionType.LITERAL; }

		@Override
		public Literal eval(Env env) throws JRettsError { 
			return this.lit; 
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			return this.lit.getLiteralType();
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
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isAtom() {
			return false;
		}

		@Override
		public boolean isRule() {
			return false;
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + this.lit.toString();
		}

	}
	
	record VariableExp(Variable var, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() {
			return SExpressionType.VARIABLE;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			return env.resolve(this.var.s());
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			return env.resolve(this.var.s()).getLiteralType();
		}


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
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isAtom() {
			return false;
		}

		@Override
		public boolean isRule() {
			return false;
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + this.var.toString();
		}
		
	}

	record ConstantExp(Constant constant, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() {
			return SExpressionType.CONSTANT;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			throw new JRettsError("Constants are not allowed to be evaluated directly.");
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			throw new JRettsError("Constants are not allowed to be evaluated directly.");
		}

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
			return false;
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public boolean isAtom() {
			return false;
		}

		@Override
		public boolean isRule() {
			return false;
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + constant.toString();
		}

	}

	record FunctionExp(LispFunction fn, List<SExpression> params, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() {
			return SExpressionType.FUNCTION;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			return fn.eval(this.params, env);
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
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
		public boolean isConstant() {
			return false;
		}

		@Override
		public boolean isAtom() {
			return false;
		}

		@Override
		public boolean isRule() {
			return false;
		}

		@Override
		public String toString(final int indent) {
			return U.indent(indent) +
					"(" + this.fn.symbol() + "\n" +
					String.join(" ", this.params.stream().map(s -> s.toString(indent + 1)).toArray(String[]::new)) +
					"\n" +
					U.indent(indent) +
					")";
		}

	}

	record AtomExp(Atom atom, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() {
			return SExpressionType.ATOM;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			throw new JRettsError("Constants are not allowed to be evaluated directly.");
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			throw new JRettsError("Constants are not allowed to be evaluated directly.");
		}

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
			return false;
		}

		@Override
		public boolean isConstant() {
			return true;
		}

		@Override
		public boolean isAtom() {
			return true;
		}

		@Override
		public boolean isRule() {
			return false;
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + atom.toString();
		}

	}
}
