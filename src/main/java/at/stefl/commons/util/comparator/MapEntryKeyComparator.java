package at.stefl.commons.util.comparator;

import java.util.Comparator;
import java.util.Map.Entry;

public class MapEntryKeyComparator<T> extends HybridDelegationComparator<T, Entry<? extends T, ?>> {

    public MapEntryKeyComparator() {}

    public MapEntryKeyComparator(final Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public int compare(final Entry<? extends T, ?> o1, final Entry<? extends T, ?> o2) {
        return this.comparator.compare(o1.getKey(), o2.getKey());
    }

}