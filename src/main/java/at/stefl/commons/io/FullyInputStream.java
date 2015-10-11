package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class FullyInputStream extends DelegationInputStream {

    public FullyInputStream(final InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return ByteStreamUtil.readFully(this.in);
    }

    @Override
    public int read(final byte[] b) throws IOException {
        return ByteStreamUtil.readFully(this.in, b);
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return ByteStreamUtil.readFully(this.in, b, off, len);
    }

}