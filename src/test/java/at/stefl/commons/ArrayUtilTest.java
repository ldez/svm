package at.stefl.commons;

import org.junit.Test;

import at.stefl.commons.util.array.ArrayUtil;

public class ArrayUtilTest {

    @Test
    public void should_testName() throws Exception {
        final int[] array = ArrayUtil.growGeometric(new int[3], 4, 2);
        System.out.println(array.length);
    }

}