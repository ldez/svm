package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public abstract class DelegationWriter extends Writer {

    protected Writer out;

    public DelegationWriter(final Writer out) {
        this.out = out;
    }

    @Override
    public void write(final int c) throws IOException {
        this.out.write(c);
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.out.write(cbuf);
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        this.out.write(cbuf, off, len);
    }

    @Override
    public void write(final String str) throws IOException {
        this.out.write(str);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        this.out.write(str, off, len);
    }

    @Override
    public Writer append(final char c) throws IOException {
        return this.out.append(c);
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        return this.out.append(csq);
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        return this.out.append(csq, start, end);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }

}