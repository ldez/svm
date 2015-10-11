package at.stefl.commons.lwxml.reader;

import java.io.IOException;

import at.stefl.commons.lwxml.LWXMLEvent;

// TODO: improve
public abstract class LWXMLFilterReader extends LWXMLReader {

    protected final LWXMLReader in;

    public LWXMLFilterReader(final LWXMLReader in) {
        if (in == null) {
            throw new NullPointerException();
        }

        this.in = in;
    }

    @Override
    public LWXMLEvent getCurrentEvent() {
        return this.in.getCurrentEvent();
    }

    @Override
    public long getCurrentEventNumber() {
        return this.in.getCurrentEventNumber();
    }

    @Override
    public boolean ready() throws IOException {
        return this.in.ready();
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

}