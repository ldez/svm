package at.stefl.commons.lwxml.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.DividedCharArrayWriter;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLReader;

// TODO: implement reader
public class LWXMLEventListWriter extends LWXMLWriter {

    // removed Deque because of Android 1.6
    // private Deque<LWXMLEvent> eventList = new LinkedList<LWXMLEvent>();
    // private Deque<String> valueList = new LinkedList<String>();
    private final LinkedList<LWXMLEvent> eventList = new LinkedList<LWXMLEvent>();
    private final LinkedList<String> valueList = new LinkedList<String>();
    private final DividedCharArrayWriter valueWriter = new DividedCharArrayWriter();

    private LWXMLEvent lastEvent;
    private long eventNumber;
    private boolean eventWritten;

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.lastEvent;
    }

    @Override
    public long getCurrentEventNumber() {
        return this.eventNumber;
    }

    @Override
    public boolean isCurrentEventWritten() {
        return this.eventWritten;
    }

    public int getEventCount() {
        return this.eventList.size();
    }

    public int getValueCount() {
        return this.valueList.size();
    }

    public List<LWXMLEvent> toEventList() {
        return new ArrayList<LWXMLEvent>(this.eventList);
    }

    public List<String> toValueList() {
        return new ArrayList<String>(this.valueList);
    }

    private void finishLastEvent() {
        if (this.eventWritten) {
            return;
        }

        if (LWXMLEvent.hasValue(this.lastEvent)) {
            this.valueList.addLast(this.valueWriter.toString());
            this.valueWriter.reset();
        }

        this.eventWritten = true;
    }

    @Override
    public void writeEvent(final LWXMLEvent event) {
        if (event == null) {
            throw new NullPointerException();
        }
        if (event == LWXMLEvent.END_DOCUMENT) {
            throw new LWXMLWriterException("cannot write event (" + event + ")");
        }

        if ((this.lastEvent != null) && !this.lastEvent.isFollowingEvent(event)) {
            throw new LWXMLWriterException("given event (" + event + ") cannot follow last event (" + this.lastEvent + ")");
        }

        this.finishLastEvent();
        this.eventList.addLast(event);

        this.lastEvent = event;
        this.eventNumber++;
        this.eventWritten = !event.hasValue();
    }

    private void checkWrite() {
        if (this.lastEvent == null) {
            throw new LWXMLWriterException("no current event");
        }
        if (!this.lastEvent.hasValue()) {
            throw new LWXMLWriterException("current event has no value");
        }
        if (this.eventWritten) {
            throw new LWXMLWriterException("value is already written");
        }
    }

    @Override
    public void write(final int c) {
        this.checkWrite();
        this.valueWriter.write(c);
    }

    @Override
    public void write(final char[] cbuf) {
        this.checkWrite();
        this.valueWriter.write(cbuf);
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) {
        this.checkWrite();
        this.valueWriter.write(cbuf, off, len);
    }

    @Override
    public void write(final String str) {
        this.checkWrite();
        this.valueWriter.write(str);
    }

    @Override
    public void write(final String str, final int off, final int len) {
        this.checkWrite();
        this.valueWriter.write(str, off, len);
    }

    @Override
    public LWXMLEventListWriter append(final char c) {
        this.checkWrite();
        this.valueWriter.append(c);
        return this;
    }

    @Override
    public LWXMLEventListWriter append(final CharSequence csq) {
        this.checkWrite();
        this.valueWriter.append(csq);
        return this;
    }

    @Override
    public LWXMLEventListWriter append(final CharSequence csq, final int start, final int end) {
        this.checkWrite();
        this.valueWriter.append(csq, start, end);
        return this;
    }

    public void write(final LWXMLReader in) throws IOException {
        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                return;
            }

            CharStreamUtil.writeStreamBuffered(in, this);
        }
    }

    public void writeTo(final LWXMLWriter out) throws IOException {
        this.finishLastEvent();

        final Iterator<String> valueIterator = this.valueList.iterator();

        for (final LWXMLEvent event : this.eventList) {
            out.writeEvent(event);
            if (event.hasValue()) {
                out.write(valueIterator.next());
            }
        }
    }

    public void reset() {
        this.eventList.clear();
        this.valueList.clear();
        this.valueWriter.reset();

        this.lastEvent = null;
        this.eventWritten = false;
    }

    @Override
    public void flush() {
        this.finishLastEvent();
    }

    @Override
    public void close() {
        this.flush();
    }

}