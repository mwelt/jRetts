package de.ubmw.jRetts.store;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.vocabulary.Atom;
import de.ubmw.jRetts.vocabulary.Term;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.ubmw.jRetts.vocabulary.Atom.A;
import static de.ubmw.jRetts.vocabulary.Term.T;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    public void test() throws JRettsError {

        Store store = new Store();

        store.add(A(T(":mwelt"), T(":hasName"), T("Michael Welt")));
        store.add(A(T(":mwelt"), T(":hasAge"), T(40L)));
        store.add(A(T(":jwelt"), T(":hasName"), T("Johanna Welt")));
        store.add(A(T(":jwelt"), T(":hasAge"), T(37L)));

        store.dump(System.out);


        List<Atom> res = store.query(A(T("?who"), T(":hasName"), T("?name")));

        for (Atom a : res) {
            System.out.println(a.toString());
        }

        res = store.query(A(T(":mwelt"), T(":hasName"), T("?name")));

        for (Atom a : res) {
            System.out.println(a.toString());
        }

        res = store.query(A(T(":mwelt"), T("?p"), T("?o")));

        for (Atom a : res) {
            System.out.println(a.toString());
        }

        res = store.query(A(T("?who"), T(":hasAge"), T(40L)));

        for (Atom a : res) {
            System.out.println(a.toString());
        }

    }

}