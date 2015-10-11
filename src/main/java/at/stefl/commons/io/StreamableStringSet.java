package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

import at.stefl.commons.util.collection.OrderedPair;

public class StreamableStringSet extends AbstractSet<String> {

    private static final Object VALUE = new Object();

    private final StreamableStringMap<Object> map;

    public StreamableStringSet() {
        this.map = new StreamableStringMap<Object>();
    }

    public StreamableStringSet(final int capacity) {
        this.map = new StreamableStringMap<Object>(capacity);
    }

    public StreamableStringSet(final Collection<? extends String> c) {
        this(c.size());

        this.addAll(c);
    }

    public StreamableStringSet(final StreamableStringMap<?> map) {
        this(map.size());

        this.addAll(map.keySet());
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public Iterator<String> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public boolean contains(final Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public boolean add(final String e) {
        return this.map.put(e, VALUE) == null;
    }

    @Override
    public boolean remove(final Object o) {
        return this.map.remove(o) != null;
    }

    public String match(final Reader in) throws IOException {
        final OrderedPair<String, Object> match = this.map.match(in);
        return (match == null) ? null : match.getElement1();
    }

    public void clearBuffer() {
        this.map.clearBuffer();
    }

    @Override
    public void clear() {
        this.map.clear();
    }

}