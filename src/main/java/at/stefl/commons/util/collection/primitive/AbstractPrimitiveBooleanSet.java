package at.stefl.commons.util.collection.primitive;

import java.util.Collection;

public abstract class AbstractPrimitiveBooleanSet extends AbstractPrimitiveBooleanCollection implements PrimitiveBooleanSet {

    public abstract boolean isFull();

    @Override
    public boolean addAll(final Collection<? extends Boolean> c) {
        if (this.isFull()) {
            return false;
        }

        if (c instanceof PrimitiveBooleanCollection) {
            final PrimitiveBooleanCollection pc = (PrimitiveBooleanCollection) c;
            return ((pc.contains(false)) ? this.add(false) : false) | ((pc.contains(true)) ? this.add(true) : false);
        } else {
            return ((c.contains(false)) ? this.add(false) : false) | ((c.contains(true)) ? this.add(true) : false);
        }
    }

}