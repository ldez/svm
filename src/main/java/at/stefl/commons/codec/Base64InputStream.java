package at.stefl.commons.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import at.stefl.commons.io.CharStreamUtil;

// TODO: use bytes?
public class Base64InputStream extends InputStream {

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final Reader in;
    private boolean closed;

    private final Base64Settings settings;

    private final char[] inBuffer;
    private final byte[] outBuffer = new byte[3];
    private int outIndex = 0;
    private int outBuffered = 0;

    public Base64InputStream(final Reader in, final Base64Settings settings) {
        this(in, DEFAULT_BUFFER_SIZE, settings);
    }

    public Base64InputStream(final Reader in, final int bufferSize, final Base64Settings settings) {
        this.in = in;
        this.inBuffer = new char[bufferSize];
        this.settings = settings;
    }

    @Override
    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }

        if (this.outIndex >= this.outBuffered) {
            final int read = CharStreamUtil.readTireless(this.in, this.inBuffer, 0, 4);
            if (read == -1) {
                this.closed = true;
                return -1;
            }

            this.outIndex = 0;
            this.outBuffered = Base64.decode3Byte(this.inBuffer, this.outBuffer, this.settings);
        }

        return this.outBuffer[this.outIndex++];
    }

    @Override
    public int read(final byte[] b, int off, int len) throws IOException {
        if (this.closed) {
            return -1;
        }
        if (len == 0) {
            return 0;
        }

        int result = 0;

        int maxRead = Math.min(this.outBuffered - this.outIndex, len);
        System.arraycopy(this.outBuffer, this.outIndex, b, off, maxRead);
        this.outIndex += maxRead;
        off += maxRead;
        len -= maxRead;
        result += maxRead;

        while (len > 0) {
            final int lenMultiple3 = len + (((len % 3) != 0) ? 3 : 0);
            final int inLeft = (lenMultiple3 / 3) * 4;
            maxRead = Math.min(this.inBuffer.length, inLeft);
            final int read = CharStreamUtil.readTireless(this.in, this.inBuffer, 0, maxRead);
            if (read == -1) {
                this.closed = true;
                return -1;
            }
            Base64.decodeChars(this.inBuffer, 0, read, b, off, this.settings);
            final int decoded = this.settings.decodedSize(this.inBuffer);
            result += decoded;
            if (read < maxRead) {
                break;
            }
            off += decoded;
            len -= decoded;
        }

        if (result == 0) {
            return -1;
        }
        return result;
    }

    @Override
    public int available() {
        return this.outBuffered - this.outIndex;
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.in.close();
    }

}