package at.stefl.commons.util.iterator;

import java.util.Iterator;

public class NoDeleteDelegationIterable<T> extends SimpleDelegationIterable<T> {

    public NoDeleteDelegationIterable(final Iterable<? extends T> iterable) {
        super(iterable);
    }

    @Override
    public Iterator<T> iterator() {
        return new NoDeleteDelegationIterator<T>(this.iterable.iterator());
    }

}