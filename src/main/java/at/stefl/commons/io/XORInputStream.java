package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

// TODO: improve
public class XORInputStream extends BytewiseFilterInputStream {

    private byte[] key;
    private int index;

    public XORInputStream(final InputStream in, final byte[] key) {
        super(in);

        this.key = key;
    }

    public byte[] getKey() {
        return this.key;
    }

    public void setKey(final byte[] key) {
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        final int read = this.in.read();
        if (read == -1) {
            return -1;
        }

        final int result = read ^ this.key[this.index];
        this.index = (this.index + 1) % this.key.length;

        return result;
    }

}