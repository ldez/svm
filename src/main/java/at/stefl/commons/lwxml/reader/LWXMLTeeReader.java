package at.stefl.commons.lwxml.reader;

import java.io.IOException;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLTeeReader extends LWXMLFilterReader {

    private final LWXMLWriter tee;

    public LWXMLTeeReader(final LWXMLReader in, final LWXMLWriter tee) {
        super(in);

        this.tee = tee;
    }

    @Override
    public LWXMLEvent readEvent() throws IOException {
        final LWXMLEvent result = this.in.readEvent();

        if (result != LWXMLEvent.END_DOCUMENT) {
            this.tee.writeEvent(result);
            this.tee.flush();
        }

        return result;
    }

    @Override
    public String readValue() throws IOException {
        final String result = this.in.readValue();

        if (result != null) {
            this.tee.write(result);
            this.tee.flush();
        }

        return result;
    }

    @Override
    public int read() throws IOException {
        final int result = this.in.read();
        this.tee.write(result);
        this.tee.flush();
        return result;
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        final int result = this.in.read(cbuf);
        this.tee.write(cbuf, 0, result);
        this.tee.flush();
        return result;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        final int result = this.in.read(cbuf, off, len);
        this.tee.write(cbuf, off, result);
        this.tee.flush();
        return result;
    }

    // TODO: implement
    // @Override
    // public int read(CharBuffer target) throws IOException {}

}