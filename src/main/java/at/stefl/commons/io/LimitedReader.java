package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class LimitedReader extends DelegationReader {

    private long left;

    public LimitedReader(final Reader in, final long limit) {
        super(in);

        if (limit < 0) {
            throw new IllegalArgumentException("limit < 0");
        }
        this.left = limit;
    }

    public long left() {
        return this.left;
    }

    @Override
    public int read() throws IOException {
        if (this.left <= 0) {
            return -1;
        }
        this.left--;
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return this.read(cbuf, 0, cbuf.length);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.left <= 0) {
            return -1;
        }
        final int result = this.in.read(cbuf, off, (int) Math.min(this.left, len));
        this.left -= result;
        return result;
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        if (this.left <= 0) {
            return -1;
        }
        final CharBuffer newTarget = target.duplicate();
        target.limit((int) Math.min(target.position() + this.left, target.limit()));
        final int result = this.in.read(newTarget);
        target.position(target.position() + result);
        this.left -= result;
        return result;
    }

    @Override
    public boolean ready() throws IOException {
        return (this.left > 0) && this.in.ready();
    }

}