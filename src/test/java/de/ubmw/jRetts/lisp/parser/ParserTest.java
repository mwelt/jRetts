package de.ubmw.jRetts.lisp.parser;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.parser.Parser;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void simpleTest() {
        String code = """
                (+ 1 (- 3 2))
                """;
        try {
            Parser.parse(code);
        } catch(JRettsError err){
            err.printStackTrace();
        }
    }
}