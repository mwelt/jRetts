package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.*;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.datalog.Literal.LiteralType;

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
