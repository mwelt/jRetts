package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.datalog.Literal;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;

public class BgpDelta implements LispFunction {
    @Override
    public String symbol() {
        return "bgp+";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        env.omega.join(env.database.query(self.params().get(0).asAtomExp().atom(), true));
        return Literal.newNil();
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        if(self.params().size() != 1 || ! self.params().get(0).isAtom()) {
            throw new JRettsError("Single AtomExp expected.");
        }

        return Literal.LiteralType.NIL;
    }
}
