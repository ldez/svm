package at.stefl.commons.util.iterator;

import java.util.Collection;
import java.util.Iterator;

public class IteratorUtil {

    public static Object[] toArray(final Iterator<?> iterator, final int limit) {
        return toArray(iterator, new Object[limit], 0, limit);
    }

    public static <E> E[] toArray(final Iterator<? extends E> iterator, final E[] array) {
        for (int i = 0; iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    public static <E> E[] toArray(final Iterator<? extends E> iterator, final E[] array, final int off) {
        for (int i = off; iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    public static <E> E[] toArray(final Iterator<? extends E> iterator, final E[] array, final int off, final int limit) {
        final int end = off + limit;
        for (int i = off; (i < end) && iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    public static <E> void toCollection(final Iterator<E> iterator, final Collection<? super E> c) {
        while (iterator.hasNext()) {
            c.add(iterator.next());
        }
    }

    public static <E> void toCollection(final Iterator<? extends E> iterator, final Collection<? super E> c, final int limit) {
        for (int i = 0; (i < limit) && iterator.hasNext(); i++) {
            c.add(iterator.next());
        }
    }

    public static <E> E findObject(final Iterator<? extends E> iterator, final Object matcher) {
        E element;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (matcher.equals(element)) {
                return element;
            }
        }

        return null;
    }

    public static <E> E findObject(final Iterator<? extends E> iterator, final Object matcher, final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0");
        }

        E element;
        for (int i = 0; (i < limit) && iterator.hasNext(); i++) {
            element = iterator.next();
            if (matcher.equals(element)) {
                return element;
            }
        }

        return null;
    }

    public static int count(final Iterator<?> iterator) {
        int result = 0;
        while (iterator.hasNext()) {
            result++;
        }
        return result;
    }

    private IteratorUtil() {}

}