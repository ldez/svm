package at.stefl.commons.lwxml.writer;

import java.io.IOException;
import java.io.Reader;

import at.stefl.commons.io.TeeReader;
import at.stefl.commons.lwxml.LWXMLEvent;

// TODO: implement specific methods?
public class LWXMLTeeWriter extends LWXMLFilterWriter {

    private final LWXMLWriter tee;
    private final boolean autoFlushTee;

    public LWXMLTeeWriter(final LWXMLWriter out, final LWXMLWriter tee) {
        this(out, tee, false);
    }

    public LWXMLTeeWriter(final LWXMLWriter out, final LWXMLWriter tee, final boolean autoFlushTee) {
        super(out);

        this.tee = tee;
        this.autoFlushTee = autoFlushTee;
    }

    @Override
    public void writeEvent(final LWXMLEvent event) throws IOException {
        this.out.writeEvent(event);
        this.tee.writeEvent(event);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final int c) throws IOException {
        this.out.write(c);
        this.tee.write(c);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.out.write(cbuf);
        this.tee.write(cbuf);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        this.out.write(cbuf, off, len);
        this.tee.write(cbuf, off, len);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public int write(final Reader in) throws IOException {
        final int result = this.out.write(new TeeReader(in, this.tee));
        if (this.autoFlushTee) {
            this.tee.flush();
        }
        return result;
    }

    @Override
    public void write(final String str) throws IOException {
        this.out.write(str);
        this.tee.write(str);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        this.out.write(str, off, len);
        this.tee.write(str, off, len);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public LWXMLWriter append(final char c) throws IOException {
        this.out.append(c);
        this.tee.append(c);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq) throws IOException {
        this.out.append(csq);
        this.tee.append(csq);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq, final int start, final int end) throws IOException {
        this.out.append(csq, start, end);
        this.tee.append(csq, start, end);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
        return this;
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
        this.tee.flush();
    }

}