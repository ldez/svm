package at.stefl.commons.lwxml.writer;

import java.io.IOException;

import at.stefl.commons.lwxml.LWXMLEvent;

public abstract class LWXMLFilterWriter extends LWXMLWriter {

    protected final LWXMLWriter out;

    public LWXMLFilterWriter(final LWXMLWriter out) {
        if (out == null) {
            throw new NullPointerException();
        }

        this.out = out;
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.out.getCurrentEvent();
    }

    @Override
    public long getCurrentEventNumber() {
        return this.out.getCurrentEventNumber();
    }

    @Override
    public boolean isCurrentEventWritten() {
        return this.out.isCurrentEventWritten();
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