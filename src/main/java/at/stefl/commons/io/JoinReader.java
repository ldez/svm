package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Arrays;

// TODO: improve
public class JoinReader extends Reader {

    private final Reader[] ins;
    private int index;

    public JoinReader(final Reader... ins) {
        this.ins = Arrays.copyOf(ins, ins.length);
    }

    public int getStreamIndex() {
        return this.index;
    }

    public int getStreamCount() {
        return this.ins.length;
    }

    public boolean isStreamAvailable() {
        return this.index < this.ins.length;
    }

    @Override
    public int read() throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read();
        } while (result == -1);

        return result;
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read(cbuf);
        } while (result == -1);

        return result;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read(cbuf, off, len);
        } while (result == -1);

        return result;
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read(target);
        } while (result == -1);

        return result;
    }

    // TODO: improve
    @Override
    public long skip(final long n) throws IOException {
        if (!this.isStreamAvailable()) {
            return 0;
        }

        return CharStreamUtil.skipCharwise(this, n);
    }

    @Override
    public boolean ready() throws IOException {
        if (!this.isStreamAvailable()) {
            return false;
        }

        return this.ins[this.index].ready();
    }

    // TODO: improve
    @Override
    public boolean markSupported() {
        return false;
    }

    // TODO: improve
    @Override
    public void mark(final int readAheadLimit) throws IOException {}

    // TODO: improve
    @Override
    public void reset() throws IOException {}

    @Override
    public void close() throws IOException {
        IOException last = null;

        for (; this.index < this.ins.length; this.index++) {
            try {
                this.ins[this.index].close();
            } catch (final IOException e) {
                last = e;
            }
        }

        if (last != null) {
            throw last;
        }
    }

}