package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class DelegationReader extends Reader {

    protected Reader in;

    public DelegationReader(final Reader in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return this.in.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return this.in.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return this.in.read(target);
    }

    @Override
    public long skip(final long n) throws IOException {
        return this.in.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return this.in.ready();
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

    @Override
    public void mark(final int readAheadLimit) throws IOException {
        this.in.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        this.in.reset();
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

}