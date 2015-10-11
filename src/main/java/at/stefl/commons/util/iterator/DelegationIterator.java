package at.stefl.commons.util.iterator;

import java.util.Iterator;

public abstract class DelegationIterator<E1, E2> extends AbstractIterator<E2> {

    protected Iterator<? extends E1> iterator;

    public DelegationIterator(final Iterator<? extends E1> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public abstract E2 next();

    @Override
    public void remove() {
        this.iterator.remove();
    }

}