package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Literal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum LispFunctionE {

	NOOP(new LispFunction() {
		@Override
		public String symbol() {
			return null;
		}

		@Override
		public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
			return new Literal.NilLit();
		}

		@Override
		public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
			return Literal.LiteralType.NIL;
		}
	}),

	DO(new Do()),
	PLUS(new Plus()),
	MINUS(new Minus());
	
	private static final Map<String, LispFunction> STR2FN = new HashMap<String, LispFunction>();

	static {
		for(LispFunctionE v : LispFunctionE.values()) {
			LispFunctionE.STR2FN.put(v.fn.symbol(), v.fn);
		}
	};
	
	public static Optional<LispFunction> bySymbol(String symbol) {
		if(STR2FN.containsKey(symbol)) {
			return Optional.of(STR2FN.get(symbol));
		}

		return Optional.empty();
	}

	private LispFunction fn = null;

	private LispFunctionE(LispFunction fn) {
		this.fn = fn;
	}
	
	public LispFunction getFn() {
		return this.fn;
	}

}
