package de.ubmw.jRetts.lisp.parser;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.SExpression;
import de.ubmw.jRetts.lisp.fn.LispFunction;
import de.ubmw.jRetts.lisp.fn.LispFunctionE;
import de.ubmw.jRetts.util.ArrayDequeStack;
import de.ubmw.jRetts.util.Stack;
import de.ubmw.jRetts.datalog.Literal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.*;
import java.util.function.Function;

import static de.ubmw.jRetts.lisp.SExpression.*;
import static de.ubmw.jRetts.lisp.parser.Parser.State.*;
import static de.ubmw.jRetts.datalog.Literal.*;
import static de.ubmw.jRetts.datalog.Literal.LiteralType.NIL;
import static de.ubmw.jRetts.datalog.Literal.LiteralType.STRING;

public class Parser {

    private static final Logger logger = LoggerFactory.getLogger("parser");

    public static int MAX_SYMBOL_LENGTH = 254;

    // --------------------------------------- //
    //             parser states               //
    // --------------------------------------- //

    enum State {
        ALIST(Parser::alistf),
        AUP(Parser::asymbolf),
        ADOWN(Parser::asymbolf),
        ASTRING(Parser::astringf),
        ACOMMENT(Parser::acommentf),
        ASYMBOL(Parser::asymbolf),
        AARRUP(Parser::asymbolf),
        AARRDOWN(Parser::asymbolf);

        private final Function<Character, State> transition;

        State(Function<Character, State> transition) {
            this.transition = transition;
        }

        State apply(Character c) {
            return this.transition.apply(c);
        }
    };

    // --------------------------------------- //
    //         parser transfer functions       //
    // --------------------------------------- //

    private static State alistf(int c){
        if(c == '('){ return ADOWN; }
        if(c == ')'){ return AUP; }
        if(c == '[') { return AARRDOWN; }
        if(c == ']') { return AARRUP; }
        if(c == ';') { return ACOMMENT; }
        if(c == ' ') { return ALIST; }
        if(c == '\t') { return ALIST; }
        if(c == '\n') { return ALIST; }
        if(c == '\"') { return ASTRING; }
        return ASYMBOL;
    }

    private static State astringf(int c){
        if(c == '\"') { return ALIST; }
        return ASTRING;
    }

    private static State acommentf(int c){
        if(c == '\n') { return ALIST; }
        return ACOMMENT;
    }

    private static State asymbolf(int c){
        if(c == '(') { return ADOWN; }
        if(c == ')') { return AUP; }
        if(c == '[') { return AARRDOWN; }
        if(c == ']') { return AARRUP; }
        if(c == ' ') { return ALIST; }
        if(c == '\t') { return ALIST; }
        if(c == '\n') { return ALIST; }
        return ASYMBOL;
    }

    // --------------------------------------- //
    //                the parser               //
    // --------------------------------------- //

    public static SExpression parse(Reader reader) throws JRettsError, ParserError {

        // ---------- //
        // initialize //
        // ---------- //

        int cpos = 0, cline = 0;
        FunctionExp cSExp = new FunctionExp(LispFunctionE.DO,new ArrayList<>(), -1, cpos);

        State cstate = ADOWN;
        State lstate = ADOWN;

        CharBuffer cBuf = CharBuffer.allocate(MAX_SYMBOL_LENGTH);

        boolean first = false;
        boolean inArray = false;
        List<Literal> cArray = Collections.emptyList();
        LiteralType cArrayType = NIL;

        Stack<FunctionExp> stack = new ArrayDequeStack<>();

        int c;

        // -------------- //
        // automaton loop //
        // -------------- //

        try {
            while ((c = reader.read()) != -1) {


                // -- increment line and position -- //

                if(c == '\n'){
                    cline++;
                    cpos = 0;
                }
                cpos++;


                // -- state transition -- //

                lstate = cstate;
                cstate = cstate.apply((char) c);

                logger.debug("'%c': %name -> %name".formatted(c, lstate.name(), cstate.name()));

                // -- handle symbols and strings -- //

                if(cstate == ASYMBOL || cstate == ASTRING){
                    if(lstate != cstate) {
                        cBuf.flip().clear();
                    }
                    cBuf.append((char) c);
                    continue;
                }

                // -- handle functions -- //

                if(lstate != cstate && lstate == ASYMBOL && first){
                    String fnSymbol = cBuf.flip().toString();
                    Optional<LispFunctionE> fn = LispFunctionE.bySymbol(fnSymbol);
                    if(fn.isEmpty()) {
                        throw new ParserError("Unknown function symbol \"" + fnSymbol + "\".", cline, cpos);
                    }
                    cSExp = new FunctionExp(fn.get(), new ArrayList<>(), cline, cpos);
                    first = false;
                }


                // -- handle parameters -- //

                else if (lstate != cstate && lstate == ASYMBOL) {
                    String paraSymbol = cBuf.flip().toString();
                    SExpression paraExp = null;

                    if(paraSymbol.charAt(0) == '?') {

                        // -- a variable -- //
                        if (inArray) {
                            throw new ParserError("Variables are not allowed inside arrays.", cline, cpos);
                        }
                        paraExp = new VariableExp(new Variable(paraSymbol.substring(1)), cline, cpos);

                    } else if(paraSymbol.charAt(0) == ':') {

                        // -- a constant -- //
                       paraExp = new ConstantExp(new Constant(paraSymbol.substring(1)), cline, cpos);

                    } else {

                        // -- a literal -- //
                        Literal lit;

                        // -- a number -- //
                        Scanner s = new Scanner(paraSymbol);

                        if (s.hasNextLong()) {
                            lit = new LongLit(s.nextLong());
                            if (s.hasNext()) {
                                throw new ParserError("Something went wrong while parsing symbol as Long \"" + paraSymbol + "\".", cline, cpos);
                            }
                        } else if (s.hasNextDouble()) {
                            lit = new DoubleLit(s.nextDouble());
                            if (s.hasNext()) {
                                throw new ParserError("Something went wrong while parsing symbol as Double \"" + paraSymbol + "\".", cline, cpos);
                            }
                        } else {
                            throw new ParserError("Can not determine type of symbol \"" + paraSymbol + "\".", cline, cpos);
                        }

                        // -- handle a literal inside an array -- //
                        if(inArray){
                            if(cArrayType == NIL){ cArrayType = lit.getLiteralType(); }
                            if(cArrayType == lit.getLiteralType()){
                                cArray.add(lit);
                            }else{
                                throw new ParserError("Arrays must be of single type only.", cline, cpos);
                            }
                        } else {
                            paraExp = new LiteralExp(lit, cline, cpos);
                        }
                    }

                    // -- add the parameter to the current function   -- //
                    // -- but only if there'name no array context active -- //
                    if(! inArray) { cSExp.params().add(paraExp); }

                } // <-- var, constant or number


                // -- handle strings that are ready -- //

                else if(lstate != cstate && lstate == ASTRING){
                    cBuf.append((char) c);
                    Literal lit = new StringLit(cBuf.flip().toString());

                    if(inArray){
                        if(cArrayType == NIL){ cArrayType = STRING; }
                        if(cArrayType == STRING){
                            cArray.add(lit);
                        }else{
                            throw new ParserError("Arrays must be of single type only.", cline, cpos);
                        }
                    } else {
                        cSExp.params().add(new LiteralExp(lit, cline, cpos));
                    }
                    continue;
                }


                // -- array opening bracket -- //

                if(lstate != cstate && cstate == AARRDOWN){

                    if(inArray){
                        throw new ParserError("Nested arrays are not allowed.", cline, cpos);
                    }

                    inArray = true;
                    cstate = ALIST;
                    cArray = new ArrayList<>();
                    continue;
                }


                // -- array closing bracket -- //

                if(lstate != cstate && cstate == AARRUP){

                    if(! inArray){
                        throw new ParserError("Unbalanced parentheses.", cline, cpos);
                    }

                    cSExp.params().add(new LiteralExp(new ArrayLit(cArray.toArray(new Literal[0])), cline, cpos));

                    inArray = false;
                    cArray = Collections.emptyList();

                    cstate = ALIST;
                    continue;
                }


                // -- list opening bracket -- //

                if(cstate == ADOWN){

                    if(inArray){
                        throw new ParserError("Symbol not allowed here (List inside an array).", cline, cpos);
                    }

                    // -- if the first "parameter" is a list -- //
                    // -- then it defaults to do operation   -- //
                    if(first){
                        cSExp = new FunctionExp(LispFunctionE.DO,new ArrayList<>(), cline, cpos);
                    }

                    stack.push(cSExp);

                    first = true;
                    cstate = ALIST;
                    continue;
                }


                // -- list closing bracket -- //

                if(cstate == AUP){

                    if(inArray){
                        throw new ParserError("Symbol not allowed here (List inside an array).", cline, cpos);
                    }

                    if(stack.size() == 0){
                        throw new ParserError("Unbalanced parentheses.", cline, cpos);
                    }

                    // -- add current SExp as param to the parent lisp -- //
                    FunctionExp parentExp = stack.pop();
                    parentExp.params().add(cSExp);

                    cSExp = parentExp;
                    cstate = ALIST;
                }

            } // <-- while loop


            if(stack.size() != 0){
                throw new ParserError("Unbalanced parentheses.", cline, cpos);
            }

        } catch (IOException iox) {
            throw new JRettsError("Unable to parse data. See wrapped Exception.", iox);
        }

        return cSExp;
    }
	
}
