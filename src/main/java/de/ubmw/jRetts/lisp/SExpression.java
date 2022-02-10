package de.ubmw.jRetts.lisp;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.datalog.Atom;
import de.ubmw.jRetts.lisp.fn.LispFunctionE;
import de.ubmw.jRetts.util.U;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.datalog.Term.Constant;
import de.ubmw.jRetts.datalog.Term.Variable;

import java.util.List;

public interface SExpression {
	
	enum SExpressionType {
		FUNCTION,
		VARIABLE,
		CONSTANT,
		LITERAL,
		ATOM,
	}

	SExpressionType getType();
	
	boolean isFunction();
	boolean isVariable();
	boolean isLiteral();
	boolean isConstant();
	boolean isAtom();

	int line();
	int col();

	FunctionExp asFunctionExp() throws JRettsError;
	VariableExp asVariableExp() throws JRettsError;
	LiteralExp asLiteralExp() throws JRettsError;
	ConstantExp asConstantExp() throws JRettsError;
	AtomExp asAtomExp() throws JRettsError;

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
		public FunctionExp asFunctionExp() throws JRettsError {
			throw new JRettsError("Can not return LiteralExp as FunctionExp");
		}

		@Override
		public VariableExp asVariableExp() throws JRettsError {
			throw new JRettsError("Can not return LiteralExp as VariableExp");
		}

		@Override
		public LiteralExp asLiteralExp() {
			return this;
		}

		@Override
		public ConstantExp asConstantExp() throws JRettsError {
			throw new JRettsError("Can not return LiteralExp as ConstantExp");
		}

		@Override
		public AtomExp asAtomExp() throws JRettsError {
			throw new JRettsError("Can not return LiteralExp as AtomExp");
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
			return env.resolve(this.var.name());
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			return env.resolve(this.var.name()).getLiteralType();
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
		public FunctionExp asFunctionExp() throws JRettsError {
			throw new JRettsError("Can not return VariableExp as FunctionExp");
		}

		@Override
		public VariableExp asVariableExp() {
			return this;
		}

		@Override
		public LiteralExp asLiteralExp() throws JRettsError {
			throw new JRettsError("Can not return VariableExp as LiteralExp");
		}

		@Override
		public ConstantExp asConstantExp() throws JRettsError {
			throw new JRettsError("Can not return VariableExp as ConstantExp");
		}

		@Override
		public AtomExp asAtomExp() throws JRettsError {
			throw new JRettsError("Can not return VariableExp as AtomExp");
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
		public FunctionExp asFunctionExp() throws JRettsError {
			throw new JRettsError("Can not return ConstantExp as FunctionExp");
		}

		@Override
		public VariableExp asVariableExp() throws JRettsError {
			throw new JRettsError("Can not return ConstantExp as VariableExp");
		}

		@Override
		public LiteralExp asLiteralExp() throws JRettsError {
			throw new JRettsError("Can not return ConstantExp as LiteralExp");
		}

		@Override
		public ConstantExp asConstantExp() {
			return this;
		}

		@Override
		public AtomExp asAtomExp() throws JRettsError {
			throw new JRettsError("Can not return ConstantExp as AtomExp");
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + constant.toString();
		}

	}

	record FunctionExp(LispFunctionE fn, List<SExpression> params, int line, int col) implements SExpression {

		@Override
		public SExpressionType getType() {
			return SExpressionType.FUNCTION;
		}

		@Override
		public Literal eval(Env env) throws JRettsError {
			return fn.getFn().eval(this, env);
		}

		@Override
		public Literal.LiteralType typeCheck(Env env) throws JRettsError {
			return fn.getFn().typeCheck(this, env);
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
		public FunctionExp asFunctionExp() {
			return this;
		}

		@Override
		public VariableExp asVariableExp() throws JRettsError {
			throw new JRettsError("Can not return FunctionExp as VariableExp");
		}

		@Override
		public LiteralExp asLiteralExp() throws JRettsError {
			throw new JRettsError("Can not return FunctionExp as LiteralExp");
		}

		@Override
		public ConstantExp asConstantExp() throws JRettsError {
			throw new JRettsError("Can not return FunctionExp as ConstantExp");
		}

		@Override
		public AtomExp asAtomExp() throws JRettsError {
			throw new JRettsError("Can not return FunctionExp as AtomExp");
		}

		@Override
		public String toString(final int indent) {
			return U.indent(indent) +
					"(" + this.fn.getFn().symbol() + "\n" +
					String.join(" ", this.params.stream().map(s -> s.toString(indent + 1)).toArray(String[]::new)) +
					"\n" +
					U.indent(indent) +
					")";
		}

	}

	// -- currently is this constructed and not built by the parser -- //
	// -- TODO parser addon -- //
	record AtomExp(Atom atom, int line, int col) implements SExpression {

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
		public FunctionExp asFunctionExp() throws JRettsError {
			throw new JRettsError("Can not return AtomExp as FunctionExp");
		}

		@Override
		public VariableExp asVariableExp() throws JRettsError {
			throw new JRettsError("Can not return AtomExp as VariableExp");
		}

		@Override
		public LiteralExp asLiteralExp() throws JRettsError {
			throw new JRettsError("Can not return AtomExp as LiteralExp");
		}

		@Override
		public AtomExp asAtomExp() {
			return this;
		}

		@Override
		public ConstantExp asConstantExp() throws JRettsError {
			throw new JRettsError("Can not return AtomExp as ConstantExp");
		}

		@Override
		public String toString(int i) {
			return U.indent(i) + atom.toString();
		}

	}

}
