package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.datalog.Atom;
import de.ubmw.jRetts.datalog.Literal;

/**
 * Function that interprets the parameters as an {@link Atom}.
 */
public class Dot implements LispFunction {

    @Override
    public String symbol() {
        return ".";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        Atom a = Atom.fromSExpression(self.params());
        env.database.add(a);
        return Literal.newNil();
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        // -- test if params are actual convertable to an atom -- //
        Atom a = Atom.fromSExpression(self.params());
        return Literal.LiteralType.NIL;
    }


}