package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

// TODO: improve
public class XOROutputStream extends BytewiseFilterOutputStream {

    private byte[] key;
    private int index;

    public XOROutputStream(final OutputStream out, final byte[] key) {
        super(out);

        this.key = key;
    }

    public byte[] getKey() {
        return this.key;
    }

    public void setKey(final byte[] key) {
        this.key = key;
    }

    @Override
    public void write(final int b) throws IOException {
        this.out.write(b ^ this.key[this.index]);

        this.index = (this.index + 1) % this.key.length;
    }

}