package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream extends FilterInputStream {

    private long count;

    public CountingInputStream(final InputStream in) {
        super(in);
    }

    public long count() {
        return this.count;
    }

    @Override
    public int read() throws IOException {
        final int read = this.in.read();
        if (read != -1) {
            this.count++;
        }
        return read;
    }

    @Override
    public int read(final byte[] b) throws IOException {
        final int read = this.in.read(b);
        if (read != -1) {
            this.count += read;
        }
        return read;
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int read = this.in.read(b, off, len);
        if (read != -1) {
            this.count += read;
        }
        return read;
    }

    @Override
    public long skip(final long n) throws IOException {
        final long skipped = this.in.skip(n);
        this.count += skipped;
        return skipped;
    }

}