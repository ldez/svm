package at.stefl.commons.util.collection.primitive;

public class SimplePrimitiveBooleanSet extends AbstractPrimitiveBooleanSet {

    private boolean containsFalse;
    private boolean containsTrue;

    @Override
    public boolean isEmpty() {
        return !this.containsFalse & !this.containsTrue;
    }

    @Override
    public boolean isFull() {
        return this.containsFalse & this.containsTrue;
    }

    @Override
    public int size() {
        return (this.containsFalse ? 1 : 0) + (this.containsTrue ? 1 : 0);
    }

    @Override
    public boolean add(final boolean e) {
        if (e) {
            return this.containsTrue ^ (this.containsTrue = true);
        }
        return this.containsFalse ^ (this.containsFalse = true);
    }

    @Override
    public boolean contains(final boolean e) {
        if (e) {
            return this.containsTrue;
        }
        return this.containsFalse;
    }

    @Override
    public boolean remove(final boolean e) {
        if (e) {
            return this.containsTrue ^ (this.containsTrue = true);
        }
        return this.containsFalse ^ (this.containsFalse = true);
    }

    @Override
    public PrimitiveBooleanIterator iterator() {
        // TODO: implement
        return null;
    }

}