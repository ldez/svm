package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.stefl.commons.lwxml.LWXMLEvent;

public class LWXMLElementReader extends LWXMLFilterReader {

    private boolean closed;

    private boolean first = true;
    private int depth;

    public LWXMLElementReader(final LWXMLReader in) {
        super(in);
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        if (this.closed) {
            return LWXMLEvent.END_DOCUMENT;
        }
        return this.in.getCurrentEvent();
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        if (this.closed) {
            return LWXMLEvent.END_DOCUMENT;
        }

        if (this.depth < 0) {
            this.closed = true;
            return LWXMLEvent.END_DOCUMENT;
        }

        final LWXMLEvent event = this.in.readEvent();

        switch (event) {
            case START_ELEMENT:
                if (!this.first) {
                    this.depth++;
                }
                break;
            case END_EMPTY_ELEMENT:
            case END_ELEMENT:
                this.depth--;
                break;
            case END_DOCUMENT:
                this.closed = true;
                return LWXMLEvent.END_DOCUMENT;
            default:
                break;
        }

        this.first = false;
        return event;
    }

    @Override
    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.in.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.in.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        if (this.closed) {
            return -1;
        }
        return this.in.read(target);
    }

    @Override
    public long skip(final long n) throws IOException {
        if (this.closed) {
            return 0;
        }
        return this.in.skip(n);
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.in.close();
    }

}