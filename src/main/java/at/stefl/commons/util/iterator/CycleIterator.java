package at.stefl.commons.util.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CycleIterator<E> extends AbstractIterator<E> {

    private final Iterable<? extends E> iterable;
    private Iterator<? extends E> iterator;

    private boolean hasNext;

    public CycleIterator(final Iterable<? extends E> iterable) {
        this.iterable = iterable;

        this.reinit();
    }

    private void reinit() {
        this.iterator = this.iterable.iterator();
        this.hasNext = this.iterator.hasNext();
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public E next() {
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        final E result = this.iterator.next();
        if (!this.iterator.hasNext()) {
            this.reinit();
        }
        return result;
    }

    @Override
    public void remove() {
        if (!this.hasNext) {
            throw new IllegalStateException();
        }
        this.iterator.remove();
    }

}