package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BytewiseFilterOutputStream extends FilterOutputStream {

    public BytewiseFilterOutputStream(final OutputStream out) {
        super(out);
    }

    @Override
    public abstract void write(int b) throws IOException;

    @Override
    public void write(final byte[] b) throws IOException {
        ByteStreamUtil.writeBytewise(this, b, 0, b.length);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        ByteStreamUtil.writeBytewise(this, b, off, len);
    }

}