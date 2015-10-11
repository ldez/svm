package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TeeReader extends FilterReader {

    private final Writer tee;

    public TeeReader(final Reader in, final Writer tee) {
        super(in);

        this.tee = tee;
    }

    @Override
    public int read() throws IOException {
        final int read = this.in.read();

        if (read != -1) {
            this.tee.write(read);
            this.tee.flush();
        }

        return read;
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        final int read = this.in.read(cbuf);

        if (read != -1) {
            this.tee.write(cbuf, 0, read);
            this.tee.flush();
        }

        return read;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        final int read = this.in.read(cbuf, off, len);

        if (read != -1) {
            this.tee.write(cbuf, off, read);
            this.tee.flush();
        }

        return read;
    }

    // TODO: implement
    // @Override
    // public int read(CharBuffer target) throws IOException {}

}