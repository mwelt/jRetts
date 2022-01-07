package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.Literal;
import de.ubmw.jRetts.lisp.SExpression;

import java.util.List;

public class Do implements LispFunction {
    @Override
    public String symbol() {
        return "do";
    }

    @Override
    public Literal eval(List<SExpression> params, Env env) throws JRettsError {
        for (var param : params) { param.eval(env); }
        return new Literal.NilLit();
    }

    @Override
    public Literal.LiteralType typeCheck(List<SExpression> params, Env env) throws JRettsError {
        for (var param : params) { param.typeCheck(env); }
        return Literal.LiteralType.NIL;
    }
}
