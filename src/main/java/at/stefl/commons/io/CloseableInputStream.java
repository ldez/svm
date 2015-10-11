package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class CloseableInputStream extends FilterInputStream {

    private boolean closed;

    public CloseableInputStream(final InputStream in) {
        super(in);
    }

    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public int read() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read();
    }

    @Override
    public int read(final byte[] b) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read(b);
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read(b, off, len);
    }

    @Override
    public int available() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.available();
    }

    @Override
    public void reset() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.in.reset();
    }

    @Override
    public synchronized void mark(final int readlimit) {
        if (this.closed) {
            return;
        }
        this.in.mark(readlimit);
    }

    @Override
    public long skip(final long n) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.skip(n);
    }

    @Override
    public void close() {
        this.closed = true;
    }

}