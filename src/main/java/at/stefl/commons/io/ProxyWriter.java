package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

// TODO: make use of
public abstract class ProxyWriter extends DelegationWriter {

    public ProxyWriter(final Writer out) {
        super(out);
    }

    protected void beforeWrite(final int n) throws IOException {}

    protected void afterWrite(final int n) throws IOException {}

    protected void handleIOException(final IOException e) throws IOException {
        throw e;
    }

    @Override
    public void write(final int c) throws IOException {
        try {
            this.beforeWrite(1);
            this.out.write(c);
            this.afterWrite(1);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        try {
            this.beforeWrite(cbuf.length);
            this.out.write(cbuf);
            this.afterWrite(cbuf.length);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        try {
            this.beforeWrite(len);
            this.out.write(cbuf, off, len);
            this.afterWrite(len);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final String str) throws IOException {
        try {
            this.beforeWrite(str.length());
            this.out.write(str);
            this.afterWrite(str.length());
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        try {
            this.beforeWrite(len);
            this.out.write(str, off, len);
            this.afterWrite(len);
        } catch (final IOException e) {
            this.handleIOException(e);
        }
    }

    @Override
    public Writer append(final char c) throws IOException {
        try {
            this.beforeWrite(1);
            this.out.append(c);
            this.afterWrite(1);
        } catch (final IOException e) {
            this.handleIOException(e);
        }

        return this;
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        try {
            this.beforeWrite(csq.length());
            this.out.append(csq);
            this.afterWrite(csq.length());
        } catch (final IOException e) {
            this.handleIOException(e);
        }

        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        try {
            this.beforeWrite(end - start);
            this.out.append(csq, start, end);
            this.afterWrite(end - start);
        } catch (final IOException e) {
            this.handleIOException(e);
        }

        return this;
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