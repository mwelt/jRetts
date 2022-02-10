package de.ubmw.jRetts.datalog;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.lisp.fn.LispFunctionE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static de.ubmw.jRetts.lisp.SExpression.*;

public class Rule {

    // -- Lisp Expression of this Rule -- //
    private final FunctionExp ruleExp;

    // -- a list of Delta Rule Expression -- //
    private final List<FunctionExp> deltaRuleExps;

    public Rule(FunctionExp ruleExp) throws JRettsError {
        this.ruleExp = new FunctionExp(LispFunctionE.DO, ruleExp.params(),
                ruleExp.line(), ruleExp.col());
        this.deltaRuleExps = decomposeRules();
    }

    private List<FunctionExp> decomposeRules() throws JRettsError {

        // **
        // -- first pass -- //
        // **

        int nBgp = 0;
        for (ListIterator<SExpression> it = ruleExp.params().listIterator(); it.hasNext(); ) {
            SExpression sexp = it.next();
            if (sexp.isFunction() && sexp.asFunctionExp().fn() == LispFunctionE.DOT) {
                nBgp++;
                // -- replace the Dot function with a Bgp function, -- //
                // -- and combine all parameters into an AtomExpression -- //
                SExpression s = sexp.asFunctionExp().params().get(0);
                it.set(new FunctionExp(LispFunctionE.BGP,
                        Arrays.asList(new SExpression[]{
                                new AtomExp(Atom.fromSExpression(sexp.asFunctionExp().params()),
                                        s.line(), s.col())}),
                        sexp.line(), sexp.col()
                ));
            }
        }

        // **
        // -- second pass
        // -- create all the delta rule variations -- //
        // **
        List<FunctionExp> deltaRules = new ArrayList<>();

        for (int i = 0; i < nBgp; i++) {
            List<SExpression> params = new ArrayList<>();

            int k = 0;
            for (SExpression sexp : ruleExp.params()) {
                if (sexp.isFunction() && sexp.asFunctionExp().fn() == LispFunctionE.BGP && k == i) {
                    // -- the new delta atom -- //
                    params.add(new FunctionExp(LispFunctionE.BGP_DELTA,
                            sexp.asFunctionExp().params(), sexp.line(), sexp.col()));
                } else {
                    // -- just add the expression -- //
                    params.add(sexp);
                }
                k++;
            }
            deltaRules.add(new FunctionExp(LispFunctionE.DO,
                    params, ruleExp.line(), ruleExp.col()));
        }

        return deltaRules;
    }

     public List<FunctionExp> getDeltaRuleExps() {
        return deltaRuleExps;
    }
}
