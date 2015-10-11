package at.stefl.commons.util.collection.primitive;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractPrimitiveBooleanCollection extends AbstractPrimitiveCollection<Boolean> implements PrimitiveBooleanCollection {

    @Override
    public boolean add(final Boolean e) {
        if (e == null) {
            throw new NullPointerException();
        }
        return this.add((boolean) e);
    }

    @Override
    public boolean addAll(final Collection<? extends Boolean> c) {
        boolean result = false;

        if (c instanceof PrimitiveBooleanCollection) {
            final PrimitiveBooleanIterator iterator = (PrimitiveBooleanIterator) c.iterator();
            while (iterator.hasNext()) {
                result |= this.add(iterator.nextPrimitive());
            }
        } else {
            for (final Boolean e : c) {
                result |= this.add(e);
            }
        }

        return result;
    }

    @Override
    public boolean addAll(final boolean... a) {
        return this.addAll(a, 0, a.length);
    }

    @Override
    public boolean addAll(final boolean[] a, final int off) {
        return this.addAll(a, off, a.length - off);
    }

    @Override
    public boolean addAll(final boolean[] a, final int off, final int len) {
        boolean result = false;
        final int end = off + len;
        for (int i = off; i < end; i++) {
            result |= this.add(a[i]);
        }
        return result;
    }

    @Override
    public boolean contains(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Boolean)) {
            throw new IllegalArgumentException();
        }
        return this.contains((boolean) (Boolean) o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        if (c instanceof PrimitiveBooleanCollection) {
            final PrimitiveBooleanIterator iterator = (PrimitiveBooleanIterator) c.iterator();
            while (iterator.hasNext()) {
                if (!this.contains(iterator.nextPrimitive())) {
                    return false;
                }
            }
        } else {
            for (final Object e : c) {
                if (!this.contains(e)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean containsAll(final boolean... a) {
        return this.containsAll(a, 0, a.length);
    }

    @Override
    public boolean containsAll(final boolean[] a, final int off) {
        return this.containsAll(a, off, a.length - off);
    }

    @Override
    public boolean containsAll(final boolean[] a, final int off, final int len) {
        final int end = off + len;
        for (int i = off; i < end; i++) {
            if (!this.contains(a[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Boolean)) {
            throw new IllegalArgumentException();
        }
        return this.remove((boolean) (Boolean) o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean result = false;

        if (c instanceof PrimitiveBooleanCollection) {
            final PrimitiveBooleanIterator iterator = (PrimitiveBooleanIterator) c.iterator();
            while (iterator.hasNext()) {
                result |= this.remove(iterator.nextPrimitive());
            }
        } else {
            for (final Object e : c) {
                result |= this.remove(e);
            }
        }

        return result;
    }

    @Override
    public boolean removeAll(final boolean... a) {
        return this.removeAll(a, 0, a.length);
    }

    @Override
    public boolean removeAll(final boolean[] a, final int off) {
        return this.removeAll(a, off, a.length - off);
    }

    @Override
    public boolean removeAll(final boolean[] a, final int off, final int len) {
        boolean result = false;
        final int end = off + len;
        for (int i = off; i < end; i++) {
            result |= this.remove(a[i]);
        }
        return result;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean result = false;

        if (c instanceof PrimitiveBooleanCollection) {
            final PrimitiveBooleanIterator iterator = (PrimitiveBooleanIterator) c.iterator();
            while (iterator.hasNext()) {
                if (!c.contains(iterator.nextPrimitive())) {
                    iterator.remove();
                    result = true;
                }
            }
        } else {
            final Iterator<Boolean> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (!c.contains(iterator.next())) {
                    iterator.remove();
                    result = true;
                }
            }
        }

        return result;
    }

    @Override
    public abstract PrimitiveBooleanIterator iterator();

    @Override
    public boolean[] toPrimitiveArray() {
        final int size = this.size();
        final boolean[] result = new boolean[size];
        final PrimitiveBooleanIterator iterator = this.iterator();
        for (int i = 0; i < size; i++) {
            result[i] = iterator.nextPrimitive();
        }
        return result;
    }

}