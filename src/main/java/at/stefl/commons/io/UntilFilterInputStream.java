package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

import at.stefl.commons.util.StateMachine;

public class UntilFilterInputStream extends BytewiseFilterInputStream implements StateMachine {

    private boolean found;

    private final ByteFilter filter;

    public UntilFilterInputStream(final InputStream in, final ByteFilter filter) {
        super(in);

        this.filter = filter;
    }

    @Override
    public int read() throws IOException {
        if (this.found) {
            return -1;
        }

        final int read = this.in.read();

        if ((read == -1) || !this.filter.accept((byte) read)) {
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