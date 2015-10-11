package at.stefl.commons.util.iterator;

import java.util.NoSuchElementException;

public class SingleElementIterator<E> extends AbstractIterator<E> {

    private E element;
    private boolean hasNext = true;
    private boolean first = true;
    private boolean removed;

    public SingleElementIterator(final E element) {
        this.element = element;
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
        this.hasNext = false;
        this.first = false;
        return this.element;
    }

    @Override
    public void remove() {
        if (this.first || !this.hasNext || this.removed) {
            throw new IllegalStateException();
        }
        this.element = null;
        this.removed = true;
    }

}