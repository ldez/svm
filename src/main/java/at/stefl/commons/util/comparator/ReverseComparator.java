package at.stefl.commons.util.comparator;

import java.util.Comparator;

public class ReverseComparator<T> extends SimpleHybridDelegationComparator<T> {

    public ReverseComparator() {}

    public ReverseComparator(final Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public int compare(final T o1, final T o2) {
        return -super.compare(o1, o2);
    }

}