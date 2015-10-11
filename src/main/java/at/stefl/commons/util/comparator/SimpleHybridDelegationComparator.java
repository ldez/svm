package at.stefl.commons.util.comparator;

import java.util.Comparator;

public abstract class SimpleHybridDelegationComparator<T> extends HybridDelegationComparator<T, T> {

    public SimpleHybridDelegationComparator() {}

    public SimpleHybridDelegationComparator(final Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public int compare(final T o1, final T o2) {
        return ComperatorUtil.hybridCompare(o1, o2, this.comparator);
    }

}