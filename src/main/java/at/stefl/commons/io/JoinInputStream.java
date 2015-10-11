package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

// TODO: improve
public class JoinInputStream extends InputStream {

    private final InputStream[] ins;
    private int index;

    public JoinInputStream(final InputStream... ins) {
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
    public int read(final byte[] b) throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read(b);
        } while (result == -1);

        return result;
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        int result;

        do {
            if (!this.isStreamAvailable()) {
                return -1;
            }
            result = this.ins[this.index].read(b, off, len);
        } while (result == -1);

        return result;
    }

    // TODO: improve
    @Override
    public long skip(final long n) throws IOException {
        if (!this.isStreamAvailable()) {
            return 0;
        }

        return ByteStreamUtil.skipBytewise(this, n);
    }

    @Override
    public int available() throws IOException {
        if (!this.isStreamAvailable()) {
            return 0;
        }

        return this.ins[this.index].available();
    }

    // TODO: improve
    @Override
    public boolean markSupported() {
        return false;
    }

    // TODO: improve
    @Override
    public synchronized void mark(final int readlimit) {}

    // TODO: improve
    @Override
    public synchronized void reset() throws IOException {}

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