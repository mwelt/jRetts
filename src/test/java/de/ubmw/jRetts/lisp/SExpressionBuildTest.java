package de.ubmw.jRetts.lisp;

import static org.junit.jupiter.api.Assertions.*;
import static de.ubmw.jRetts.lisp.SExpressionBuild.*;

import de.ubmw.jRetts.vocabulary.Literal;
import org.junit.jupiter.api.Test;

import de.ubmw.jRetts.JRettsError;

class SExpressionBuildTest {

	@Test
	void test() throws JRettsError {
		try {
			Env env = new Env();
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
