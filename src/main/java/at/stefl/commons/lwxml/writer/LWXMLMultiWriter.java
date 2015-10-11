package at.stefl.commons.lwxml.writer;

import java.io.IOException;
import java.util.Iterator;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.util.iterator.ArrayIterator;

public class LWXMLMultiWriter extends LWXMLWriter implements Iterable<LWXMLWriter> {

    private final LWXMLWriter[] outs;

    public LWXMLMultiWriter(final LWXMLWriter[] outs) {
        final int len = outs.length;
        if (len == 0) {
            throw new IllegalArgumentException("empty array");
        }

        this.outs = new LWXMLWriter[len];
        System.arraycopy(outs, 0, this.outs, 0, len);
    }

    @Override
    public Iterator<LWXMLWriter> iterator() {
        return new ArrayIterator<LWXMLWriter>(this.outs);
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.outs[0].getCurrentEvent();
    }

    @Override
    public long getCurrentEventNumber() {
        return this.outs[0].getCurrentEventNumber();
    }

    @Override
    public boolean isCurrentEventWritten() {
        return this.outs[0].isCurrentEventWritten();
    }

    @Override
    public void writeEvent(final LWXMLEvent event) throws IOException {
        for (final LWXMLWriter out : this.outs) {
            out.writeEvent(event);
        }
    }

    @Override
    public void write(final int c) throws IOException {
        for (final LWXMLWriter out : this.outs) {
            out.write(c);
        }
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        for (final LWXMLWriter out : this.outs) {
            out.write(cbuf, off, len);
        }
    }

    @Override
    public void flush() throws IOException {
        for (final LWXMLWriter out : this.outs) {
            out.flush();
        }
    }

    @Override
    public void close() throws IOException {
        for (final LWXMLWriter out : this.outs) {
            out.close();
        }
    }

}