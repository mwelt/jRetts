package de.ubmw.jRetts.lisp;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.database.Database;
import de.ubmw.jRetts.util.Omega;
import de.ubmw.jRetts.datalog.Literal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Env {

	private Map<String, Literal> vars;

	public Database database;

	public boolean ruleEval = false;

	public List<SExpression> rules;

	public Omega omega;

	public Env(Database database) {
		vars = new HashMap<>();
		this.database = database;
		rules = new ArrayList<>();
	}

	public Literal resolve(String name) throws JRettsError {
		if (vars.containsKey(name)) {
			return vars.get(name);
		}
		
		throw new JRettsError("Unknown variable name " + name + " .");
	}

}