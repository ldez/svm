package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLUtil;

public class LWXMLElementDelegationReader extends LWXMLFilterReader {

    private LWXMLEvent lastEvent;

    private LWXMLElementReader ein;

    public LWXMLElementDelegationReader(final LWXMLReader in) {
        super(in);
    }

    public LWXMLElementReader getElementReader() {
        if (this.ein == null) {
            this.ein = new LWXMLElementReader(this.in);
        }
        return this.ein;
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        if (this.ein != null) {
            LWXMLUtil.flush(this.ein);
            this.ein = null;
        }

        return this.lastEvent = this.in.readEvent();
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.lastEvent;
    }

    @Override
    public int read() throws IOException {
        if (this.ein != null) {
            throw new IllegalStateException();
        }
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        if (this.ein != null) {
            throw new IllegalStateException();
        }
        return this.in.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.ein != null) {
            throw new IllegalStateException();
        }
        return this.in.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        if (this.ein != null) {
            throw new IllegalStateException();
        }
        return this.in.read(target);
    }

    @Override
    public long skip(final long n) throws IOException {
        if (this.ein != null) {
            throw new IllegalStateException();
        }
        return this.in.skip(n);
    }

    @Override
    public void close() throws IOException {
        this.in.close();

        if (this.ein != null) {
            this.ein.close();
            this.ein = null;
        }
    }

}