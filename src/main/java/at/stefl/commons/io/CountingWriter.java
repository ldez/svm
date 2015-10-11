package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class CountingWriter extends FilterWriter {

    private long count;

    public CountingWriter(final Writer out) {
        super(out);
    }

    public long count() {
        return this.count;
    }

    @Override
    public void write(final int c) throws IOException {
        this.out.write(c);
        this.count++;
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.out.write(cbuf);
        this.count += cbuf.length;
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        this.out.write(cbuf, off, len);
        this.count += len;
    }

    @Override
    public void write(final String str) throws IOException {
        this.out.write(str);
        this.count += str.length();
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        this.out.write(str, off, len);
        this.count += str.length();
    }

    @Override
    public Writer append(final char c) throws IOException {
        this.out.append(c);
        this.count++;
        return this;
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        if (csq == null) {
            csq = "null";
        }
        this.out.append(csq);
        this.count += csq.length();
        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        this.out.append(csq, start, end);
        this.count += end - start;
        return this;
    }

}