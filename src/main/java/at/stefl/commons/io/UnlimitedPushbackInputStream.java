package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.LinkedList;

// TODO: optimize
public class UnlimitedPushbackInputStream extends PushbackInputStream {

    // removed Deque because of Android 1.6
    // private Deque<Byte> buffer = new LinkedList<Byte>();
    private LinkedList<Byte> buffer = new LinkedList<Byte>();

    public UnlimitedPushbackInputStream(final InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        if (this.buffer.isEmpty()) {
            return super.read();
        } else {
            return this.buffer.removeFirst();
        }
    }

    @Override
    public int read(final byte[] b, int off, int len) throws IOException {
        int count = 0;

        while (!this.buffer.isEmpty()) {
            final int read = this.read();
            if (read == -1) {
                return count;
            }
            b[off] = (byte) read;
            off++;
            len--;
            count++;
        }

        if ((off < b.length) & (len > 0)) {
            count += super.read(b, off, len);
        }

        return count;
    }

    @Override
    public void unread(final int b) throws IOException {
        this.buffer.addLast((byte) b);
    }

    @Override
    public void unread(final byte[] b) throws IOException {
        for (final byte i : b) {
            this.buffer.addLast(i);
        }
    }

    @Override
    public void unread(final byte[] b, final int off, final int len) throws IOException {
        for (int i = off; i < (off + len); i++) {
            this.buffer.addLast(b[i]);
        }
    }

    @Override
    public int available() throws IOException {
        return this.buffer.size() + super.available();
    }

    @Override
    public void close() throws IOException {
        this.buffer = null;
        super.close();
    }

}