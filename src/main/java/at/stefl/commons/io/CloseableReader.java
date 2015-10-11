package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class CloseableReader extends FilterReader {

    private boolean closed;

    public CloseableReader(final Reader in) {
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
    public int read(final char[] cbuf) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read(cbuf);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read(target);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.read(cbuf, off, len);
    }

    @Override
    public boolean ready() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.in.ready();
    }

    @Override
    public void reset() throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.in.reset();
    }

    @Override
    public void mark(final int readAheadLimit) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.in.mark(readAheadLimit);
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