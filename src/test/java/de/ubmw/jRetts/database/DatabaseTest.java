package de.ubmw.jRetts.database;

import de.ubmw.jRetts.JRettsError;
import de.ubmw.jRetts.util.Mu;
import de.ubmw.jRetts.util.Omega;
import de.ubmw.jRetts.vocabulary.Atom;
import org.junit.jupiter.api.Test;

import static de.ubmw.jRetts.vocabulary.Atom.A;
import static de.ubmw.jRetts.vocabulary.Term.T;

class DatabaseTest {

    @Test
    public void test() throws JRettsError {

        Database database = new Database();

        database.add(A(T(":mwelt"), T(":hasName"), T("Michael Welt")));
        database.add(A(T(":mwelt"), T(":hasAge"), T(40L)));
        database.add(A(T(":jwelt"), T(":hasName"), T("Johanna Welt")));
        database.add(A(T(":jwelt"), T(":hasAge"), T(37L)));

        database.dump(System.out);

        Atom bgp = A(T("?who"), T(":hasName"), T("?name"));
        Omega res = database.query(bgp);

        System.out.println(bgp + ":");
        for (Mu m : res) {
            System.out.println(m.toString());
        }

        bgp = A(T(":mwelt"), T(":hasName"), T("?name"));
        res = database.query(bgp);

        System.out.println(bgp + ":");
        for (Mu m : res) {
            System.out.println(m.toString());
        }

        bgp = A(T(":mwelt"), T("?p"), T("?o"));
        res = database.query(bgp);

        System.out.println(bgp + ":");
        for (Mu m : res) {
            System.out.println(m.toString());
        }

        bgp = A(T("?who"), T(":hasAge"), T(40L));
        res = database.query(bgp);

        System.out.println(bgp + ":");
        for (Mu m : res) {
            System.out.println(m.toString());
        }

    }

    @Test
    public void testJoin() throws JRettsError {

        Database database = new Database();

        database.add(A(T(":mwelt"), T(":hasName"), T("Michael Welt")));
        database.add(A(T(":mwelt"), T(":hasAge"), T(40L)));
        database.add(A(T(":jwelt"), T(":hasName"), T("Johanna Welt")));
        database.add(A(T(":jwelt"), T(":hasAge"), T(37L)));

        Atom bgp = A(T("?who"), T(":hasName"), T("?name"));
        Omega res1 = database.query(bgp);

        bgp = A(T("?who"), T(":hasAge"), T(37L));
        Omega res2 = database.query(bgp);

        Omega res3 = res1.join(res2);

        for (Mu m : res3) {
            System.out.println(m.toString());
        }
    }

}