package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.vocabulary.Literal;
import de.ubmw.jRetts.lisp.SExpression;

import java.util.List;

public class Do implements LispFunction {
    @Override
    public String symbol() {
        return "do";
    }

    @Override
    public Literal eval(List<SExpression> params, Env env) throws JRettsError {
        Literal l = new Literal.NilLit();
        for (var param : params) { l = param.eval(env); }
        return l;
    }

    @Override
    public Literal.LiteralType typeCheck(List<SExpression> params, Env env) throws JRettsError {
        Literal.LiteralType t = Literal.LiteralType.NIL;
        for (var param : params) { t = param.typeCheck(env); }
        return t;
    }
}
