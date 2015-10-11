package at.stefl.commons.util.collection;

import java.util.Map;
import java.util.Map.Entry;

import at.stefl.commons.util.object.ObjectUtil;

public abstract class AbstractEntry<K, V> implements Entry<K, V> {

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(this.getKey()) ^ ObjectUtil.hashCode(this.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (!(o instanceof Map.Entry)) {
            return false;
        }
        final Map.Entry<?, ?> other = (Map.Entry<?, ?>) o;

        return ObjectUtil.equals(this.getKey(), other.getKey()) && ObjectUtil.equals(this.getValue(), other.getValue());
    }

    @Override
    public String toString() {
        return this.getKey() + "=" + this.getValue();
    }

    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException();
    }

}