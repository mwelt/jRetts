package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;

public class Bind implements LispFunction {
    @Override
    public String symbol() {
        return "bind";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        throw new JRettsError("Not implemented yet.");
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        throw new JRettsError("Not implemented yet.");
    }
}
