package at.stefl.commons.util.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import at.stefl.commons.util.primitive.IntegerReference;

// TODO: handle size > Integer.MAX_VALUE
public abstract class MultisetWrapper<E> extends AbstractMultiset<E> {

    private class MultisetIterator implements Iterator<E> {

        private final Iterator<Entry<E, IntegerReference>> iterator = MultisetWrapper.this.map.entrySet().iterator();
        private Entry<E, IntegerReference> entry;
        private int count;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return (this.count > 0) || this.iterator.hasNext();
        }

        @Override
        public E next() {
            if (this.count <= 0) {
                this.entry = this.iterator.next();
                if (this.entry == null) {
                    this.canRemove = false;
                    return null;
                }

                this.count = this.entry.getValue().value;
            }

            this.count--;
            this.canRemove = true;
            return this.entry.getKey();
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException();
            }

            final IntegerReference countReference = this.entry.getValue();
            if (countReference.value <= 1) {
                this.iterator.remove();
            } else {
                countReference.value = this.count - 1;
            }
            MultisetWrapper.this.size--;

            this.canRemove = false;
        }
    }

    private class UniqueIterator implements Iterator<E> {

        private final Iterator<Entry<E, IntegerReference>> iterator = MultisetWrapper.this.map.entrySet().iterator();
        private Entry<E, IntegerReference> entry;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public E next() {
            this.entry = this.iterator.next();
            this.canRemove = (this.entry != null);
            if (!this.canRemove) {
                return null;
            }
            return this.entry.getKey();
        }

        @Override
        public void remove() {
            if (!this.canRemove) {
                throw new IllegalStateException();
            }

            this.iterator.remove();
            MultisetWrapper.this.size -= this.entry.getValue().value;

            this.canRemove = false;
        }
    }

    private final Map<E, IntegerReference> map;

    private int size;

    public MultisetWrapper(final Map<E, IntegerReference> map) {
        this.map = map;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof MultisetWrapper)) {
            return super.equals(o);
        }
        final MultisetWrapper<?> other = (MultisetWrapper<?>) o;

        if (this.size != other.size) {
            return false;
        }
        return this.map.equals(other.map);
    }

    @Override
    public int uniqueCount(final Object o) {
        final IntegerReference countReference = this.map.get(o);
        return (countReference == null) ? 0 : countReference.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new MultisetIterator();
    }

    @Override
    public Iterator<E> uniqueIterator() {
        return new UniqueIterator();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int uniqueCount() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public boolean contains(final Object o, final int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c <= 0");
        }
        return this.uniqueCount(o) >= c;
    }

    @Override
    public boolean containsExactly(final Object o, final int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c <= 0");
        }
        return this.uniqueCount(o) == c;
    }

    @Override
    public boolean add(final E e, final int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c <= 0");
        }

        IntegerReference countReference = this.map.get(e);
        if (countReference == null) {
            this.map.put(e, countReference = new IntegerReference());
        }

        countReference.value += c;
        this.size += c;
        return true;
    }

    @Override
    public boolean remove(final Object o, final int c) {
        if (c <= 0) {
            throw new IllegalArgumentException("c <= 0");
        }

        final IntegerReference countReference = this.map.get(o);
        if (countReference == null) {
            return false;
        }

        if (countReference.value <= c) {
            this.map.remove(o);
            this.size -= countReference.value;
        } else {
            countReference.value -= c;
            this.size -= c;
        }

        return true;
    }

    @Override
    public boolean removeAll(final Object o) {
        final IntegerReference countReference = this.map.get(o);
        if (countReference == null) {
            return false;
        }

        this.map.remove(o);
        this.size -= countReference.value;
        return true;
    }

    @Override
    public void clear() {
        this.map.clear();
        this.size = 0;
    }

}