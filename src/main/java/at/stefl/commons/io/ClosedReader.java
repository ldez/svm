package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ClosedReader extends Reader {

    public static final ClosedReader CLOSED_READER = new ClosedReader();

    private ClosedReader() {}

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return -1;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return -1;
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return -1;
    }

    @Override
    public long skip(final long n) throws IOException {
        return 0;
    }

    @Override
    public boolean ready() throws IOException {
        return true;
    }

    @Override
    public void close() throws IOException {}

}