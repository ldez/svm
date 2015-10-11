package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

// TODO: make use of
public class ProxyReader extends DelegationReader {

    public ProxyReader(final Reader in) {
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
    public int read(final char[] cbuf) throws IOException {
        try {
            this.beforeRead(cbuf.length);
            final int result = this.in.read(cbuf);
            this.afterRead(result);
            return result;
        } catch (final IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        try {
            this.beforeRead(len);
            final int result = this.in.read(cbuf, off, len);
            this.afterRead(result);
            return result;
        } catch (final IOException e) {
            this.handleIOException(e);
            return -1;
        }
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        try {
            this.beforeRead(target.remaining());
            final int result = this.in.read(target);
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
    public boolean ready() throws IOException {
        try {
            return this.in.ready();
        } catch (final IOException e) {
            this.handleIOException(e);
            return false;
        }
    }

    @Override
    public void mark(final int readAheadLimit) throws IOException {
        try {
            this.in.mark(readAheadLimit);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void reset() throws IOException {
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