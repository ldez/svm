package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class FullyReader extends DelegationReader {

    public FullyReader(final Reader in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        return CharStreamUtil.readFully(this.in);
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return CharStreamUtil.readFully(this.in, cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return CharStreamUtil.readFully(this.in, cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return CharStreamUtil.readFully(this.in, target);
    }

}