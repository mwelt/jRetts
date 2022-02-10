package de.ubmw.jRetts.lisp;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.database.Database;
import de.ubmw.jRetts.datalog.Literal;
import org.junit.jupiter.api.Test;

import static de.ubmw.jRetts.lisp.SExpressionBuild.F;
import static de.ubmw.jRetts.lisp.SExpressionBuild.LL;
import static org.junit.jupiter.api.Assertions.fail;

class SExpressionBuildTest {

	@Test
	void test() {
		try {
			Env env = new Env(new Database());
			SExpression sexp = F("+", LL(1), F("-", LL(3), LL(5)));
			System.out.println(sexp.toString(0));
			sexp.typeCheck(env);
			Literal erg = sexp.eval(env);
			System.out.println("? = " + erg.toString());
		} catch (JRettsError err) {
			fail(err.getMsg());
		}
	}

}
