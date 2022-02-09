package de.ubmw.jRetts.database;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ComitRevertListTest {

    @Test
    public void test() {
        ComitRevertList<String> crl = new ComitRevertList<>();

        crl.add("Hello");
        crl.add("The");
        crl.add("World!");

        crl.commit();

        crl.add("This");
        crl.add("is");
        crl.add("a");
        crl.add("fancy");
        crl.add("list.");

        crl.commit();

        crl.add("With");
        crl.add("multiple");
        crl.add("versions!");

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));

        crl.revert();

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));

        crl.revert();

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));

        crl.revert();

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));
    }

    @Test
    public void testJoin() {

        ComitRevertList<String> crl = new ComitRevertList<>();

        crl.add("Hello");
        crl.add("The");
        crl.add("World!");

        crl.commit();

        crl.add("This");
        crl.add("is");
        crl.add("a");
        crl.add("fancy");
        crl.add("list.");

        crl.commit();

        crl.add("With");
        crl.add("multiple");
        crl.add("versions!");

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));

        crl.join(2);

        System.out.println(Arrays.toString(crl.toArray()));
        System.out.println(Arrays.toString(crl.getVersionStack().toArray()));
    }
}