package at.stefl.commons.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.LinkedList;

// TODO: optimize
public class UnlimitedPushbackReader extends PushbackReader {

    // removed Deque because of Android 1.6
    // private Deque<Character> buffer = new LinkedList<Character>();
    private LinkedList<Character> buffer = new LinkedList<Character>();

    public UnlimitedPushbackReader(final Reader in) {
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
    public int read(final char[] cbuf, int off, int len) throws IOException {
        int count = 0;

        while (!this.buffer.isEmpty()) {
            final int read = this.read();
            if (read == -1) {
                return count;
            }
            cbuf[off] = (char) read;
            off++;
            len--;
            count++;
        }

        if ((off < cbuf.length) & (len > 0)) {
            count += super.read(cbuf, off, len);
        }

        return count;
    }

    @Override
    public void unread(final int c) throws IOException {
        this.buffer.addLast((char) c);
    }

    @Override
    public void unread(final char[] cbuf) throws IOException {
        for (final char c : cbuf) {
            this.buffer.addLast(c);
        }
    }

    @Override
    public void unread(final char[] cbuf, final int off, final int len) throws IOException {
        for (int i = off; i < (off + len); i++) {
            this.buffer.addLast(cbuf[i]);
        }
    }

    @Override
    public boolean ready() throws IOException {
        return !this.buffer.isEmpty() || super.ready();
    }

    @Override
    public void close() throws IOException {
        this.buffer = null;
        super.close();
    }

}