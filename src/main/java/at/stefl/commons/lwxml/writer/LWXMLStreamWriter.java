package at.stefl.commons.lwxml.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalEventException;

// TODO: use xml escaping encoder
public class LWXMLStreamWriter extends LWXMLWriter {

    private boolean closed;
    private final Writer out;

    private LWXMLEvent lastEvent;
    private long eventNumber = -1;
    private boolean eventWritten;

    public LWXMLStreamWriter(final OutputStream out) {
        this(new BufferedWriter(new OutputStreamWriter(out)));
    }

    public LWXMLStreamWriter(final Writer out) {
        this.out = out;
    }

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

    private void finishLastEvent() throws IOException {
        this.finishLastEvent(null);
    }

    private void finishLastEvent(final LWXMLEvent nextEvent) throws IOException {
        if (this.lastEvent == null) {
            return;
        }
        if (this.eventWritten) {
            return;
        }

        switch (this.lastEvent) {
            case PROCESSING_INSTRUCTION_DATA:
                this.out.write("?>");
                break;
            case COMMENT:
                this.out.write("-->");
                break;
            case END_ELEMENT:
                this.out.write(">");
                break;
            case ATTRIBUTE_VALUE:
                this.out.write("\"");
                break;
            case END_ATTRIBUTE_LIST:
                if ((nextEvent != null) && (nextEvent != LWXMLEvent.END_EMPTY_ELEMENT)) {
                    this.out.write(">");
                }
                break;
            case CDATA:
                this.out.write("]]>");
                break;
            default:
                break;
        }

        this.eventWritten = true;
    }

    @Override
    public void writeEvent(final LWXMLEvent event) throws IOException {
        if (this.closed) {
            throw new LWXMLWriterException("already closed");
        }

        if (event == null) {
            throw new NullPointerException();
        }
        if (event == LWXMLEvent.END_DOCUMENT) {
            throw new LWXMLWriterException("cannot write event (" + event + ")");
        }

        if ((this.lastEvent != null) && !this.lastEvent.isFollowingEvent(event)) {
            throw new LWXMLWriterException("given event (" + event + ") cannot follow last event (" + this.lastEvent + ")");
        }

        this.finishLastEvent(event);

        this.eventWritten = false;

        switch (event) {
            case PROCESSING_INSTRUCTION_TARGET:
                this.out.write("<?");
                break;
            case PROCESSING_INSTRUCTION_DATA:
                this.out.write(" ");
                break;
            case COMMENT:
                this.out.write("<!--");
                break;
            case START_ELEMENT:
                this.out.write("<");
                break;
            case END_EMPTY_ELEMENT:
                this.out.write("/>");
                this.eventWritten = true;
                break;
            case END_ELEMENT:
                this.out.write("</");
                break;
            case ATTRIBUTE_NAME:
                this.out.write(" ");
                break;
            case ATTRIBUTE_VALUE:
                this.out.write("=\"");
                break;
            case END_ATTRIBUTE_LIST:
                break;
            case CHARACTERS:
                break;
            case CDATA:
                this.out.write("<![CDATA[");
                break;
            default:
                throw new LWXMLIllegalEventException(event);
        }

        this.lastEvent = event;
        this.eventNumber++;
    }

    private void checkWrite() {
        if (this.closed) {
            throw new LWXMLWriterException("already closed");
        }
        if (this.lastEvent == null) {
            throw new LWXMLWriterException("no current event");
        }
        if (!this.lastEvent.hasValue()) {
            throw new LWXMLWriterException("current event has no value");
        }
        if (this.eventWritten) {
            throw new LWXMLWriterException("value already written");
        }
    }

    @Override
    public void write(final int c) throws IOException {
        this.checkWrite();
        this.out.write(c);
    }

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.checkWrite();
        this.out.write(cbuf);
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        this.checkWrite();
        this.out.write(cbuf, off, len);
    }

    @Override
    public void write(final String str) throws IOException {
        this.checkWrite();
        this.out.write(str);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        this.checkWrite();
        this.out.write(str, off, len);
    }

    @Override
    public LWXMLWriter append(final char c) throws IOException {
        this.checkWrite();
        this.out.append(c);
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq) throws IOException {
        this.checkWrite();
        this.out.append(csq);
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq, final int start, final int end) throws IOException {
        this.checkWrite();
        this.out.append(csq, start, end);
        return this;
    }

    @Override
    public void flush() throws IOException {
        if (this.closed) {
            return;
        }

        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }

        try {
            this.finishLastEvent();
            this.out.flush();
            this.out.close();
        } finally {
            this.closed = true;
        }
    }

}