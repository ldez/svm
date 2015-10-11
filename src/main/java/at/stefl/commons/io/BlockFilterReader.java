package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

public abstract class BlockFilterReader extends DelegationReader {

    private final char[] singleByte = new char[1];

    public BlockFilterReader(final Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return (this.read(this.singleByte, 0, 1) == -1) ? -1 : this.singleByte[0];
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return this.read(cbuf, 0, cbuf.length);
    }

    @Override
    public abstract int read(char[] cbuf, int off, int len) throws IOException;

}