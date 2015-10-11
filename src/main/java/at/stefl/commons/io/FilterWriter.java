package at.stefl.commons.io;

import java.io.Writer;

public abstract class FilterWriter extends DelegationWriter {

    public FilterWriter(final Writer out) {
        super(out);
    }

}