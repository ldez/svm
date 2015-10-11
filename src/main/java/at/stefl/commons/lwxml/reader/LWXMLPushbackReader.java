package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.LinkedList;

import at.stefl.commons.io.ClosedReader;
import at.stefl.commons.io.CountingReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalFollowerException;

// TODO: improve exception handling
public class LWXMLPushbackReader extends LWXMLFilterReader {

    // removed Deque because of Android 1.6
    // private Deque<LWXMLEvent> eventStack = new LinkedList<LWXMLEvent>();
    // private Deque<String> valueStack = new LinkedList<String>();
    private final LinkedList<LWXMLEvent> eventStack = new LinkedList<LWXMLEvent>();
    private final LinkedList<String> valueStack = new LinkedList<String>();

    private LWXMLEvent currentEvent;
    private long eventNumberDifference;
    private String currentValue;
    private Reader valueReader;
    private CountingReader countingReader;

    private boolean streamReading;

    public LWXMLPushbackReader(final LWXMLReader in) {
        super(in);
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.currentEvent;
    }

    @Override
    public long getCurrentEventNumber() {
        return this.in.getCurrentEventNumber() + this.eventNumberDifference;
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        this.streamReading = (this.eventStack.size() == 0);

        if (this.streamReading) {
            this.currentValue = null;
            this.countingReader = null;

            final LWXMLEvent tmp = this.in.readEvent();
            if ((this.currentEvent != null) && !this.currentEvent.isFollowingEvent(tmp)) {
                throw new LWXMLIllegalFollowerException(this.currentEvent, tmp);
            }

            this.currentEvent = tmp;
            this.valueReader = this.in;
        } else {
            this.currentEvent = this.eventStack.removeFirst();

            if (this.currentEvent.hasValue()) {
                this.currentValue = this.valueStack.removeFirst();

                if (this.currentValue != null) {
                    this.valueReader = new StringReader(this.currentValue);
                    this.countingReader = new CountingReader(this.valueReader);
                    this.valueReader = this.countingReader;
                } else {
                    this.valueReader = this.in;
                    this.streamReading = true;
                }
            } else {
                this.valueReader = ClosedReader.CLOSED_READER;
            }

            this.eventNumberDifference++;
        }

        return this.currentEvent;
    }

    public void unreadEvent() {
        this.unreadEventImpl(this.currentEvent, null);
    }

    public void unreadEvent(final LWXMLEvent event) {
        this.unreadEvent(event, null);
    }

    public void unreadEvent(final String value) {
        this.unreadEvent(this.currentEvent, value);
    }

    public void unreadEvent(final LWXMLEvent event, final String value) {
        if (event.hasValue() && (value == null)) {
            throw new IllegalArgumentException("value necessary");
        }
        if ((this.eventStack.size() != 0) && !event.isFollowingEvent(this.eventStack.getFirst())) {
            throw new LWXMLIllegalFollowerException(this.eventStack.getFirst(), event);
        }

        this.unreadEventImpl(event, value);
    }

    private void unreadEventImpl(final LWXMLEvent event, final String value) {
        this.eventStack.addFirst(event);
        if (event.hasValue()) {
            this.valueStack.addFirst(value);
        }

        this.eventNumberDifference--;
    }

    // TODO: implement in LWXMLReader
    public LWXMLEvent touchEvent() throws IOException {
        final LWXMLEvent result = this.readEvent();
        this.unreadEvent();
        return result;
    }

    @Override
    public String readValue() throws IOException {
        if (this.streamReading) {
            return this.in.readValue();
        }

        if (!LWXMLEvent.hasValue(this.currentEvent)) {
            return null;
        }
        return this.currentValue.substring((int) this.countingReader.count());
    }

    @Override
    public int read() throws IOException {
        return this.valueReader.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return this.valueReader.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return this.valueReader.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return this.valueReader.read(target);
    }

    @Override
    public long skip(final long n) throws IOException {
        return this.valueReader.skip(n);
    }

}