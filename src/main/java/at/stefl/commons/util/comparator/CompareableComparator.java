package at.stefl.commons.util.comparator;

import java.util.Comparator;

public class CompareableComparator<T extends Comparable<? super T>> implements Comparator<T> {

    @Override
    public int compare(final T o1, final T o2) {
        return o1.compareTo(o2);
    }

}