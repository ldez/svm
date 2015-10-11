package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class FlushingWriter extends FilterWriter {

    public FlushingWriter(final Writer out) {
        super(out);
    }

    @Override
    public void write(final int c) throws IOException {
        this.out.write(c);
        this.out.flush();
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.out.write(cbuf);
        this.out.flush();
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        this.out.write(cbuf, off, len);
        this.out.flush();
    }

    @Override
    public void write(final String str) throws IOException {
        this.out.write(str);
        this.out.flush();
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        this.out.write(str, off, len);
        this.out.flush();
    }

    @Override
    public Writer append(final char c) throws IOException {
        this.out.append(c);
        this.out.flush();
        return this;
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        this.out.append(csq);
        this.out.flush();
        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        this.out.append(csq, start, end);
        this.out.flush();
        return this;
    }

}