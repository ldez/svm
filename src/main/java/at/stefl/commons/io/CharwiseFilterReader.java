package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class CharwiseFilterReader extends FilterReader {

    public CharwiseFilterReader(final Reader in) {
        super(in);
    }

    @Override
    public abstract int read() throws IOException;

    @Override
    public int read(final char[] cbuf) throws IOException {
        return CharStreamUtil.readCharwise(this, cbuf, 0, cbuf.length);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return CharStreamUtil.readCharwise(this, cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return CharStreamUtil.readCharwise(this, target);
    }

    @Override
    public long skip(final long n) throws IOException {
        return CharStreamUtil.skipCharwise(this, n);
    }

}