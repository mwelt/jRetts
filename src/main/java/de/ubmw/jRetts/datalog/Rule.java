package de.ubmw.jRetts.datalog;

import de.ubmw.jRetts.lisp.fn.LispFunctionE;

import java.util.ArrayList;
import java.util.List;

import static de.ubmw.jRetts.lisp.SExpression.*;

public class Rule {

    // -- Lisp Expression of this Rule -- //
    private final FunctionExp ruleExp;

    // -- a list of Delta Rule Expression -- //
    private final List<FunctionExp> deltaRuleExps;

    public Rule(FunctionExp ruleExp) {
        this.ruleExp = new FunctionExp(LispFunctionE.DO.getFn(), ruleExp.params(), ruleExp.line(), ruleExp.col());
        this.deltaRuleExps = toDeltaRules();
    }

    private List<FunctionExp> toDeltaRules() {
        List<FunctionExp> deltaRules = new ArrayList<>();
        return null;
    }
}
