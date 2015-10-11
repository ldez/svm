package at.stefl.commons;

import org.junit.Test;

import at.stefl.commons.util.collection.CharArrayQueue;

public class CharArrayQueueTest {

    @Test
    public void should_testName() throws Exception {
        final CharArrayQueue queue = new CharArrayQueue(4);

        queue.addAll("hall".toCharArray());
        System.out.println(queue);
        queue.remove();
        queue.remove();
        System.out.println(queue);
        queue.addAll("ha".toCharArray());
        System.out.println(queue);
        queue.put('x');
        queue.put('y');
        System.out.println(queue);
        System.out.println(queue.subSequence(3, 4));
    }

}