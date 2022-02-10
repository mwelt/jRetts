package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Literal;

public class Do implements LispFunction {
    @Override
    public String symbol() {
        return "do";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        Literal l = new Literal.NilLit();
        for (var param : self.params()) { l = param.eval(env); }
        return l;
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        Literal.LiteralType t = Literal.LiteralType.NIL;
        for (var param : self.params()) { t = param.typeCheck(env); }
        return t;
    }
}
