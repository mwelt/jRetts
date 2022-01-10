package de.ubmw.jRetts.store;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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

}