package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class CloseableWriter extends FilterWriter {

    private boolean closed;

    public CloseableWriter(final Writer out) {
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
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.write(cbuf, off, len);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        this.out.write(str, off, len);
    }

    @Override
    public Writer append(final char c) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.out.append(c);
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.out.append(csq);
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        if (this.closed) {
            throw new StreamClosedException();
        }
        return this.out.append(csq, start, end);
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