package at.stefl.commons.lwxml.reader;

import java.io.IOException;
import java.nio.CharBuffer;

import at.stefl.commons.lwxml.LWXMLEvent;

public class LWXMLFlatReader extends LWXMLFilterReader {

    private LWXMLEvent lastEvent;
    private int depth;
    private boolean endEmptyElement;

    public LWXMLFlatReader(final LWXMLReader in) {
        super(in);
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        if (this.endEmptyElement) {
            return LWXMLEvent.END_EMPTY_ELEMENT;
        }
        return this.lastEvent;
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        this.endEmptyElement = false;

        boolean collapseEndElement = true;
        LWXMLEvent event;

        loop: while (true) {
            event = this.in.readEvent();

            switch (event) {
                case START_ELEMENT:
                    this.depth++;
                case ATTRIBUTE_NAME:
                case ATTRIBUTE_VALUE:
                case END_ATTRIBUTE_LIST:
                    if (this.depth == 1) {
                        break loop;
                    }
                    break;
                case END_EMPTY_ELEMENT:
                case END_ELEMENT:
                    this.depth--;

                    if (this.depth < 0) {
                        this.depth = 0;
                        collapseEndElement = false;
                    }

                    break;
                default:
                    break;
            }

            if (this.depth <= 0) {
                break;
            }
            collapseEndElement = false;
        }

        if (collapseEndElement && (event == LWXMLEvent.END_ELEMENT)) {
            this.endEmptyElement = true;
            return LWXMLEvent.END_EMPTY_ELEMENT;
        }

        return this.lastEvent = event;
    }

    @Override
    public int read() throws IOException {
        if (this.endEmptyElement) {
            return -1;
        }
        return this.in.read();
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        if (this.endEmptyElement) {
            return -1;
        }
        return this.in.read(cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.endEmptyElement) {
            return -1;
        }
        return this.in.read(cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        if (this.endEmptyElement) {
            return -1;
        }
        return this.in.read(target);
    }

    @Override
    public long skip(final long n) throws IOException {
        if (this.endEmptyElement) {
            return 0;
        }
        return this.in.skip(n);
    }

}