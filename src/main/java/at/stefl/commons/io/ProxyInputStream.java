package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

// TODO: make use of
public abstract class ProxyInputStream extends DelegationInputStream {

    public ProxyInputStream(final InputStream in) {
        super(in);
    }

    protected void beforeRead(final int n) throws IOException {}

    protected void afterRead(final int n) throws IOException {}

    protected void handleIOException(final IOException e) throws IOException {
        throw e;
    }

    @Override
    public int read() throws IOException {
        try {
            this.beforeRead(1);
            final int result = this.in.read();
            this.afterRead((result != -1) ? 1 : -1);
            return result;
        } catch (final IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(final byte[] b) throws IOException {
        try {
            this.beforeRead(b.length);
            final int result = this.in.read(b);
            this.afterRead(result);
            return result;
        } catch (final IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        try {
            this.beforeRead(len);
            final int result = this.in.read(b, off, len);
            this.afterRead(result);
            return result;
        } catch (final IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public long skip(final long n) throws IOException {
        try {
            return this.in.skip(n);
        } catch (final IOException e) {
            this.handleIOException(e);
            return 0;
        }
    }

    @Override
    public int available() throws IOException {
        try {
            return this.in.available();
        } catch (final IOException e) {
            this.handleIOException(e);
            return 0;
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.in.close();
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

}