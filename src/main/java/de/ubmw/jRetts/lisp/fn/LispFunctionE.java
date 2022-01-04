package de.ubmw.jRetts.lisp.fn;

import java.util.HashMap;
import java.util.Map;

import de.ubmw.jRetts.JRettsError;

public enum LispFunctionE {

	PLUS(new Plus()),

	MINUS(new Minus());
	
	private static Map<String, LispFunction> STR2FN = new HashMap<String, LispFunction>();

	static {
		for(LispFunctionE v : LispFunctionE.values()) {
			LispFunctionE.STR2FN.put(v.fn.symbol(), v.fn);
		}
	};
	
	public static LispFunction bySymbol(String symbol) throws JRettsError {
		if(STR2FN.containsKey(symbol)) {
			return STR2FN.get(symbol);
		}
		
		throw new JRettsError("Unknown function symbol \"" + symbol + "\".");
	}

	private LispFunction fn = null;

	private LispFunctionE(LispFunction fn) {
		this.fn = fn;
	}
	
	public LispFunction getFn() {
		return this.fn;
	}

}
