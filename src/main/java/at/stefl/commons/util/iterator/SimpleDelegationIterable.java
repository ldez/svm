package at.stefl.commons.util.iterator;

public abstract class SimpleDelegationIterable<T> extends DelegationIterable<T, T> {

    public SimpleDelegationIterable(final Iterable<? extends T> iterable) {
        super(iterable);
    }

}