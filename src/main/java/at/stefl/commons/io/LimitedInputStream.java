package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends DelegationInputStream {

    private long left;

    public LimitedInputStream(final InputStream in, final long limit) {
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
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        if (this.left <= 0) {
            return -1;
        }
        final int result = this.in.read(b, off, (int) Math.min(this.left, len));
        this.left -= result;
        return result;
    }

    @Override
    public int available() throws IOException {
        return (int) Math.min(this.left, this.in.available());
    }

}