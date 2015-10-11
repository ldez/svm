package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class ClosedWriter extends Writer {

    public static final ClosedWriter CLOSED_WRITER = new ClosedWriter();

    private ClosedWriter() {}

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        throw new IOException("stream already closed");
    }

    @Override
    public void flush() throws IOException {}

    @Override
    public void close() throws IOException {}

}