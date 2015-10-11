package at.stefl.commons.test;

import org.junit.Test;

import at.stefl.commons.util.collection.InverseSet;

public class InverseSetTest {

    @Test
    public void should_testName() throws Exception {
        final InverseSet<String> infiniteSet = new InverseSet<String>();

        System.out.println(infiniteSet.inverseSet().size());
        System.out.println(infiniteSet);
        System.out.println();

        final String a = "asdf";
        System.out.println(a);
        System.out.println(infiniteSet.contains(a));
        System.out.println();

        infiniteSet.removeElement("asdf");
        System.out.println(infiniteSet.inverseSet().size());
        System.out.println(infiniteSet);
        System.out.println();

        System.out.println(a);
        System.out.println(infiniteSet.contains(a));
        System.out.println();

        infiniteSet.add("asdf");
        System.out.println(infiniteSet.inverseSet().size());
        System.out.println(infiniteSet);
        System.out.println();

        System.out.println(a);
        System.out.println(infiniteSet.contains(a));
        System.out.println();
    }

}