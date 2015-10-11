package at.stefl.commons.util.collection;

public class OrderedPair<E1, E2> {

    private final E1 element1;
    private final E2 element2;

    public OrderedPair(final E1 element1, final E2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    @Override
    public String toString() {
        return "(" + this.element1 + ", " + this.element2 + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof OrderedPair)) {
            return false;
        }
        final OrderedPair<?, ?> other = (OrderedPair<?, ?>) obj;

        return ((this.element1 == null) ? (other.element1 == null) : this.element1.equals(other.element1)) && ((this.element2 == null) ? (other.element2 == null) : this.element2.equals(other.element2));
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + ((this.element1 == null) ? 0 : this.element1.hashCode());
        result = 31 * result + ((this.element2 == null) ? 0 : this.element2.hashCode());

        return result;
    }

    public E1 getElement1() {
        return this.element1;
    }

    public E2 getElement2() {
        return this.element2;
    }

    public OrderedPair<E1, E2> setElement1(final E1 element1) {
        return new OrderedPair<E1, E2>(element1, this.element2);
    }

    public OrderedPair<E1, E2> setElement2(final E2 element2) {
        return new OrderedPair<E1, E2>(this.element1, element2);
    }

}