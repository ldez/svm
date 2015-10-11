package at.stefl.commons.util.collection;

import java.util.Collection;
import java.util.HashMap;

import at.stefl.commons.util.primitive.IntegerReference;

public class HashMultiset<E> extends MultisetWrapper<E> {

    public HashMultiset() {
        super(new HashMap<E, IntegerReference>());
    }

    public HashMultiset(final int initialCapacity) {
        super(new HashMap<E, IntegerReference>(initialCapacity));
    }

    public HashMultiset(final int initialCapacity, final float loadFactor) {
        super(new HashMap<E, IntegerReference>(initialCapacity, loadFactor));
    }

    public HashMultiset(final Collection<? extends E> c) {
        this(c.size());
        this.addAll(c);
    }

}