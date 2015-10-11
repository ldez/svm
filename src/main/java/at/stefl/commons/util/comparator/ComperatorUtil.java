package at.stefl.commons.util.comparator;

import java.util.Comparator;

public class ComperatorUtil {

    @SuppressWarnings("unchecked")
    public static <T> int hybridCompare(final T o1, final T o2, final Comparator<? super T> comparator) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        }
        final Comparable<? super T> c1 = (Comparable<? super T>) o1;
        return c1.compareTo(o2);
    }

    public static <T> Comparator<T> reverseComparator() {
        return new ReverseComparator<T>();
    }

    public static <T> Comparator<T> reverseComparator(final Comparator<? super T> comparator) {
        return new ReverseComparator<T>(comparator);
    }

    private ComperatorUtil() {}

}