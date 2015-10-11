package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.stefl.commons.lwxml.LWXMLEvent;

public class LWXMLCountingReader extends LWXMLFilterReader {

    private final int[] counts = new int[LWXMLEvent.values().length];

    public LWXMLCountingReader(final LWXMLReader in) {
        super(in);
    }

    public int getCount(final LWXMLEvent event) {
        return this.counts[event.ordinal()];
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        final LWXMLEvent event = this.in.readEvent();
        this.counts[event.ordinal()]++;
        return event;
    }

    @Override
    public int read() throws IOException {
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return this.in.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return this.in.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return this.in.read(target);
    }

}