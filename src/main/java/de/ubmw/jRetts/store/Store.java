package de.ubmw.jRetts.store;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.lisp.parser.Parser;
import de.ubmw.jRetts.vocabulary.Atom;
import de.ubmw.jRetts.vocabulary.Term.*;

import java.io.PrintStream;
import java.util.*;

public class Store {

    public void dump(PrintStream out) {
        for (Constant c : index.keySet()) {
            out.println(c.toString());
        }

        for (Atom a : data) {
            out.println(a.toString());
        }
    }

    private static record IndexNode(Constant c, ComitRevertList<Integer> pos, ComitRevertList<Integer> sps, ComitRevertList<Integer> sos) {
        IndexNode(Constant c) {
            this(c, new ComitRevertList<>(5), new ComitRevertList<>(5), new ComitRevertList<>(5));
        }
    }

    private final ComitRevertList<Atom> data;

    private final Map<Constant, IndexNode> index;

    public Store() {
        data = new ComitRevertList<>();
        index = new HashMap<>();
    }

    // TODO syncronize
    public void add(Atom atom) throws JRettsError {
        if (! atom.isGround()) {
            throw new JRettsError("Only ground atoms are allowed in the store.");
        }

        if ( ! (atom.s().isConstant() && atom.p().isConstant())) {
            throw new JRettsError("s and p positions must be constant.");
        }

        int idx = data.size();
        data.add(atom);

        IndexNode sINode = index.computeIfAbsent(atom.s().asConstant(), IndexNode::new);
        sINode.pos().add(idx);

        IndexNode pINode = index.computeIfAbsent(atom.p().asConstant(), IndexNode::new);
        pINode.sos().add(idx);

        if (atom.o().isConstant()) {
            IndexNode oINode = index.computeIfAbsent(atom.o().asConstant(), IndexNode::new);
            pINode.sps().add(idx);
        }
    }

    public List<Atom> query(Atom query) throws JRettsError {

        if (!(query.s().isVariable() || query.s().isConstant())
                && (query.p().isVariable() || query.p().isConstant())
                && (query.o().isConstant() || query.o().isVariable() || query.o().isLiteral())) {
            throw new JRettsError("Unsupported query format.");
        }

        if (query.s().isVariable() && query.p().isVariable() && query.o().isVariable()) {
            // -- ?s ?p ?o -- //
            return querySPO(query);
        } else if (query.s().isVariable() && query.p().isVariable() && (query.o().isConstant() || query.o().isLiteral())) {
            // -- ?s ?p :o / 1.234 -- //
            return querySP(query);
        } else if (query.s().isVariable() && query.p().isConstant() && (query.o().isConstant() || query.o().isLiteral())) {
            // -- ?s :p :o / 1.234 -- //
            return queryS(query);
        } else if (query.s().isConstant() && query.p().isVariable() && (query.o().isConstant() || query.o().isLiteral())) {
            // -- :s ?p :o / 1.234 -- //
            return queryP(query);
        } else if (query.s().isConstant() && query.p().isConstant() &&  query.o().isVariable()) {
            // -- :s :p ?o -- //
            return queryO(query);
        } else if (query.s().isConstant() && query.p().isVariable() && query.o().isVariable()) {
            // -- :s ?p ?o -- //
            return queryPO(query);
        } else if (query.s().isVariable() && query.p().isConstant() && query.o().isVariable()) {
            // -- ?s :p ?o -- //
            return querySO(query);
        } else if (query.s().isConstant() && query.p().isConstant() && (query.o().isConstant() || query.o().isLiteral())) {
            // -- :s :p :o / 1.234 -- //
            return queryConst(query);
        }

        throw new JRettsError("Unsupported query format.");

    }

    // -- :s :p :o / 1.234 -- //
    private List<Atom> queryConst(Atom query) throws JRettsError {
        if (! (index.containsKey(query.s().asConstant()) &&
                index.containsKey(query.p().asConstant()))) {
            return Collections.emptyList();
        }
        LinkedList<Atom> result = new LinkedList<>();
        IndexNode sINode = index.get(query.s().asConstant());
        for (Integer i : sINode.pos()) {
            Atom a = data.get(i);
            if (a.p().equals(query.p()) && a.o().equals(query.o())) {
                result.add(a);
            }
        }
        return result;
    }

    // -- ?s :p ?o -- //
    private List<Atom> querySO(Atom query) throws JRettsError {
        if (! index.containsKey(query.p().asConstant())) {
            return Collections.emptyList();
        }
        LinkedList<Atom> result = new LinkedList<>();
        IndexNode pINode = index.get(query.p().asConstant());
        for (Integer i : pINode.sos()) {
            Atom a = data.get(i);
            result.add(a);
        }
        return result;
    }

    // -- :s ?p ?o -- //
    private List<Atom> queryPO(Atom query) throws JRettsError {
        if (! index.containsKey(query.s().asConstant())) {
            return Collections.emptyList();
        }
        LinkedList<Atom> result = new LinkedList<>();
        IndexNode sINode = index.get(query.s().asConstant());
        for (Integer i : sINode.pos()) {
            Atom a = data.get(i);
            result.add(a);
        }
        return result;
    }

    // -- :s :p ?o -- //
    private List<Atom> queryO(Atom query) throws JRettsError {
        if (! (index.containsKey(query.s().asConstant()) &&
                index.containsKey(query.p().asConstant()))) {
            return Collections.emptyList();
        }
        LinkedList<Atom> result = new LinkedList<>();
        IndexNode sINode = index.get(query.s().asConstant());
        for (Integer i : sINode.pos()) {
            Atom a = data.get(i);
            if (a.p().equals(query.p())) {
                result.add(a);
            }
        }
        return result;
    }

    // -- query ?s ?p :o / 1.234 -- //
    private List<Atom> querySP(Atom query) throws JRettsError {
        if (! query.o().isConstant()) {
            throw  new JRettsError("Queries with only literals in o-position are not allowed.");
        }
        if (! index.containsKey(query.o().asConstant())) {
            return Collections.emptyList();
        }
        IndexNode oINode = index.get(query.o().asConstant());
        LinkedList<Atom> result = new LinkedList<>();
        for (Integer i : oINode.sps()) {
            Atom a = data.get(i);
            result.add(a);
        }
        return result;
    }

    // -- query ?s :p :o / 1.234 -- //
    private List<Atom> queryS(Atom query) throws JRettsError {
        if (! index.containsKey(query.p().asConstant())) {
            return Collections.emptyList();
        }
        IndexNode pINode = index.get(query.p().asConstant());
        LinkedList<Atom> result = new LinkedList<>();
        for (Integer i : pINode.sos()) {
            Atom a = data.get(i);
            if (a.o().equals(query.o())) {
                result.add(a);
            }
        }
        return result;
    }

    // -- query :s ?p :o / 1.234 -- //
    private List<Atom> queryP(Atom query) throws JRettsError {
        if (! index.containsKey(query.s().asConstant())) {
            return Collections.emptyList();
        }
        IndexNode sINode = index.get(query.s().asConstant());
        LinkedList<Atom> result = new LinkedList<>();
        for (Integer i : sINode.pos()) {
            Atom a = data.get(i);
            if (a.o().equals(query.o())) {
                result.add(a);
            }
        }
        return result;
    }

    // -- do not allow ?s ?p ?o -- //
    private List<Atom> querySPO(Atom query) throws JRettsError {
        throw  new JRettsError("Queries with variables in all three positions are not allowed.");
    }

}
