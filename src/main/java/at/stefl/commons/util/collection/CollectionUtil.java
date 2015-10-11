package at.stefl.commons.util.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

import at.stefl.commons.util.iterator.IterableIterator;
import at.stefl.commons.util.iterator.IteratorEnumeration;
import at.stefl.commons.util.iterator.IteratorUtil;
import at.stefl.commons.util.object.ObjectTransformer;

// TODO: improve argument names
// TODO: call method by method, avoid redundant code?
public class CollectionUtil {

    public static <E> Iterable<E> getIterable(final Iterator<E> iterator) {
        return new IterableIterator<E>(iterator);
    }

    public static boolean containsAll(final Collection<?> c, final Object... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Boolean> c, final boolean... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Byte> c, final byte... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Character> c, final char... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Short> c, final short... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Integer> c, final int... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Long> c, final long... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Float> c, final float... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Double> c, final double... array) {
        for (int i = 0; i < array.length; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<?> c, final Object[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Boolean> c, final boolean[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Byte> c, final byte[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Character> c, final char[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Short> c, final short[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Integer> c, final int[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Long> c, final long[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Float> c, final float[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean containsAll(final Collection<? super Double> c, final double[] array, final int off, final int len) {
        final int end = off + len;

        for (int i = off; i < end; i++) {
            if (!c.contains(array[i])) {
                return false;
            }
        }

        return true;
    }

    public static <E> boolean addAll(final Collection<? super E> c, final E... array) {
        boolean result = false;

        for (final E element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Boolean> c, final boolean... array) {
        boolean result = false;

        for (final boolean element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Byte> c, final byte... array) {
        boolean result = false;

        for (final byte element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Character> c, final char... array) {
        boolean result = false;

        for (final char element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Short> c, final short... array) {
        boolean result = false;

        for (final short element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Integer> c, final int... array) {
        boolean result = false;

        for (final int element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Long> c, final long... array) {
        boolean result = false;

        for (final long element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Float> c, final float... array) {
        boolean result = false;

        for (final float element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Double> c, final double... array) {
        boolean result = false;

        for (final double element : array) {
            result |= c.add(element);
        }

        return result;
    }

    public static <E> boolean addAll(final Collection<? super E> c, final E[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Boolean> c, final boolean[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Byte> c, final byte[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Character> c, final char[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Short> c, final short[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Integer> c, final int[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Long> c, final long[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Float> c, final float[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean addAll(final Collection<? super Double> c, final double[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.add(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<?> c, final Object... array) {
        boolean result = false;

        for (final Object element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Byte> c, final boolean... array) {
        boolean result = false;

        for (final boolean element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Byte> c, final byte... array) {
        boolean result = false;

        for (final byte element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Character> c, final char... array) {
        boolean result = false;

        for (final char element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Short> c, final short... array) {
        boolean result = false;

        for (final short element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Integer> c, final int... array) {
        boolean result = false;

        for (final int element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Long> c, final long... array) {
        boolean result = false;

        for (final long element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Float> c, final float... array) {
        boolean result = false;

        for (final float element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Double> c, final double... array) {
        boolean result = false;

        for (final double element : array) {
            result |= c.remove(element);
        }

        return result;
    }

    public static boolean removeAll(final Collection<?> c, final Object[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Byte> c, final boolean[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Byte> c, final byte[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Character> c, final char[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Short> c, final short[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Integer> c, final int[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Long> c, final long[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Float> c, final float[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static boolean removeAll(final Collection<? super Double> c, final double[] array, final int off, final int len) {
        final int end = off + len;
        boolean result = false;

        for (int i = off; i < end; i++) {
            result |= c.remove(array[i]);
        }

        return result;
    }

    public static <E extends Comparable<E>> E getGreatest(final Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }

        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getGreatest((List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.compareTo(result) > 0) {
                result = element;
            }
        }

        return result;
    }

    private static <E extends Comparable<E>> E getGreatest(final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            final E element = randomAccessList.get(i);
            if (element.compareTo(result) > 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E> E getGreatest(final Comparator<? super E> comparator, final Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }

        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getGreatest(comparator, (List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }

        return result;
    }

    private static <E> E getGreatest(final Comparator<? super E> comparator, final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            final E element = randomAccessList.get(i);
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E extends Comparable<E>> E getGreatestNotNull(final Collection<? extends E> c) {
        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getGreatestNotNull((List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
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

    private static <E extends Comparable<E>> E getGreatestNotNull(final List<? extends E> randomAccessList) {
        E result = null;
        E element = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            element = randomAccessList.get(i);
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

    public static <E> E getGreatestNotNull(final Comparator<? super E> comparator, final Collection<? extends E> c) {
        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getGreatestNotNull(comparator, (List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
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

    private static <E> E getGreatestNotNull(final Comparator<? super E> comparator, final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);
        E element;

        for (int i = 1; i < randomAccessList.size(); i++) {
            element = randomAccessList.get(i);
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

    public static <E extends Comparable<E>> E getSmallest(final Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }

        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getSmallest((List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.compareTo(result) < 0) {
                result = element;
            }
        }

        return result;
    }

    private static <E extends Comparable<E>> E getSmallest(final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            final E element = randomAccessList.get(i);
            if (element.compareTo(result) < 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E> E getSmallest(final Comparator<? super E> comparator, final Collection<? extends E> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }

        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getSmallest(comparator, (List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
            if (comparator.compare(element, result) < 0) {
                result = element;
            }
        }

        return result;
    }

    private static <E> E getSmallest(final Comparator<? super E> comparator, final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            final E element = randomAccessList.get(i);
            if (comparator.compare(element, result) < 0) {
                result = element;
            }
        }

        return result;
    }

    public static <E extends Comparable<E>> E getSmallestNotNull(final Collection<? extends E> c) {
        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getSmallestNotNull((List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
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

    private static <E extends Comparable<E>> E getSmallestNotNull(final List<? extends E> randomAccessList) {
        E result = null;
        E element = randomAccessList.get(0);

        for (int i = 1; i < randomAccessList.size(); i++) {
            element = randomAccessList.get(i);
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

    public static <E> E getSmallestNotNull(final Comparator<? super E> comparator, final Collection<? extends E> c) {
        if ((c instanceof List) && (c instanceof RandomAccess)) {
            return getSmallestNotNull(comparator, (List<? extends E>) c);
        }

        final Iterator<? extends E> iterator = c.iterator();
        E result = iterator.next();
        E element;

        while (iterator.hasNext()) {
            element = iterator.next();
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

    private static <E> E getSmallestNotNull(final Comparator<? super E> comparator, final List<? extends E> randomAccessList) {
        E result = randomAccessList.get(0);
        E element;

        for (int i = 1; i < randomAccessList.size(); i++) {
            element = randomAccessList.get(i);
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

    public static <K, V> void putAll(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final Collection<? extends V> values) {
        if ((values instanceof List) && (values instanceof RandomAccess)) {
            putAll(map, keyGenerator, (List<? extends V>) values);
            return;
        }

        for (final V value : values) {
            final K key = keyGenerator.transform(value);
            map.put(key, value);
        }
    }

    private static <K, V> void putAll(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final List<? extends V> randomAccessList) {
        for (int i = 0; i < randomAccessList.size(); i++) {
            final V value = randomAccessList.get(i);
            final K key = keyGenerator.transform(value);
            map.put(key, value);
        }
    }

    public static <K, V> void putAllNotNull(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final Collection<? extends V> values) {
        if ((values instanceof List) && (values instanceof RandomAccess)) {
            putAllNotNull(map, keyGenerator, (List<? extends V>) values);
            return;
        }

        for (final V value : values) {
            final K key = keyGenerator.transform(value);
            if (key == null) {
                continue;
            }
            map.put(key, value);
        }
    }

    private static <K, V> void putAllNotNull(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final List<? extends V> randomAccessList) {
        for (int i = 0; i < randomAccessList.size(); i++) {
            final V value = randomAccessList.get(i);
            final K key = keyGenerator.transform(value);
            if (key == null) {
                continue;
            }
            map.put(key, value);
        }
    }

    // TODO: implement key/value generator
    // TODO: implement multiple put (put to multiple maps)
    public static <K, V> void putAll(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final V... values) {
        V value;
        K key;

        for (final V value2 : values) {
            value = value2;
            key = keyGenerator.transform(value);
            map.put(key, value);
        }
    }

    public static <K, V> void putAllNotNull(final Map<? super K, ? super V> map, final ObjectTransformer<? super V, ? extends K> keyGenerator, final V... values) {
        V value;
        K key;

        for (final V value2 : values) {
            value = value2;
            key = keyGenerator.transform(value);
            if (key == null) {
                continue;
            }
            map.put(key, value);
        }
    }

    public static <K, V> HashMap<K, V> toHashMap(final ObjectTransformer<? super V, ? extends K> keyGenerator, final V... values) {
        final HashMap<K, V> result = new HashMap<K, V>();
        putAll(result, keyGenerator, values);
        return result;
    }

    public static <K, V> HashMap<K, V> toHashMapNotNull(final ObjectTransformer<? super V, ? extends K> keyGenerator, final V... values) {
        final HashMap<K, V> result = new HashMap<K, V>();
        putAllNotNull(result, keyGenerator, values);
        return result;
    }

    public static <K, V> HashMap<K, V> toHashMap(final ObjectTransformer<? super V, ? extends K> keyGenerator, final Collection<? extends V> values) {
        final HashMap<K, V> result = new HashMap<K, V>();
        putAll(result, keyGenerator, values);
        return result;
    }

    public static <K, V> HashMap<K, V> toHashMapNotNull(final ObjectTransformer<? super V, ? extends K> keyGenerator, final Collection<? extends V> values) {
        final HashMap<K, V> result = new HashMap<K, V>();
        putAllNotNull(result, keyGenerator, values);
        return result;
    }

    public static <E> Enumeration<E> enumeration(final Collection<? extends E> c) {
        return new IteratorEnumeration<E>(c.iterator());
    }

    public static <E> void swap(final List<E> list, final int i, final int j) {
        if (list instanceof RandomAccess) {
            list.set(i, list.set(j, list.get(i)));
        } else {
            final ListIterator<E> iterator = list.listIterator(i);
            iterator.set(list.set(j, iterator.next()));
        }
    }

    public static <E> void swapAll(final List<E> list) {
        if (list instanceof RandomAccess) {
            for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
                list.set(i, list.set(j, list.get(i)));
            }
        } else {
            final ListIterator<E> iteratorI = list.listIterator();
            final ListIterator<E> iteratorJ = list.listIterator(list.size());
            E tmp;

            while (iteratorI.nextIndex() < iteratorJ.nextIndex()) {
                tmp = iteratorI.next();
                iteratorI.set(iteratorJ.previous());
                iteratorJ.set(tmp);
            }
        }
    }

    public static <E> void swapAll(final List<E> list, final int off, final int len) {
        if (list instanceof RandomAccess) {
            final int last = off + len - 1;
            for (int i = off, j = last; i < j; i++, j--) {
                list.set(i, list.set(j, list.get(i)));
            }
        } else {
            final int end = off + len;
            final ListIterator<E> iteratorI = list.listIterator(off);
            final ListIterator<E> iteratorJ = list.listIterator(end);
            E tmp;

            while (iteratorI.nextIndex() < iteratorJ.nextIndex()) {
                tmp = iteratorI.next();
                iteratorI.set(iteratorJ.previous());
                iteratorJ.set(tmp);
            }
        }
    }

    public static <V> void get(final Map<?, ? extends V> map, final Collection<? extends Object> keys, final Collection<? super V> values) {
        for (final Object key : keys) {
            if (!map.containsKey(key)) {
                continue;
            }

            final V value = map.get(key);
            values.add(value);
        }
    }

    public static <V> void get(final Map<?, ? extends V> map, final Collection<? super V> values, final Object... keys) {
        for (final Object key2 : keys) {
            final Object key = key2;
            if (!map.containsKey(key)) {
                continue;
            }

            final V value = map.get(key);
            values.add(value);
        }
    }

    public static <V> void getNotNull(final Map<?, ? extends V> map, final Collection<? extends Object> keys, final Collection<? super V> values) {
        for (final Object key : keys) {
            final V value = map.get(key);
            if (value != null) {
                values.add(value);
            }
        }
    }

    public static <V> void getNotNull(final Map<?, ? extends V> map, final Collection<? super V> values, final Object... keys) {
        for (final Object key : keys) {
            final V value = map.get(key);
            if (value != null) {
                values.add(value);
            }
        }
    }

    public static <V> HashSet<V> getHashSet(final Map<?, ? extends V> map, final Collection<? extends Object> keys) {
        final HashSet<V> result = new HashSet<V>();
        get(map, keys, result);
        return result;
    }

    public static <V> HashSet<V> getHashSet(final Map<?, ? extends V> map, final Object... keys) {
        final HashSet<V> result = new HashSet<V>();
        get(map, result, keys);
        return result;
    }

    public static <V> HashSet<V> getHashSetNotNull(final Map<?, ? extends V> map, final Collection<? extends Object> keys) {
        final HashSet<V> result = new HashSet<V>();
        getNotNull(map, keys, result);
        return result;
    }

    public static <V> HashSet<V> getHashSetNotNull(final Map<?, ? extends V> map, final Object... keys) {
        final HashSet<V> result = new HashSet<V>();
        getNotNull(map, result, keys);
        return result;
    }

    public static <E> E[] toArray(final Collection<? extends E> from, final E[] to) {
        return toArray(from, to, 0);
    }

    public static <E> E[] toArray(final Collection<? extends E> from, final E[] to, final int off) {
        return toArray(from, to, off, from.size());
    }

    public static <E> E[] toArray(final Collection<? extends E> from, final E[] to, final int off, final int len) {
        return IteratorUtil.toArray(from.iterator(), to, off, len);
    }

    private CollectionUtil() {}

}