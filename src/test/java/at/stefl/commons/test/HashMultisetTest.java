package at.stefl.commons.test;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

import at.stefl.commons.util.collection.HashMultiset;
import at.stefl.commons.util.collection.Multiset;

public class HashMultisetTest {

    @Test
    public void should_testName() throws Exception {
        final Multiset<String> multiset = new HashMultiset<String>();

        System.out.println(multiset.size());
        System.out.println(multiset);
        System.out.println();

        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");

        System.out.println(multiset.size());
        System.out.println(multiset);
        System.out.println();

        multiset.remove("a");
        multiset.remove("c");

        System.out.println(multiset.size());
        System.out.println(multiset);
        System.out.println();

        multiset.add("a");
        multiset.add("c");
        multiset.add("a");

        System.out.println(multiset.size());
        System.out.println(multiset);
        System.out.println();

        multiset.removeAll(Arrays.asList(new String[] { "a", "b" }));

        System.out.println(multiset.size());
        System.out.println(multiset);
        System.out.println();

        final Multiset<String> multiset2 = new HashMultiset<String>();
        multiset2.add("a");
        multiset2.add("c");

        System.out.println(multiset.equals(multiset2));
        System.out.println();

        final Iterator<String> iterator = multiset.iterator();
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(multiset);
        System.out.println(multiset.size());
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(multiset);
        System.out.println(multiset.size());
    }

}