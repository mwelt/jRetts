package de.ubmw.jRetts.lisp.parser;

public class ParserError extends Throwable {

    private String msg;
    private int line;
    private int position;
    private Throwable wrappedThrowable;

    public ParserError(String msg, int line, int position) {
        this.msg = msg;
        this.line = line;
        this.position = position;

    }
    public ParserError(String msg, int line, int position, Throwable wrappedThrowable) {
        this(msg, line, position);
        this.wrappedThrowable = wrappedThrowable;
    }

    public String getMsg() {
        return msg;
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }

    public Throwable getWrappedThrowable() {
        return wrappedThrowable;
    }
}
