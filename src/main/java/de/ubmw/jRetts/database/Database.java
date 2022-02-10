package de.ubmw.jRetts.database;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.util.Mu;
import de.ubmw.jRetts.util.Omega;
import de.ubmw.jRetts.datalog.Atom;
import de.ubmw.jRetts.datalog.Term.Constant;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Database {

    public void dump(PrintStream out) {
        for (Constant c : index.keySet()) {
            out.println(c.toString());
        }

        for (Atom a : data) {
            out.println(a.toString());
        }
    }

    private record IndexNode(Constant c, ComitRevertList<Integer> pos, ComitRevertList<Integer> sps,
                             ComitRevertList<Integer> sos) {
        IndexNode(Constant c) {
            this(c, new ComitRevertList<>(5), new ComitRevertList<>(5), new ComitRevertList<>(5));
        }
    }

    private final ComitRevertList<Atom> data;

    private final Map<Constant, IndexNode> index;

    public Database() {
        data = new ComitRevertList<>();
        index = new HashMap<>();
    }

    // TODO syncronize
    public void add(Atom atom) throws JRettsError {
        if (!atom.isGround()) {
            throw new JRettsError("Only ground atoms are allowed in the store.");
        }

        if (!(atom.s().isConstant() && atom.p().isConstant())) {
            throw new JRettsError("name and p positions must be constant.");
        }

        int idx = data.size();
        data.add(atom);

        IndexNode sINode = index.computeIfAbsent(atom.s().asConstant(), IndexNode::new);
        sINode.pos().add(idx);

        IndexNode pINode = index.computeIfAbsent(atom.p().asConstant(), IndexNode::new);
        pINode.sos().add(idx);

        if (atom.o().isConstant()) {
            IndexNode oINode = index.computeIfAbsent(atom.o().asConstant(), IndexNode::new);
            oINode.sps().add(idx);
        }
    }

    public Omega query(Atom bgp) throws JRettsError {
        return query(bgp, false);
    }

    public Omega query(Atom bgp, boolean delta) throws JRettsError {

        if (!(bgp.s().isVariable() || bgp.s().isConstant())
                && (bgp.p().isVariable() || bgp.p().isConstant())
                && (bgp.o().isConstant() || bgp.o().isVariable() || bgp.o().isLiteral())) {
            throw new JRettsError("Unsupported query format.");
        }

        if (bgp.s().isVariable() && bgp.p().isVariable() && bgp.o().isVariable()) {
            // -- ?name ?p ?o -- //
            return querySPO(bgp, delta);
        } else if (bgp.s().isVariable() && bgp.p().isVariable() && (bgp.o().isConstant() || bgp.o().isLiteral())) {
            // -- ?name ?p :o / 1.234 -- //
            return querySP(bgp, delta);
        } else if (bgp.s().isVariable() && bgp.p().isConstant() && (bgp.o().isConstant() || bgp.o().isLiteral())) {
            // -- ?name :p :o / 1.234 -- //
            return queryS(bgp, delta);
        } else if (bgp.s().isConstant() && bgp.p().isVariable() && (bgp.o().isConstant() || bgp.o().isLiteral())) {
            // -- :name ?p :o / 1.234 -- //
            return queryP(bgp, delta);
        } else if (bgp.s().isConstant() && bgp.p().isConstant() && bgp.o().isVariable()) {
            // -- :name :p ?o -- //
            return queryO(bgp, delta);
        } else if (bgp.s().isConstant() && bgp.p().isVariable() && bgp.o().isVariable()) {
            // -- :name ?p ?o -- //
            return queryPO(bgp, delta);
        } else if (bgp.s().isVariable() && bgp.p().isConstant() && bgp.o().isVariable()) {
            // -- ?name :p ?o -- //
            return querySO(bgp, delta);
        } else if (bgp.s().isConstant() && bgp.p().isConstant() && (bgp.o().isConstant() || bgp.o().isLiteral())) {
            // -- :name :p :o / 1.234 -- //
            return queryConst(bgp, delta);
        }

        throw new JRettsError("Unsupported query format.");

    }

    // -- :name :p :o / 1.234 -- //
    private Omega queryConst(Atom bgp, boolean delta) throws JRettsError {

        throw new JRettsError("Queries with constants in all three positions are not allowed.");

//        if (! (index.containsKey(query.s().asConstant()) &&
//                index.containsKey(query.p().asConstant()))) {
//            return Collections.emptyList();
//        }
//        LinkedList<Atom> result = new LinkedList<>();
//        IndexNode sINode = index.get(query.s().asConstant());
//        for (Integer i : sINode.pos()) {
//            Atom a = data.get(i);
//            if (a.p().equals(query.p()) && a.o().equals(query.o())) {
//                result.add(a);
//            }
//        }
//        return result;
    }

    // -- ?name :p ?o -- //
    private Omega querySO(Atom bgp, boolean delta) throws JRettsError {
        if (!index.containsKey(bgp.p().asConstant())) {
            return Omega.emptyOmega();
        }
        Omega result = new Omega();
        IndexNode pINode = index.get(bgp.p().asConstant());
        for (Integer i : delta ? pINode.sos().delta() : pINode.sos()) {
            Atom a = data.get(i);
            Mu m = new Mu();
            m.put(bgp.s().asVariable(), a.s());
            m.put(bgp.o().asVariable(), a.o());
            result.add(m);
        }
        return result;
    }

    // -- :name ?p ?o -- //
    private Omega queryPO(Atom bgp, boolean delta) throws JRettsError {
        if (!index.containsKey(bgp.s().asConstant())) {
            return Omega.emptyOmega();
        }
        Omega result = new Omega();
        IndexNode sINode = index.get(bgp.s().asConstant());
        for (Integer i : delta ? sINode.pos().delta() : sINode.pos()) {
            Atom a = data.get(i);
            Mu m = new Mu();
            m.put(bgp.p().asVariable(), a.p());
            m.put(bgp.o().asVariable(), a.o());
            result.add(m);
        }
        return result;
    }

    // -- :name :p ?o -- //
    private Omega queryO(Atom bgp, boolean delta) throws JRettsError {
        if (!(index.containsKey(bgp.s().asConstant()) &&
                index.containsKey(bgp.p().asConstant()))) {
            return Omega.emptyOmega();
        }
        Omega result = new Omega();
        IndexNode sINode = index.get(bgp.s().asConstant());
        for (Integer i : delta ? sINode.pos().delta() : sINode.pos()) {
            Atom a = data.get(i);
            if (a.p().equals(bgp.p())) {
                Mu m = new Mu();
                m.put(bgp.o().asVariable(), a.o());
                result.add(m);
            }
        }
        return result;
    }

    // -- query ?name ?p :o / 1.234 -- //
    private Omega querySP(Atom bgp, boolean delta) throws JRettsError {
        if (!bgp.o().isConstant()) {
            throw new JRettsError("Queries with only literals in o-position are not allowed.");
        }
        if (!index.containsKey(bgp.o().asConstant())) {
            return Omega.emptyOmega();
        }
        IndexNode oINode = index.get(bgp.o().asConstant());
        Omega result = new Omega();
        for (Integer i : delta ? oINode.sps().delta() : oINode.sps()) {
            Atom a = data.get(i);
            Mu m = new Mu();
            m.put(bgp.s().asVariable(), a.s());
            m.put(bgp.p().asVariable(), a.p());
            result.add(m);
        }
        return result;
    }

    // -- query ?name :p :o / 1.234 -- //
    private Omega queryS(Atom bgp, boolean delta) throws JRettsError {
        if (!index.containsKey(bgp.p().asConstant())) {
            return Omega.emptyOmega();
        }
        IndexNode pINode = index.get(bgp.p().asConstant());
        Omega result = new Omega();
        for (Integer i : delta ? pINode.sos().delta() : pINode.sos()) {
            Atom a = data.get(i);
            if (a.o().equals(bgp.o())) {
                Mu m = new Mu();
                m.put(bgp.s().asVariable(), a.s());
                result.add(m);
            }
        }
        return result;
    }

    // -- query :name ?p :o / 1.234 -- //
    private Omega queryP(Atom bgp, boolean delta) throws JRettsError {
        if (!index.containsKey(bgp.s().asConstant())) {
            return Omega.emptyOmega();
        }
        IndexNode sINode = index.get(bgp.s().asConstant());
        Omega result = new Omega();
        for (Integer i : delta ? sINode.pos().delta() : sINode.pos()) {
            Atom a = data.get(i);
            if (a.o().equals(bgp.o())) {
                Mu m = new Mu();
                m.put(bgp.p().asVariable(), a.p());
                result.add(m);
            }
        }
        return result;
    }

    // -- do not allow ?name ?p ?o -- //
    private Omega querySPO(Atom bgp, boolean delta) throws JRettsError {
        throw new JRettsError("Queries with variables in all three positions are not allowed.");
    }

}
