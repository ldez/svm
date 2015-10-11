package at.stefl.commons.util.collection;

public class SimpleOrderedPair<E> extends OrderedPair<E, E> {

    public SimpleOrderedPair(final E element1, final E element2) {
        super(element1, element2);
    }

    @Override
    public SimpleOrderedPair<E> setElement1(final E element1) {
        return new SimpleOrderedPair<E>(element1, this.getElement2());
    }

    @Override
    public SimpleOrderedPair<E> setElement2(final E element2) {
        return new SimpleOrderedPair<E>(this.getElement1(), element2);
    }

}