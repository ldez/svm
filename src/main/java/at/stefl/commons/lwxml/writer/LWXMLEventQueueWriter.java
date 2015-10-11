package at.stefl.commons.lwxml.writer;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.commons.util.string.CharSequenceUtil;

// TODO: implement better solution (-> growable array)
public class LWXMLEventQueueWriter extends LWXMLWriter {

    private static final LWXMLEvent[] EVENT_ARRAY = LWXMLEvent.values();

    private class EventQueueReader extends LWXMLReader {
        private int position;
        private LWXMLEvent event;
        private long eventNumber = -1;
        private Reader reader;

        private final int revision;

        public EventQueueReader() {
            this.revision = LWXMLEventQueueWriter.this.revision;
        }

        private void checkRevision() {
            if (this.revision != LWXMLEventQueueWriter.this.revision) {
                throw new IllegalStateException("stream was reset");
            }
        }

        @Override
        public LWXMLEvent getCurrentEvent() {
            return this.event;
        }

        @Override
        public long getCurrentEventNumber() {
            return this.eventNumber;
        }

        @Override
        public LWXMLEvent readEvent() throws IOException {
            this.checkRevision();

            if (this.position >= LWXMLEventQueueWriter.this.eventCount) {
                return LWXMLEvent.END_DOCUMENT;
            }
            this.position++;

            this.event = EVENT_ARRAY[LWXMLEventQueueWriter.this.eventArray[this.position]];
            final int value = LWXMLEventQueueWriter.this.valueArray[this.position];
            if (value != -1) {
                final int offset = LWXMLEventQueueWriter.this.offsetArray[value];
                final int length = LWXMLEventQueueWriter.this.lengthArray[value];
                this.reader = new CharArrayReader(LWXMLEventQueueWriter.this.charArray, offset, length);
            } else {
                this.reader = null;
            }

            this.eventNumber++;

            return this.event;
        }

        @Override
        public int read() throws IOException {
            this.checkRevision();
            if (this.reader == null) {
                return -1;
            }
            return this.reader.read();
        }

        @Override
        public int read(final char[] cbuf) throws IOException {
            this.checkRevision();
            if (this.reader == null) {
                return -1;
            }
            return this.reader.read(cbuf);
        }

        @Override
        public int read(final char[] cbuf, final int off, final int len) throws IOException {
            this.checkRevision();
            if (this.reader == null) {
                return -1;
            }
            return this.reader.read(cbuf, off, len);
        }

        @Override
        public int read(final CharBuffer target) throws IOException {
            this.checkRevision();
            if (this.reader == null) {
                return -1;
            }
            return this.reader.read(target);
        }
    }

    public static final int DEFAULT_INITIAL_EVENT_CAPACITY = 10;
    public static final double DEFAULT_VALUES_ON_EVENT = 0.9;
    public static final double DEFAULT_CHARS_ON_VALUE = 6;

    private final double valuesOnEvent;
    private final double charsOnValue;

    private int[] eventArray;
    private int[] valueArray;
    private int[] offsetArray;
    private int[] lengthArray;
    private char[] charArray;

    private int eventCount;
    private int valueCount;
    private int charCount;

    private LWXMLEvent lastEvent;
    private long eventNumber = -1;
    private boolean eventWritten;

    private int revision;

    public LWXMLEventQueueWriter() {
        this(DEFAULT_INITIAL_EVENT_CAPACITY, DEFAULT_CHARS_ON_VALUE, DEFAULT_VALUES_ON_EVENT);
    }

    public LWXMLEventQueueWriter(final int initialEventCapacity, final double valuesOnEvent, final double charsOnValue) {
        this.valuesOnEvent = valuesOnEvent;
        this.charsOnValue = charsOnValue;

        this.eventArray = new int[initialEventCapacity];
        this.valueArray = new int[initialEventCapacity];

        final int initialValueCapacity = (int) (initialEventCapacity * valuesOnEvent);
        this.offsetArray = new int[initialValueCapacity];
        this.lengthArray = new int[initialValueCapacity];

        final int initialCharCapacity = (int) (initialValueCapacity * charsOnValue);
        this.charArray = new char[initialCharCapacity];
    }

    private void ensureEventCapacity(final int need) {
        final int minSize = this.eventCount + need;
        this.eventArray = ArrayUtil.growGeometric(this.eventArray, minSize, 2);
        this.valueArray = ArrayUtil.growGeometric(this.valueArray, minSize, 2);
    }

    private void ensureValueCapacity(final int need) {
        final int minSize = Math.max(this.valueCount + need, (int) (this.eventCount * this.valuesOnEvent));
        this.offsetArray = ArrayUtil.growGeometric(this.offsetArray, minSize, 2);
        this.lengthArray = ArrayUtil.growGeometric(this.lengthArray, minSize, 2);
    }

    private void ensureCharCapacity(final int need) {
        final int minSize = Math.max(this.charCount + need, (int) (this.valueCount * this.charsOnValue));
        this.charArray = ArrayUtil.growGeometric(this.charArray, minSize, 2);
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

    public int getEventCount() {
        return this.eventCount;
    }

    public int getValueCount() {
        return this.valueCount;
    }

    public int getCharCount() {
        return this.charCount;
    }

    public LWXMLReader getReader() {
        return new EventQueueReader();
    }

    private void finishLastEvent() {
        if (this.lastEvent == null) {
            return;
        }
        if (this.eventWritten) {
            return;
        }

        if (this.lastEvent.hasValue()) {
            this.lengthArray[this.valueCount] = this.charCount - this.offsetArray[this.valueCount];
            this.valueCount++;
        }

        this.eventCount++;
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

        this.ensureEventCapacity(1);
        this.eventArray[this.eventCount] = event.ordinal();
        this.valueArray[this.eventCount] = event.hasValue() ? this.valueCount : -1;

        if (event.hasValue()) {
            this.ensureValueCapacity(1);
            this.offsetArray[this.valueCount] = this.charCount;
        }

        this.lastEvent = event;
        this.eventNumber++;
        this.eventWritten = false;
    }

    private void checkWrite(final int len) {
        if (this.lastEvent == null) {
            throw new LWXMLWriterException("no current event");
        }
        if (!this.lastEvent.hasValue()) {
            throw new LWXMLWriterException("current event has no value");
        }
        if (this.eventWritten) {
            throw new LWXMLWriterException("value is already written");
        }

        this.ensureCharCapacity(len);
    }

    @Override
    public void write(final int c) {
        this.checkWrite(1);
        this.charArray[this.charCount] = (char) c;
        this.charCount++;
    }

    @Override
    public void write(final char[] cbuf) {
        this.write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(final char[] cbuf, final int off, final int len) {
        this.checkWrite(len);
        System.arraycopy(cbuf, off, this.charArray, this.charCount, len);
        this.charCount += len;
    }

    @Override
    public void write(final String str) {
        this.write(str, 0, str.length());
    }

    @Override
    public void write(final String str, final int off, final int len) {
        this.checkWrite(len);
        str.getChars(off, off + len, this.charArray, this.charCount);
        this.charCount += len;
    }

    @Override
    public LWXMLEventQueueWriter append(final char c) {
        this.write(c);
        return this;
    }

    @Override
    public LWXMLEventQueueWriter append(final CharSequence csq) {
        return this.append(csq, 0, csq.length());
    }

    @Override
    public LWXMLEventQueueWriter append(final CharSequence csq, final int start, final int end) {
        final int length = end - start;
        this.checkWrite(length);
        CharSequenceUtil.copy(csq, this.charArray, start, end);
        this.charCount += length;
        return this;
    }

    public void write(final LWXMLReader in) throws IOException {
        final CharStreamUtil streamUtil = new CharStreamUtil();

        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                return;
            }

            this.writeEvent(event);
            streamUtil.writeStream(in, this);
        }
    }

    public void writeTo(final LWXMLWriter out) throws IOException {
        this.finishLastEvent();

        for (int i = 0; i < this.eventCount; i++) {
            final LWXMLEvent event = EVENT_ARRAY[this.eventArray[i]];
            out.writeEvent(event);

            if (event.hasValue()) {
                final int value = this.valueArray[i];
                out.write(this.charArray, this.offsetArray[value], this.lengthArray[value]);
            }
        }
    }

    public void reset() {
        this.eventCount = 0;
        this.valueCount = 0;
        this.charCount = 0;

        this.lastEvent = null;
        this.eventWritten = false;

        this.revision++;
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