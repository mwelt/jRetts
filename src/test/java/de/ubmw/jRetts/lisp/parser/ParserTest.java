package de.ubmw.jRetts.lisp.parser;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.Env;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.lisp.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void simpleTest() {
        String code = """
                (+ 1 (- 3 2))
                """;
        try {
            SExpression prog = Parser.parse(new StringReader(code));
            System.out.println(prog.toString(0));
            System.out.println(prog.eval(new Env()).toString());
        } catch(JRettsError err) {
            err.printStackTrace();
            fail();
        } catch(ParserError perr) {
            System.err.printf("Parser Error (%d, %d): %name%n", perr.getLine(), perr.getPosition(), perr.getMsg());
            perr.printStackTrace();
            fail();
        }
    }
}