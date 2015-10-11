package at.stefl.commons.util.collection;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

// TODO: implement toString
public abstract class AbstractMultiset<E> extends AbstractSet<E> implements Multiset<E> {

    private class UniqueSet extends AbstractSet<E> {

        @Override
        public Iterator<E> iterator() {
            return AbstractMultiset.this.uniqueIterator();
        }

        @Override
        public int size() {
            return AbstractMultiset.this.uniqueCount();
        }

        @Override
        public boolean contains(final Object o) {
            return AbstractMultiset.this.contains(o);
        }

        @Override
        public boolean remove(final Object o) {
            return AbstractMultiset.this.remove(o);
        }

        @Override
        public void clear() {
            AbstractMultiset.this.clear();
        }
    }

    private UniqueSet uniqueSet;

    @Override
    public int hashCode() {
        int result = 0;

        for (final E e : this) {
            if (e != null) {
                result += e.hashCode();
            }
        }

        return result;
    }

    // TODO: fix for collections
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (!(o instanceof Multiset)) {
            return false;
        }
        final Multiset<?> other = (Multiset<?>) o;

        if (this.size() != other.size()) {
            return false;
        }
        if (this.uniqueCount() != other.uniqueCount()) {
            return false;
        }

        for (final E e : this) {
            if (!this.contains(e)) {
                return false;
            }
            if (this.uniqueCount(e) != other.uniqueCount(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean contains(final Object o, final int c) {
        if (!this.contains(o)) {
            return false;
        }
        if (this.uniqueCount(o) < c) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsExactly(final Object o, final int c) {
        if (!this.contains(o)) {
            return false;
        }
        if (this.uniqueCount(o) != c) {
            return false;
        }
        return true;
    }

    @Override
    public boolean add(final E e) {
        return this.add(e, 1);
    }

    @Override
    public boolean remove(final Object o) {
        return this.remove(o, 1);
    }

    @Override
    public boolean removeAll(final Object o) {
        return this.remove(o, this.uniqueCount(o));
    }

    @Override
    public Set<E> uniqueSet() {
        return (this.uniqueSet == null) ? (this.uniqueSet = new UniqueSet()) : this.uniqueSet;
    }

}