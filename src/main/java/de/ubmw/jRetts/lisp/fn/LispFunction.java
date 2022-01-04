package de.ubmw.jRetts.lisp.fn;

import java.util.List;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.Literal;
import de.ubmw.jRetts.lisp.Literal.LiteralType;
import de.ubmw.jRetts.lisp.SExpression;

public interface LispFunction {
	
	public String symbol();
	
	public Literal eval(List<SExpression> params, Env env) throws JRettsError;
	
	public LiteralType typeCheck(List<SExpression> params, Env env) throws JRettsError; 

}
