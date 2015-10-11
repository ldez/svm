package at.stefl.commons.util.collection;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class InverseSet<E> extends AbstractSet<E> {

    private final Set<? super E> inverseSet;

    public InverseSet() {
        this(new HashSet<E>());
    }

    public InverseSet(final Set<? super E> reverseSet) {
        this.inverseSet = reverseSet;
    }

    @Override
    public int hashCode() {
        return this.inverseSet.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (!(o instanceof InverseSet)) {
            return false;
        }
        final InverseSet<?> other = (InverseSet<?>) o;

        if (this.inverseSet.size() != this.inverseSet.size()) {
            return false;
        }
        return this.inverseSet.containsAll(other.inverseSet);
    }

    @Override
    public String toString() {
        return "!" + this.inverseSet;
    }

    public Set<? super E> inverseSet() {
        return this.inverseSet;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean contains(final Object o) {
        return !this.inverseSet.contains(o);
    }

    @Override
    public boolean add(final E e) {
        return this.inverseSet.remove(e);
    }

    public boolean removeElement(final E e) {
        return this.inverseSet.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Object> T[] toArray(final T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

}