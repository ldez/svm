package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BlockFilterOutputStream extends DelegationOutputStream {

    private final byte[] singleByte = new byte[1];

    public BlockFilterOutputStream(final OutputStream in) {
        super(in);
    }

    @Override
    public void write(final int b) throws IOException {
        this.singleByte[0] = (byte) b;
        this.write(this.singleByte, 0, 1);
    }

    @Override
    public void write(final byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }

    @Override
    public abstract void write(byte[] b, int off, int len) throws IOException;

}