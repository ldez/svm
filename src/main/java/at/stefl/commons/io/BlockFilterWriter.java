package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public abstract class BlockFilterWriter extends DelegationWriter {

    private final char[] singleByte = new char[1];

    public BlockFilterWriter(final Writer in) {
        super(in);
    }

    @Override
    public void write(final int b) throws IOException {
        this.singleByte[0] = (char) b;
        this.write(this.singleByte, 0, 1);
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.write(cbuf, 0, cbuf.length);
    }

    @Override
    public abstract void write(char[] cbuf, int off, int len) throws IOException;

}