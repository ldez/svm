package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

// TODO: make use of
public abstract class ProxyOutputStream extends DelegationOutputStream {

    public ProxyOutputStream(final OutputStream out) {
        super(out);
    }

    protected void beforeWrite(final int n) throws IOException {}

    protected void afterWrite(final int n) throws IOException {}

    protected void handleIOException(final IOException e) throws IOException {
        throw e;
    }

    @Override
    public void write(final int b) throws IOException {
        try {
            this.beforeWrite(1);
            this.out.write(b);
            this.afterWrite(1);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final byte[] b) throws IOException {
        try {
            this.beforeWrite(b.length);
            this.out.write(b);
            this.afterWrite(b.length);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        try {
            this.beforeWrite(len);
            this.out.write(b, off, len);
            this.afterWrite(len);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.out.flush();
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

}