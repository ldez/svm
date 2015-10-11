package at.stefl.commons.lwxml.writer;

import at.stefl.commons.lwxml.LWXMLEvent;

public class LWXMLNullWriter extends LWXMLWriter {

    public static final LWXMLNullWriter NULL = new LWXMLNullWriter();

    private LWXMLEvent currentEvent;
    private long eventNumber = -1;

    public LWXMLNullWriter() {}

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.currentEvent;
    }

    @Override
    public long getCurrentEventNumber() {
        return this.eventNumber;
    }

    @Override
    public boolean isCurrentEventWritten() {
        return true;
    }

    @Override
    public void writeEvent(final LWXMLEvent event) {
        this.currentEvent = event;
        this.eventNumber++;
    }

    @Override
    public void write(final int c) {}

    @Override
    public void write(final char[] cbuf, final int off, final int len) {}

}