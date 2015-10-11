package at.stefl.commons.util.collection;

import java.util.Map.Entry;

import at.stefl.commons.util.object.ObjectPreserver;
import at.stefl.commons.util.object.ObjectTransformer;

public class EntryWrapper<K1, V1, K2, V2> extends AbstractEntryWrapper<K1, V1, K2, V2> {

    private final ObjectTransformer<? super K1, ? extends K2> keyTransformer;
    private final ObjectPreserver<? super V1, V2> valueExtractor;

    public EntryWrapper(final Entry<K1, V1> entry, final ObjectTransformer<? super K1, ? extends K2> keyTransformer, final ObjectPreserver<? super V1, V2> valueExtractor) {
        super(entry);

        this.keyTransformer = keyTransformer;
        this.valueExtractor = valueExtractor;
    }

    @Override
    public K2 getKey() {
        return this.keyTransformer.transform(this.entry.getKey());
    }

    @Override
    public V2 getValue() {
        return this.valueExtractor.transform(this.entry.getValue());
    }

    @Override
    public V2 setValue(final V2 value) {
        final V2 tmp = this.getValue();
        this.valueExtractor.preserve(value, this.entry.getValue());
        return tmp;
    }

}