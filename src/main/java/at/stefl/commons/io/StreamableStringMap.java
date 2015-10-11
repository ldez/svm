package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import at.stefl.commons.util.collection.AbstractEntryWrapper;
import at.stefl.commons.util.collection.OrderedPair;
import at.stefl.commons.util.iterator.DelegationIterator;
import at.stefl.commons.util.string.AbstractCharSequence;
import at.stefl.commons.util.string.CharSequenceArrayWrapper;
import at.stefl.commons.util.string.CharSequenceWraper;

// TODO: improve: make use of object tools
// TODO: improve: re-implement for mapt, set, ... matching ability
// TODO: override HashMap?
public class StreamableStringMap<V> extends AbstractMap<String, V> {

    private static class EntryWrapper<V> extends AbstractEntryWrapper<AbstractCharSequence, OrderedPair<String, V>, String, V> {

        public EntryWrapper(final Entry<AbstractCharSequence, OrderedPair<String, V>> entry) {
            super(entry);
        }

        @Override
        public String getKey() {
            return this.entry.getKey().toString();
        }

        @Override
        public V getValue() {
            return this.entry.getValue().getElement2();
        }

        @Override
        public V setValue(final V value) {
            final V result = this.entry.getValue().getElement2();
            this.entry.setValue(this.entry.getValue().setElement2(value));
            return result;
        }
    }

    private class EntrySetIterator extends DelegationIterator<Entry<AbstractCharSequence, OrderedPair<String, V>>, Entry<String, V>> {

        public EntrySetIterator() {
            super(StreamableStringMap.this.map.entrySet().iterator());
        }

        @Override
        public Entry<String, V> next() {
            return new EntryWrapper<V>(this.iterator.next());
        }
    }

    private class EntrySet extends AbstractSet<Entry<String, V>> {

        @Override
        public Iterator<Entry<String, V>> iterator() {
            return new EntrySetIterator();
        }

        @Override
        public boolean add(final Entry<String, V> e) {
            StreamableStringMap.this.put(e.getKey(), e.getValue());
            return true;
        }

        @Override
        public boolean remove(final Object o) {
            final boolean result = StreamableStringMap.this.containsKey(o);
            if (result) {
                StreamableStringMap.this.remove(o);
            }
            return result;
        }

        @Override
        public boolean contains(final Object o) {
            return StreamableStringMap.this.containsKey(o);
        }

        @Override
        public int size() {
            return StreamableStringMap.this.map.size();
        }
    }

    private final HashMap<AbstractCharSequence, OrderedPair<String, V>> map;

    private EntrySet entrySet;

    private int bufferSize;
    private char[] buffer;

    public StreamableStringMap() {
        this.map = new HashMap<AbstractCharSequence, OrderedPair<String, V>>();
    }

    public StreamableStringMap(final int capacity) {
        this.map = new HashMap<AbstractCharSequence, OrderedPair<String, V>>(capacity);
    }

    public StreamableStringMap(final Map<? extends String, ? extends V> map) {
        this(map.size());

        this.putAll(map);
    }

    @Override
    public V put(final String key, final V value) {
        if (key == null) {
            throw new NullPointerException();
        }

        final int len = key.length();
        if (len > this.bufferSize) {
            this.bufferSize = len;
            if (this.buffer != null) {
                this.buffer = new char[this.bufferSize];
            }
        }

        final OrderedPair<String, V> result = this.map.put(new CharSequenceWraper(key), new OrderedPair<String, V>(key, value));
        return (result == null) ? null : result.getElement2();
    }

    @Override
    public V remove(final Object key) {
        if (!(key instanceof String)) {
            throw new ClassCastException();
        }
        return this.remove((String) key);
    }

    public V remove(final String key) {
        final OrderedPair<String, V> result = this.map.remove(new CharSequenceWraper(key));
        return (result == null) ? null : result.getElement2();
    }

    @Override
    public boolean containsKey(final Object key) {
        if (!(key instanceof String)) {
            throw new ClassCastException();
        }
        return this.containsKey((String) key);
    }

    public boolean containsKey(final String key) {
        return this.map.containsKey(new CharSequenceWraper(key));
    }

    @Override
    public V get(final Object key) {
        if (!(key instanceof String)) {
            throw new ClassCastException();
        }
        return this.get((String) key);
    }

    public V get(final String key) {
        final OrderedPair<String, V> result = this.get((CharSequence) key);
        return (result == null) ? null : result.getElement2();
    }

    private OrderedPair<String, V> get(final CharSequence charSequence) {
        return this.map.get(new CharSequenceWraper(charSequence));
    }

    public OrderedPair<String, V> match(final Reader in) throws IOException {
        if (this.buffer == null) {
            this.buffer = new char[this.bufferSize];
        }

        final int read = CharStreamUtil.readTireless(in, this.buffer);

        final Object matcher = new CharSequenceArrayWrapper(this.buffer, 0, read);
        return this.map.get(matcher);
    }

    @Override
    public Set<Entry<String, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet();
        }
        return this.entrySet;
    }

    public void clearBuffer() {
        this.bufferSize = 0;
        this.buffer = null;
    }

    @Override
    public void clear() {
        this.map.clear();
        this.entrySet = null;

        this.clearBuffer();
    }

}