package de.ubmw.jRetts.lisp.fn;

import java.util.List;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.vocabulary.Literal;
import de.ubmw.jRetts.vocabulary.Literal.LiteralType;
import de.ubmw.jRetts.lisp.SExpression;

public class Minus implements LispFunction {

	@Override
	public Literal eval(List<SExpression> params, Env env) throws JRettsError {
		return EvalUtils.foldListOfNumbers(params, env, 
				(Double x, Double y) -> x - y,
				(Long x, Long y) -> x - y);
	}

	@Override
	public LiteralType typeCheck(List<SExpression> params, Env env) throws JRettsError {
		return TypeUtils.listOfNumbers(params, env);
	}
	
	@Override
	public String symbol() {
		return "-";
	}


}
