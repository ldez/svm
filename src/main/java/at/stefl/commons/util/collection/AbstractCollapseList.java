package at.stefl.commons.util.collection;

import java.util.AbstractList;

public abstract class AbstractCollapseList<E> extends AbstractList<E> implements CollapseList<E> {

    @Override
    public boolean add(final E e) {
        this.add(this.size(), e);
        return true;
    }

    @Override
    public boolean add(final E e, final int count) {
        this.add(this.size(), e, count);
        return true;
    }

    @Override
    public void add(final int index, final E element) {
        this.add(index, element, 1);
    }

}