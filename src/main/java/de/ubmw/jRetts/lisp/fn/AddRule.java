package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Literal;

public class AddRule implements LispFunction {

    @Override
    public String symbol() {
        return ":-";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        env.rules.add(self);
        return Literal.newNil();
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        return Literal.LiteralType.NIL;
    }
}
