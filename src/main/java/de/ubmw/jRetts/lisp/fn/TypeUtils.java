package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.vocabulary.Literal.LiteralType;

import java.util.List;

public class TypeUtils {

	public static LiteralType listOfNumbers(List<SExpression> params, Env env) throws JRettsError {
		boolean isFirstDouble = isFirstElementDouble(params, env);
		
		for(SExpression s : params) {
			LiteralType t = s.typeCheck(env);
			if(! ((isFirstDouble && t == LiteralType.DOUBLE) || (! isFirstDouble && t == LiteralType.LONG))) {
				throw new JRettsError("Function only accepts Double or Long values.", s);
			}
		}
		
		return isFirstDouble ? LiteralType.DOUBLE : LiteralType.LONG;
	}
	
	public static boolean isFirstElementDouble(List<SExpression> l, Env env) throws JRettsError {
		return l.get(0).typeCheck(env) == LiteralType.DOUBLE;
	}
}
