package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

public class CountingReader extends FilterReader {

    private long count;

    public CountingReader(final Reader in) {
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
    public int read(final char[] cbuf) throws IOException {
        final int read = this.in.read(cbuf);
        if (read != -1) {
            this.count += read;
        }
        return read;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        final int read = this.in.read(cbuf, off, len);
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