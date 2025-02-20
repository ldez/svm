package at.stefl.commons.util.iterator;

import java.util.Iterator;

public class IterableIterator<E> implements Iterable<E> {

    private final Iterator<E> iterator;

    public IterableIterator(final Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Iterator<E> iterator() {
        return this.iterator;
    }

}