package de.ubmw.jRetts;

import de.ubmw.jRetts.lisp.SExpression;

public class JRettsError extends Throwable {

	private static final long serialVersionUID = -2448799473074669038L;

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
