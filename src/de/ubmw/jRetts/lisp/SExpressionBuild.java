package de.ubmw.jRetts.lisp;

import java.util.Arrays;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.SExpression.LiteralExp;
import de.ubmw.jRetts.lisp.SExpression.FunctionExp;
import de.ubmw.jRetts.lisp.fn.LispFunction;
import de.ubmw.jRetts.lisp.fn.LispFunctionE;

public class SExpressionBuild {
	
	public static SExpression F(String fnSymb, SExpression ...exps) throws JRettsError {
		LispFunction fn = LispFunctionE.bySymbol(fnSymb);
		return new FunctionExp(fn, Arrays.asList(exps), 0, 0);
	}
	
	public static LiteralExp LL(long l) throws JRettsError { 
		return new LiteralExp(Literal.newLongLit(l), 0, 0);
	}
	
	public static LiteralExp LD(double d) throws JRettsError {
		return new LiteralExp(Literal.newDoubleLit(d), 0, 0);
	}
	
	public static LiteralExp LS(String s) throws JRettsError {
		return new LiteralExp(Literal.newStringLit(s), 0, 0);
	}
	
	public static LiteralExp LAr(LiteralExp ...lts) throws JRettsError {
		Literal[] ls = new Literal[lts.length];
	
		for(int i = 0; i < lts.length; i++) {
			ls[i] = lts[i].lit();
		}
		
		return new LiteralExp(Literal.newArrayLit(ls), 0, 0);
	}

}
