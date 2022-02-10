package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.datalog.Literal.LiteralType;

public interface LispFunction {
	
	String symbol();
	
	Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError;
	
	LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError;

}
