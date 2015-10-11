package at.stefl.commons.io;

import java.io.Writer;

public class NullWriter extends Writer {

    public static final NullWriter NULL = new NullWriter();

    private NullWriter() {}

    @Override
    public void write(final int c) {}

    @Override
    public void write(final char[] cbuf) {}

    @Override
    public void write(final char[] cbuf, final int off, final int len) {}

    @Override
    public void write(final String str) {}

    @Override
    public void write(final String str, final int off, final int len) {}

    @Override
    public Writer append(final char c) {
        return this;
    }

    @Override
    public Writer append(final CharSequence csq) {
        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) {
        return this;
    }

    @Override
    public void flush() {}

    @Override
    public void close() {}

}