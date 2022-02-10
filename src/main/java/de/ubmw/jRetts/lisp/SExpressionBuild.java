package de.ubmw.jRetts.lisp;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.datalog.Term;
import de.ubmw.jRetts.lisp.SExpression.FunctionExp;
import de.ubmw.jRetts.lisp.SExpression.LiteralExp;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.lisp.fn.LispFunctionE;

import java.util.Arrays;

public class SExpressionBuild {
	
	public static SExpression.FunctionExp F(String fnSymb, SExpression ...exps) throws JRettsError {
		return LispFunctionE.bySymbol(fnSymb)
				.map(fn -> new FunctionExp(fn, Arrays.asList(exps), 0, 0))
				.orElseThrow(() ->
						new JRettsError("Unknown function symbol \"" + fnSymb + "\"."));
	}

	public static SExpression.ConstantExp C(String c) {
		return new SExpression.ConstantExp(new Term.Constant(c.startsWith(":") ? c.substring(1) : c), 0, 0);
	}

	public static SExpression.VariableExp V(String v) {
		return new SExpression.VariableExp(new Term.Variable(v.startsWith("?") ? v.substring(1) : v), 0, 0);
	}

	public static LiteralExp LL(long l) throws JRettsError { 
		return new LiteralExp(Literal.newLongLit(l), 0, 0);
	}
	
	public static LiteralExp LD(double d) {
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
