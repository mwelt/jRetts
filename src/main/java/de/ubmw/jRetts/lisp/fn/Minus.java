package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.vocabulary.Literal;
import de.ubmw.jRetts.vocabulary.Literal.LiteralType;

public class Minus implements LispFunction {

	@Override
	public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
		return EvalUtils.foldListOfNumbers(self.params(), env,
				(Double x, Double y) -> x - y,
				(Long x, Long y) -> x - y);
	}

	@Override
	public LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
		return TypeUtils.listOfNumbers(self.params(), env);
	}
	
	@Override
	public String symbol() {
		return "-";
	}

}
