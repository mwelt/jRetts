package de.ubmw.jRetts;

import de.ubmw.jRetts.lisp.SExpression;

public class JRettsError extends Throwable {

	private Throwable wrappedThrowable = null;
	
	private String msg = "";
	
	private SExpression sexp = null;
	
	public JRettsError(String msg, Throwable wrappedThrowable) {
		this.wrappedThrowable = wrappedThrowable;
		this.msg = msg;
	}
	
	public JRettsError(String msg) {
		this.msg = msg;
	}

	public JRettsError(String msg, SExpression sexp) {
		this.msg = msg;
		this.sexp = sexp;
	}
	
	public Throwable getWrappedThrowable() {
		return this.wrappedThrowable;
	}
	
	public String getMsg() {
		return this.msg;
	}

}
