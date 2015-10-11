package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public abstract class BytewiseFilterInputStream extends FilterInputStream {

    public BytewiseFilterInputStream(final InputStream in) {
        super(in);
    }

    @Override
    public abstract int read() throws IOException;

    @Override
    public int read(final byte[] b) throws IOException {
        return ByteStreamUtil.readBytewise(this, b, 0, b.length);
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return ByteStreamUtil.readBytewise(this, b, off, len);
    }

    @Override
    public long skip(final long n) throws IOException {
        return ByteStreamUtil.skipBytewise(this, n);
    }

}