package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

import at.stefl.commons.util.StateMachine;

public class UntilFilterReader extends CharwiseFilterReader implements StateMachine {

    private boolean found;

    private final CharFilter filter;

    public UntilFilterReader(final Reader in, final CharFilter filter) {
        super(in);

        this.filter = filter;
    }

    @Override
    public int read() throws IOException {
        if (this.found) {
            return -1;
        }

        final int read = this.in.read();

        if ((read == -1) || !this.filter.accept((char) read)) {
            this.found = true;
            return -1;
        }

        return read;
    }

    @Override
    public void reset() {
        this.found = false;
    }

}