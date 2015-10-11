package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class CloseableOutputStream extends FilterOutputStream {

    private boolean closed;

    public CloseableOutputStream(final OutputStream out) {
        super(out);
    }

    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public void write(final int c) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.write(c);
    }

    @Override
    public void write(final byte[] b) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.write(b);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.out.flush();
    }

}