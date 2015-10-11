package at.stefl.commons.util.iterator;

import java.lang.reflect.Array;

public class ArrayIterator<E> extends AbstractIterator<E> {

    private final Object array;
    private final int length;
    private int index;

    // TODO: type safe
    public ArrayIterator(final Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("not an array");
        }

        this.array = array;
        this.length = Array.getLength(array);
    }

    @Override
    public boolean hasNext() {
        return this.index < this.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
        return (E) Array.get(this.array, this.index++);
    }

}