package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

import at.stefl.commons.util.StateMachine;

public class UntilCharReader extends CharwiseFilterReader implements StateMachine {

    private boolean found;

    private final char c;

    public UntilCharReader(final Reader in, final char c) {
        super(in);

        this.c = c;
    }

    @Override
    public int read() throws IOException {
        if (this.found) {
            return -1;
        }

        final int read = this.in.read();

        if ((read == -1) || (read == this.c)) {
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