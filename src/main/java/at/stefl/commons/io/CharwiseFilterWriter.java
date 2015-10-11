package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public abstract class CharwiseFilterWriter extends FilterWriter {

    public CharwiseFilterWriter(final Writer out) {
        super(out);
    }

    @Override
    public abstract void write(int c) throws IOException;

    @Override
    public void write(final char[] cbuf) throws IOException {
        CharStreamUtil.writeCharwise(this, cbuf);
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        CharStreamUtil.writeCharwise(this, cbuf, off, len);
    }

    @Override
    public void write(final String str) throws IOException {
        CharStreamUtil.writeCharwise(this, str);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        CharStreamUtil.writeCharwise(this, str, off, len);
    }

    @Override
    public Writer append(final char c) throws IOException {
        this.write(c);
        return this;
    }

    @Override
    public Writer append(final CharSequence csq) throws IOException {
        CharStreamUtil.appendCharwise(this, csq);
        return this;
    }

    @Override
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        CharStreamUtil.appendCharwise(this, csq, start, end);
        return this;
    }

}