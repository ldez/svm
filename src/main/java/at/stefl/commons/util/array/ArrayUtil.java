package at.stefl.commons.util.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

import at.stefl.commons.math.MathUtil;
import at.stefl.commons.util.collection.CollectionUtil;

// TODO: improve attribute names
// TODO: implement array methods with offset and length
// TODO: avoid redundant code?
public class ArrayUtil {

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = {};
    public static final byte[] EMPTY_BYTE_ARRAY = {};
    public static final char[] EMPTY_CHAR_ARRAY = {};
    public static final short[] EMPTY_SHORT_ARRAY = {};
    public static final int[] EMPTY_INT_ARRAY = {};
    public static final long[] EMPTY_LONG_ARRAY = {};
    public static final float[] EMPTY_FLOAT_ARRAY = {};
    public static final double[] EMPTY_DOUBLE_ARRAY = {};
    public static final Object[] EMPTY_OBJECT_ARRAY = {};

    private static final Map<Class<?>, Object> EMPTY_ARRAY_MAP = new HashMap<Class<?>, Object>();

    private static final char[] HEX_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    static {
        EMPTY_ARRAY_MAP.put(boolean.class, EMPTY_BOOLEAN_ARRAY);
        EMPTY_ARRAY_MAP.put(byte.class, EMPTY_BYTE_ARRAY);
        EMPTY_ARRAY_MAP.put(char.class, EMPTY_CHAR_ARRAY);
        EMPTY_ARRAY_MAP.put(short.class, EMPTY_SHORT_ARRAY);
        EMPTY_ARRAY_MAP.put(int.class, EMPTY_INT_ARRAY);
        EMPTY_ARRAY_MAP.put(long.class, EMPTY_LONG_ARRAY);
        EMPTY_ARRAY_MAP.put(float.class, EMPTY_FLOAT_ARRAY);
        EMPTY_ARRAY_MAP.put(double.class, EMPTY_DOUBLE_ARRAY);
        EMPTY_ARRAY_MAP.put(Object.class, EMPTY_OBJECT_ARRAY);
    }

    @SuppressWarnings("unchecked")
    public static <T, E> T getEmptyArray(final Class<E> clazz) {
        Object result = EMPTY_ARRAY_MAP.get(clazz);

        if (result == null) {
            result = Array.newInstance(clazz, 0);
            EMPTY_ARRAY_MAP.put(clazz, result);
        }

        return (T) result;
    }

    public static <E> E getFirstNotNull(final E... array) {
        E element;

        for (final E element2 : array) {
            element = element2;
            if (element != null) {
                return element;
            }
        }

        return null;
    }

    public static <E> E getFirstNotNull(final E[] array, final int off, final int len) {
        final int end = off + len;
        E element;

        for (int i = off; i < end; i++) {
            element = array[i];
            if (element != null) {
                return element;
            }
        }

        return null;
    }

    public static int getEqualCount(final Object object, final Object... array) {
        int result = 0;

        for (final Object element : array) {
            if (object.equals(element)) {
                result++;
            }
        }

        return result;
    }

    public static int getEqualCount(final Object object, final Object[] array, final int off, final int len) {
        final int end = off + len;
        int result = 0;

        for (int i = off; i < end; i++) {
            if (object.equals(array[i])) {
                result++;
            }
        }

        return result;
    }

    public static int getReferenceCount(final Object object, final Object... array) {
        int result = 0;

        for (final Object element : array) {
            if (object == element) {
                result++;
            }
        }

        return result;
    }

    public static int getReferenceCount(final Object object, final Object[] array, final int off, final int len) {
        final int end = off + len;
        int result = 0;

        for (int i = off; i < end; i++) {
            if (object == array[i]) {
                result++;
            }
        }

        return result;
    }

    public static int getNullCount(final Object... array) {
        int result = 0;

        for (final Object element : array) {
            if (element == null) {
                result++;
            }
        }

        return result;
    }

    public static int getNullCount(final Object[] array, final int off, final int len) {
        final int end = off + len;
        int result = 0;

        for (int i = off; i < end; i++) {
            if (array[i] == null) {
                result++;
            }
        }

        return result;
    }

    public static int getNotNullCount(final Object... array) {
        int result = 0;

        for (final Object element : array) {
            if (element != null) {
                result++;
            }
        }

        return result;
    }

    public static int getNotNullCount(final Object[] array, final int off, final int len) {
        final int end = off + len;
        int result = 0;

        for (int i = off; i < end; i++) {
            if (array[i] != null) {
                result++;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] getNotNullArray(final E... array) {
        final int resultLength = getNotNullCount(array);
        final E[] result = (E[]) Array.newInstance(array.getClass().getComponentType(), resultLength);
        E element;

        for (int i = 0, j = 0; i < array.length; i++) {
            element = array[i];
            if (element != null) {
                result[j++] = element;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] getNotNullArray(final E[] array, final int off, final int len) {
        final int end = off + len;
        final int resultLength = getNotNullCount(array, off, len);
        final E[] result = (E[]) Array.newInstance(array.getClass().getComponentType(), resultLength);
        E element;

        for (int i = off, j = 0; i < end; i++) {
            element = array[i];
            if (element != null) {
                result[j++] = element;
            }
        }

        return result;
    }

    public static <T extends Collection<? super E>, E> T toCollection(final T c, final E... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Boolean>, E> T toCollection(final T c, final boolean... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Byte>, E> T toCollection(final T c, final byte... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Character>, E> T toCollection(final T c, final char... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Short>, E> T toCollection(final T c, final short... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Integer>, E> T toCollection(final T c, final int... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Long>, E> T toCollection(final T c, final long... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Float>, E> T toCollection(final T c, final float... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super Double>, E> T toCollection(final T c, final double... array) {
        CollectionUtil.addAll(c, array);
        return c;
    }

    public static <T extends Collection<? super E>, E> T toCollection(final T c, final E[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Boolean>, E> T toCollection(final T c, final boolean[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Byte>, E> T toCollection(final T c, final byte[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Character>, E> T toCollection(final T c, final char[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Short>, E> T toCollection(final T c, final short[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Integer>, E> T toCollection(final T c, final int[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Long>, E> T toCollection(final T c, final long[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Float>, E> T toCollection(final T c, final float[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <T extends Collection<? super Double>, E> T toCollection(final T c, final double[] array, final int off, final int len) {
        CollectionUtil.addAll(c, array, off, len);
        return c;
    }

    public static <E> HashSet<E> toHashSet(final E... array) {
        return toCollection(new HashSet<E>(array.length), array);
    }

    public static HashSet<Boolean> toHashSet(final boolean... array) {
        return toCollection(new HashSet<Boolean>(array.length), array);
    }

    public static HashSet<Byte> toHashSet(final byte... array) {
        return toCollection(new HashSet<Byte>(array.length), array);
    }

    public static HashSet<Character> toHashSet(final char... array) {
        return toCollection(new HashSet<Character>(array.length), array);
    }

    public static HashSet<Short> toHashSet(final short... array) {
        return toCollection(new HashSet<Short>(array.length), array);
    }

    public static HashSet<Integer> toHashSet(final int... array) {
        return toCollection(new HashSet<Integer>(array.length), array);
    }

    public static HashSet<Long> toHashSet(final long... array) {
        return toCollection(new HashSet<Long>(array.length), array);
    }

    public static HashSet<Float> toHashSet(final float... array) {
        return toCollection(new HashSet<Float>(array.length), array);
    }

    public static HashSet<Double> toHashSet(final double... array) {
        return toCollection(new HashSet<Double>(array.length), array);
    }

    public static <E> HashSet<E> toHashSet(final E[] array, final int off, final int len) {
        return toCollection(new HashSet<E>(array.length), array, off, len);
    }

    public static HashSet<Boolean> toHashSet(final boolean[] array, final int off, final int len) {
        return toCollection(new HashSet<Boolean>(array.length), array, off, len);
    }

    public static HashSet<Byte> toHashSet(final byte[] array, final int off, final int len) {
        return toCollection(new HashSet<Byte>(array.length), array, off, len);
    }

    public static HashSet<Character> toHashSet(final char[] array, final int off, final int len) {
        return toCollection(new HashSet<Character>(array.length), array, off, len);
    }

    public static HashSet<Short> toHashSet(final short[] array, final int off, final int len) {
        return toCollection(new HashSet<Short>(array.length), array, off, len);
    }

    public static HashSet<Integer> toHashSet(final int[] array, final int off, final int len) {
        return toCollection(new HashSet<Integer>(array.length), array, off, len);
    }

    public static HashSet<Long> toHashSet(final long[] array, final int off, final int len) {
        return toCollection(new HashSet<Long>(array.length), array, off, len);
    }

    public static HashSet<Float> toHashSet(final float[] array, final int off, final int len) {
        return toCollection(new HashSet<Float>(array.length), array, off, len);
    }

    public static HashSet<Double> toHashSet(final double[] array, final int off, final int len) {
        return toCollection(new HashSet<Double>(array.length), array, off, len);
    }

    public static <E> ArrayList<E> toArrayList(final E... array) {
        return toCollection(new ArrayList<E>(array.length), array);
    }

    public static ArrayList<Boolean> toArrayList(final boolean... array) {
        return toCollection(new ArrayList<Boolean>(array.length), array);
    }

    public static ArrayList<Byte> toArrayList(final byte... array) {
        return toCollection(new ArrayList<Byte>(array.length), array);
    }

    public static ArrayList<Character> toArrayList(final char... array) {
        return toCollection(new ArrayList<Character>(array.length), array);
    }

    public static ArrayList<Short> toArrayList(final short... array) {
        return toCollection(new ArrayList<Short>(array.length), array);
    }

    public static ArrayList<Integer> toArrayList(final int... array) {
        return toCollection(new ArrayList<Integer>(array.length), array);
    }

    public static ArrayList<Long> toArrayList(final long... array) {
        return toCollection(new ArrayList<Long>(array.length), array);
    }

    public static ArrayList<Float> toArrayList(final float... array) {
        return toCollection(new ArrayList<Float>(array.length), array);
    }

    public static ArrayList<Double> toArrayList(final double... array) {
        return toCollection(new ArrayList<Double>(array.length), array);
    }

    public static <E> ArrayList<E> toArrayList(final E[] array, final int off, final int len) {
        return toCollection(new ArrayList<E>(array.length), array, off, len);
    }

    public static ArrayList<Boolean> toArrayList(final boolean[] array, final int off, final int len) {
        return toCollection(new ArrayList<Boolean>(array.length), array, off, len);
    }

    public static ArrayList<Byte> toArrayList(final byte[] array, final int off, final int len) {
        return toCollection(new ArrayList<Byte>(array.length), array, off, len);
    }

    public static ArrayList<Character> toArrayList(final char[] array, final int off, final int len) {
        return toCollection(new ArrayList<Character>(array.length), array, off, len);
    }

    public static ArrayList<Short> toArrayList(final short[] array, final int off, final int len) {
        return toCollection(new ArrayList<Short>(array.length), array, off, len);
    }

    public static ArrayList<Integer> toArrayList(final int[] array, final int off, final int len) {
        return toCollection(new ArrayList<Integer>(array.length), array, off, len);
    }

    public static ArrayList<Long> toArrayList(final long[] array, final int off, final int len) {
        return toCollection(new ArrayList<Long>(array.length), array, off, len);
    }

    public static ArrayList<Float> toArrayList(final float[] array, final int off, final int len) {
        return toCollection(new ArrayList<Float>(array.length), array, off, len);
    }

    public static ArrayList<Double> toArrayList(final double[] array, final int off, final int len) {
        return toCollection(new ArrayList<Double>(array.length), array, off, len);
    }

    public static <E extends Comparable<E>> E getGreatest(final E... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        E result = array[0];
        E element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element.compareTo(result) > 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E> E getGreatest(final Comparator<? super E> comparator, final E... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        E result = array[0];
        E element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E extends Comparable<E>> E getGreatestNotNull(final E... array) {
        E result = null;
        E element;

        for (final E element2 : array) {
            element = element2;
            if (element == null) {
                continue;
            }
            if ((result == null) || (element.compareTo(result) > 0)) {
                result = element;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }

        return result;
    }

    public static <E> E getGreatestNotNull(final Comparator<? super E> comparator, final E... array) {
        E result = null;
        E element;

        for (final E element2 : array) {
            element = element2;
            if (element == null) {
                continue;
            }
            if ((result == null) || (comparator.compare(element, result) > 0)) {
                result = element;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }

        return result;
    }

    public static boolean getGreatest(final boolean... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        for (final boolean element : array) {
            if (element) {
                return true;
            }
        }

        return false;
    }

    public static byte getGreatest(final byte... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        byte result = array[0];
        byte element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static char getGreatest(final char... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        char result = array[0];
        char element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static short getGreatest(final short... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        short result = array[0];
        short element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static int getGreatest(final int... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        int result = array[0];
        int element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static long getGreatest(final long... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        long result = array[0];
        long element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static float getGreatest(final float... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        float result = array[0];
        float element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static double getGreatest(final double... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        double result = array[0];
        double element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element > result) {
                result = element;
            }
        }

        return result;
    }

    public static <E extends Comparable<? super E>> E getSmallest(final E... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        E result = array[0];
        E element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element.compareTo(result) < 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E> E getSmallest(final Comparator<? super E> comparator, final E... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        E result = array[0];
        E element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (comparator.compare(element, result) < 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E extends Comparable<? super E>> E getSmallestNotNull(final E... array) {
        E result = null;
        E element;

        for (final E element2 : array) {
            element = element2;
            if (element == null) {
                continue;
            }
            if ((result == null) || (element.compareTo(result) < 0)) {
                result = element;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }

        return result;
    }

    public static <E> E getSmallestNotNull(final Comparator<? super E> comparator, final E... array) {
        E result = null;
        E element;

        for (final E element2 : array) {
            element = element2;
            if (element == null) {
                continue;
            }
            if ((result == null) || (comparator.compare(element, result) < 0)) {
                result = element;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }

        return result;
    }

    public static boolean getSmallest(final boolean... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                return false;
            }
        }

        return true;
    }

    public static byte getSmallest(final byte... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        byte result = array[0];
        byte element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Byte.MIN_VALUE) {
                return Byte.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static char getSmallest(final char... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        char result = array[0];
        char element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Character.MIN_VALUE) {
                return Character.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static short getSmallest(final short... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        short result = array[0];
        short element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Short.MIN_VALUE) {
                return Short.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static int getSmallest(final int... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        int result = array[0];
        int element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static long getSmallest(final long... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        long result = array[0];
        long element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static float getSmallest(final float... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        float result = array[0];
        float element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Float.MIN_VALUE) {
                return Float.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    public static double getSmallest(final double... array) {
        if (array.length == 0) {
            throw new NoSuchElementException();
        }

        double result = array[0];
        double element;

        for (int i = 1; i < array.length; i++) {
            element = array[i];
            if (element == Double.MIN_VALUE) {
                return Double.MIN_VALUE;
            }
            if (element < result) {
                result = element;
            }
        }

        return result;
    }

    // TODO: handle exceptions?
    public static boolean equals(final Object[] array1, final int off1, final Object[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;
        Object element1;
        Object element2;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            element1 = array1[i1];
            element2 = array2[i2];
            if (!((element1 == null) ? (element2 == null) : element1.equals(element2))) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final boolean[] array1, final int off1, final boolean[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final byte[] array1, final int off1, final byte[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final char[] array1, final int off1, final char[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final short[] array1, final int off1, final short[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final int[] array1, final int off1, final int[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final long[] array1, final int off1, final long[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final float[] array1, final int off1, final float[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final double[] array1, final int off1, final double[] array2, final int off2, final int len) {
        if (array1 == array2) {
            return true;
        }
        if ((array1 == null) || (array2 == null)) {
            return false;
        }

        final int end1 = off1 + len;

        for (int i1 = off1, i2 = off2; i2 < end1; i1++, i2++) {
            if (array1[i1] != array2[i2]) {
                return false;
            }
        }

        return true;
    }

    public static int hashCode(final Object... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        Object element;

        for (final Object element2 : array) {
            element = element2;
            result = 31 * result + ((element == null) ? 0 : element.hashCode());
        }

        return result;
    }

    public static int hashCode(final boolean... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final boolean element : array) {
            result = 31 * result + (element ? 1231 : 1237);
        }

        return result;
    }

    public static int hashCode(final byte... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final byte element : array) {
            result = 31 * result + element;
        }

        return result;
    }

    public static int hashCode(final char... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final char element : array) {
            result = 31 * result + element;
        }

        return result;
    }

    public static int hashCode(final short... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final short element : array) {
            result = 31 * result + element;
        }

        return result;
    }

    public static int hashCode(final int... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final int element : array) {
            result = 31 * result + element;
        }

        return result;
    }

    public static int hashCode(final long... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        long element;

        for (final long element2 : array) {
            element = element2;
            result = 31 * result + (int) (element ^ (element >>> 32));
        }

        return result;
    }

    public static int hashCode(final float... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;

        for (final float element : array) {
            result = 31 * result + Float.floatToIntBits(element);
        }

        return result;
    }

    public static int hashCode(final double... array) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        long bits;

        for (final double element : array) {
            bits = Double.doubleToLongBits(element);
            result = 31 * result + (int) (bits ^ (bits >>> 32));
        }

        return result;
    }

    // TODO: handle exception
    public static int hashCode(final Object[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;
        Object element;

        for (int i = off; i < end; i++) {
            element = array[i];
            result = 31 * result + ((element == null) ? 0 : element.hashCode());
        }

        return result;
    }

    public static int hashCode(final boolean[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + (array[i] ? 1231 : 1237);
        }

        return result;
    }

    public static int hashCode(final byte[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + array[i];
        }

        return result;
    }

    public static int hashCode(final char[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + array[i];
        }

        return result;
    }

    public static int hashCode(final short[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + array[i];
        }

        return result;
    }

    public static int hashCode(final int[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + array[i];
        }

        return result;
    }

    public static int hashCode(final long[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;
        long element;

        for (int i = off; i < end; i++) {
            element = array[i];
            result = 31 * result + (int) (element ^ (element >>> 32));
        }

        return result;
    }

    public static int hashCode(final float[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;

        for (int i = off; i < end; i++) {
            result = 31 * result + Float.floatToIntBits(array[i]);
        }

        return result;
    }

    public static int hashCode(final double[] array, final int off, final int len) {
        if (array == null) {
            return 0;
        }

        int result = 1;
        final int end = off + len;
        long bits;

        for (int i = off; i < end; i++) {
            bits = Double.doubleToLongBits(array[i]);
            result = 31 * result + (int) (bits ^ (bits >>> 32));
        }

        return result;
    }

    public static void swap(final Object[] array, final int i, final int j) {
        final Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final boolean[] array, final int i, final int j) {
        final boolean tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final byte[] array, final int i, final int j) {
        final byte tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final char[] array, final int i, final int j) {
        final char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final short[] array, final int i, final int j) {
        final short tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final int[] array, final int i, final int j) {
        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final long[] array, final int i, final int j) {
        final long tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final float[] array, final int i, final int j) {
        final float tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swap(final double[] array, final int i, final int j) {
        final double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void swapAll(final Object[] array) {
        Object tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final boolean[] array) {
        boolean tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final byte[] array) {
        byte tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final char[] array) {
        char tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final short[] array) {
        short tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final int[] array) {
        int tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final long[] array) {
        long tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final float[] array) {
        float tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final double[] array) {
        double tmp;

        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final Object[] array, final int off, final int len) {
        final int last = off + len - 1;
        Object tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final boolean[] array, final int off, final int len) {
        final int last = off + len - 1;
        boolean tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final byte[] array, final int off, final int len) {
        final int last = off + len - 1;
        byte tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final char[] array, final int off, final int len) {
        final int last = off + len - 1;
        char tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final short[] array, final int off, final int len) {
        final int last = off + len - 1;
        short tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final int[] array, final int off, final int len) {
        final int last = off + len - 1;
        int tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final long[] array, final int off, final int len) {
        final int last = off + len - 1;
        long tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final float[] array, final int off, final int len) {
        final int last = off + len - 1;
        float tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void swapAll(final double[] array, final int off, final int len) {
        final int last = off + len - 1;
        double tmp;

        for (int i = off, j = last; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Class<T> arrayClass, final int length) {
        if (!arrayClass.isArray()) {
            throw new IllegalArgumentException("not an array");
        }
        return (T) Array.newInstance(arrayClass.getComponentType(), length);
    }

    @SuppressWarnings("unchecked")
    public static <T> T copyOf(final T array, final int newLength) {
        if (newLength < 0) {
            throw new NegativeArraySizeException();
        }

        final int length = Array.getLength(array);
        final T newArray = newInstance((Class<T>) array.getClass(), newLength);
        System.arraycopy(array, 0, newArray, 0, Math.min(length, newLength));

        return newArray;
    }

    @SuppressWarnings("unchecked")
    public static <T> T copyOfRange(final T array, final int from, final int to) {
        if (from < 0) {
            throw new ArrayIndexOutOfBoundsException(from);
        }

        final int length = Array.getLength(array);
        if (from > length) {
            throw new ArrayIndexOutOfBoundsException(from);
        }

        final int newLength = from - to;
        if (newLength < 0) {
            throw new IllegalArgumentException();
        }

        final T newArray = newInstance((Class<T>) array.getClass(), newLength);
        System.arraycopy(array, from, newArray, 0, Math.min(length, newLength));

        return newArray;
    }

    public static <T> T growDirect(final T array, final int newLength) {
        final int length = Array.getLength(array);
        if (length >= newLength) {
            return array;
        }

        return copyOf(array, newLength);
    }

    public static <T> T growArithmetic(final T array, int newLength, final int stepSize) {
        final int length = Array.getLength(array);
        if (length >= newLength) {
            return array;
        }

        newLength = length + stepSize + MathUtil.floor(newLength - length, stepSize);
        return copyOf(array, newLength);
    }

    public static <T> T growGeometric(final T array, int newLength, final int base) {
        final int length = Array.getLength(array);
        if (length >= newLength) {
            return array;
        }

        final int addExponent = (int) Math.ceil(((double) newLength / length) * (1d / base));
        newLength = (int) (length * Math.pow(base, addExponent));
        return copyOf(array, newLength);
    }

    // TODO: implement all toString

    public static String toHexString(final byte[] array) {
        return toStringHex(array, 0, array.length);
    }

    public static String toStringHex(final byte[] array, final int off, final int len) {
        final char[] result = new char[len << 1];
        final int end = off + len;

        for (int i = off, b, j = 0; i < end; i++) {
            b = array[i] & 0xff;
            result[j++] = HEX_ARRAY[b >> 4];
            result[j++] = HEX_ARRAY[b & 0x0f];
        }

        return new String(result);
    }

    public static byte[] toByteArray(final char[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final char[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final char[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static byte[] toByteArray(final short[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final short[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final short[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static byte[] toByteArray(final int[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final int[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final int[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static byte[] toByteArray(final long[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final long[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final long[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static byte[] toByteArray(final float[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final float[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final float[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static byte[] toByteArray(final double[] array) {
        return toByteArray(array, 0, array.length);
    }

    public static byte[] toByteArray(final double[] array, final int off, final int len) {
        return toByteArray(array, off, len, new byte[len], 0);
    }

    public static byte[] toByteArray(final double[] from, final int fromOff, final int len, final byte[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (byte) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final byte[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final byte[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final byte[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final short[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final short[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final short[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final int[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final int[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final int[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final long[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final long[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final long[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final float[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final float[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final float[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static char[] toCharArray(final double[] array) {
        return toCharArray(array, 0, array.length);
    }

    public static char[] toCharArray(final double[] array, final int off, final int len) {
        return toCharArray(array, off, len, new char[len], 0);
    }

    public static char[] toCharArray(final double[] from, final int fromOff, final int len, final char[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (char) from[i];
        }

        return to;
    }

    public static short[] toShortArray(final byte[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final byte[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final byte[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static short[] toShortArray(final char[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final char[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final char[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (short) from[i];
        }

        return to;
    }

    public static short[] toShortArray(final int[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final int[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final int[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (short) from[i];
        }

        return to;
    }

    public static short[] toShortArray(final long[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final long[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final long[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (short) from[i];
        }

        return to;
    }

    public static short[] toShortArray(final float[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final float[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final float[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (short) from[i];
        }

        return to;
    }

    public static short[] toShortArray(final double[] array) {
        return toShortArray(array, 0, array.length);
    }

    public static short[] toShortArray(final double[] array, final int off, final int len) {
        return toShortArray(array, off, len, new short[len], 0);
    }

    public static short[] toShortArray(final double[] from, final int fromOff, final int len, final short[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (short) from[i];
        }

        return to;
    }

    public static int[] toIntArray(final byte[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final byte[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final byte[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static int[] toIntArray(final char[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final char[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final char[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static int[] toIntArray(final short[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final short[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final short[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static int[] toIntArray(final long[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final long[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final long[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (int) from[i];
        }

        return to;
    }

    public static int[] toIntArray(final float[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final float[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final float[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (int) from[i];
        }

        return to;
    }

    public static int[] toIntArray(final double[] array) {
        return toIntArray(array, 0, array.length);
    }

    public static int[] toIntArray(final double[] array, final int off, final int len) {
        return toIntArray(array, off, len, new int[len], 0);
    }

    public static int[] toIntArray(final double[] from, final int fromOff, final int len, final int[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (int) from[i];
        }

        return to;
    }

    public static long[] toLongArray(final byte[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final byte[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final byte[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static long[] toLongArray(final char[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final char[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final char[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static long[] toLongArray(final short[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final short[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final short[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static long[] toLongArray(final int[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final int[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final int[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static long[] toLongArray(final float[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final float[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final float[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (long) from[i];
        }

        return to;
    }

    public static long[] toLongArray(final double[] array) {
        return toLongArray(array, 0, array.length);
    }

    public static long[] toLongArray(final double[] array, final int off, final int len) {
        return toLongArray(array, off, len, new long[len], 0);
    }

    public static long[] toLongArray(final double[] from, final int fromOff, final int len, final long[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (long) from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final byte[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final byte[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final byte[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final char[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final char[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final char[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final short[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final short[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final short[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final int[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final int[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final int[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final long[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final long[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final long[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static float[] toFloatArray(final double[] array) {
        return toFloatArray(array, 0, array.length);
    }

    public static float[] toFloatArray(final double[] array, final int off, final int len) {
        return toFloatArray(array, off, len, new float[len], 0);
    }

    public static float[] toFloatArray(final double[] from, final int fromOff, final int len, final float[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = (float) from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final byte[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final byte[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final byte[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final char[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final char[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final char[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final short[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final short[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final short[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final int[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final int[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final int[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final long[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final long[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final long[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    public static double[] toDoubleArray(final float[] array) {
        return toDoubleArray(array, 0, array.length);
    }

    public static double[] toDoubleArray(final float[] array, final int off, final int len) {
        return toDoubleArray(array, off, len, new double[len], 0);
    }

    public static double[] toDoubleArray(final float[] from, final int fromOff, final int len, final double[] to, final int toOff) {
        final int end = fromOff + len;

        for (int i = fromOff, j = toOff; i < end; i++, j++) {
            to[j] = from[i];
        }

        return to;
    }

    private ArrayUtil() {}

}