package at.stefl.commons.io;

import java.io.Reader;

public abstract class FilterReader extends DelegationReader {

    public FilterReader(final Reader in) {
        super(in);
    }

}