package de.ubmw.jRetts.lisp.fn;


public enum LispFunctionE {

	PLUS(new Plus()),

	MINUS(new Minus());

	private LispFunction fn = null;

	private LispFunctionE(LispFunction fn) {
		this.fn = fn;
	}
	
	public LispFunction getFn() {
		return this.fn;
	}
}
