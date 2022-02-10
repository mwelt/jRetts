package de.ubmw.jRetts.datalog;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.SExpression;
import org.junit.jupiter.api.Test;

import static de.ubmw.jRetts.lisp.SExpressionBuild.*;

class RuleTest {

    @Test
    public void test() throws JRettsError {

        SExpression.FunctionExp ruleExp =
                F(":-",
                        F(".", V("?who"), C(":hasName"), V("?name")),
                        F(".", V("?who"), C(":hasAge"), LL(40)));

        System.out.println("Original:");
        System.out.println(ruleExp);
        System.out.println("\n\nDelta Rule Decomposition:");
        Rule r = new Rule(ruleExp);
        for(SExpression.FunctionExp deltaRule : r.getDeltaRuleExps()) {
            System.out.println(deltaRule.toString(0));
        }

    }

}