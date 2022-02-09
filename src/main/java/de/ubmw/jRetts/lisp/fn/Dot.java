package de.ubmw.jRetts.lisp.fn;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.vocabulary.Atom;
import de.ubmw.jRetts.vocabulary.Literal;

import java.util.List;

/**
 * Function that interprets the parameters as an {@link Atom}.
 */
public class Dot implements  LispFunction {

    @Override
    public String symbol() {
        return ".";
    }

    @Override
    public Literal eval(SExpression.FunctionExp self, Env env) throws JRettsError {
        Atom a = atomFromSExpression(self.params());
        if (env.ruleEval) {
            // -- query -- //
            env.omega.join(env.database.query(a));
        } else {
            // -- insert -- //
            env.database.add(a);
        }

        return Literal.newNil();
    }

    @Override
    public Literal.LiteralType typeCheck(SExpression.FunctionExp self, Env env) throws JRettsError {
        // -- test if params are actual convertable to an atom -- //
        atomFromSExpression(self.params());
        return Literal.LiteralType.NIL;
    }

    public static Atom atomFromSExpression(List<SExpression> sexp) throws JRettsError {

        if ( sexp.size() != 3 ) {
            throw new JRettsError("Atom Expression must be of lenght 3.");
        }

        Atom.AtomBuilder aBuilder = new Atom.AtomBuilder();

        SExpression s = sexp.get(0);

        if (s.isVariable()) {
            aBuilder.s(s.asVariableExp().var());
        } else if (s.isConstant()) {
            aBuilder.s(s.asConstantExp().constant());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant in name position.", s);
        }

        SExpression p = sexp.get(0);

        if (p.isVariable()) {
            aBuilder.p(p.asVariableExp().var());
        } else if (p.isConstant()) {
            aBuilder.p(p.asConstantExp().constant());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant in p position.", s);
        }

        SExpression o = sexp.get(0);

        if (o.isVariable()) {
            aBuilder.o(o.asVariableExp().var());
        } else if (o.isConstant()) {
            aBuilder.o(o.asConstantExp().constant());
        } else if (o.isLiteral()) {
            aBuilder.o(o.asLiteralExp().lit());
        } else {
            throw new JRettsError(
                    "Dot function only accepts Variable or Constant or Literals in o position.", s);
        }

        return aBuilder.build();

    }

}