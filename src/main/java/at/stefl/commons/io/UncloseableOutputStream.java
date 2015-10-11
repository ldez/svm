package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class UncloseableOutputStream extends FilterOutputStream {

    public UncloseableOutputStream(final OutputStream out) {
        super(out);
    }

    @Override
    public void close() throws IOException {
        this.out = ClosedOutputStream.CLOSED_OUTPUT_STREAM;
    }

}