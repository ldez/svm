package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public abstract class BlockFilterInputStream extends DelegationInputStream {

    private final byte[] singleByte = new byte[1];

    public BlockFilterInputStream(final InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return (ByteStreamUtil.readTireless(this.in, this.singleByte, 0, 1) == -1) ? -1 : this.singleByte[0];
    }

    @Override
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public abstract int read(byte[] b, int off, int len) throws IOException;

}