package at.stefl.commons.util.comparator;

import java.util.Comparator;

public abstract class SimpleDelegationComparator<T> extends DelegationComparator<T, T> {

    SimpleDelegationComparator() {}

    public SimpleDelegationComparator(final Comparator<? super T> comparator) {
        super(comparator);
    }

    protected T translate(final T o) {
        return o;
    }

    @Override
    public int compare(final T o1, final T o2) {
        return this.comparator.compare(o1, o2);
    }

}