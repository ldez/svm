package at.stefl.commons.util.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LimitedIterator<E> extends SimpleDelegationIterator<E> {

    private int limit;

    public LimitedIterator(final Iterator<E> iterator, final int limit) {
        super(iterator);

        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0");
        }
        this.limit = limit;
    }

    @Override
    public E next() {
        if (this.limit == 0) {
            throw new NoSuchElementException();
        }
        this.limit--;
        return this.iterator.next();
    }

    @Override
    public boolean hasNext() {
        return (this.limit > 0) && this.iterator.hasNext();
    }

}