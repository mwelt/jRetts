package de.ubmw.jRetts.lisp;

import java.util.Map;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.store.Store;
import de.ubmw.jRetts.vocabulary.Literal;
import de.ubmw.jRetts.vocabulary.Literal.LiteralType;

public class Env {

	private Map<String, Literal> vars;
	
	public Literal lastValue;
	
	public LiteralType lastType;

	public Store store;

	public boolean inRule = false;
	
	public Literal resolve(String name) throws JRettsError {
		if (vars.containsKey(name)) {
			return vars.get(name);
		}
		
		throw new JRettsError("Unknown variable name " + name + " .");
	}

}
