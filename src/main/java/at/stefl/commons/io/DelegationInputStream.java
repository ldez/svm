package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public abstract class DelegationInputStream extends InputStream {

    protected InputStream in;

    public DelegationInputStream(final InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return this.in.read();
    }

    @Override
    public int read(final byte[] b) throws IOException {
        return this.in.read(b);
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return this.in.read(b, off, len);
    }

    @Override
    public long skip(final long n) throws IOException {
        return this.in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.in.available();
    }

    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }

    @Override
    public synchronized void mark(final int readlimit) {
        this.in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        this.in.reset();
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

}