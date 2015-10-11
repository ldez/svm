package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

// TODO: improve
public class FluidInputStreamReader extends Reader {

    private final InputStream in;

    private final Charset charset;
    private final CharsetDecoder decoder;

    private final ByteBuffer inBuffer;
    private final CharBuffer outBuffer;

    private boolean closed;

    public FluidInputStreamReader(final InputStream in) {
        this(in, Charset.defaultCharset());
    }

    public FluidInputStreamReader(final InputStream in, final Charset charset) {
        this.in = in;

        this.charset = charset;
        this.decoder = charset.newDecoder();

        this.inBuffer = ByteBuffer.allocate((int) Math.ceil(charset.newEncoder().maxBytesPerChar()));
        this.outBuffer = CharBuffer.allocate((int) Math.ceil(this.decoder.maxCharsPerByte()));
    }

    public FluidInputStreamReader(final InputStream in, final String charset) {
        this(in, Charset.forName(charset));
    }

    public Charset getCharset() {
        return this.charset;
    }

    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public int read() throws IOException {
        if (this.closed) {
            return -1;
        }

        if (!this.outBuffer.hasRemaining() || (this.outBuffer.position() == 0)) {
            this.decoder.reset();
            this.outBuffer.clear();

            int read;
            CoderResult coderResult;

            while (true) {
                read = this.in.read();

                if (read == -1) {
                    this.closed = true;
                } else {
                    this.inBuffer.put((byte) read);
                }

                this.inBuffer.flip();

                coderResult = this.decoder.decode(this.inBuffer, this.outBuffer, this.closed);

                if (coderResult.isUnderflow()) {
                    if (this.closed) {
                        break;
                    }
                    if (this.outBuffer.position() > 0) {
                        break;
                    }
                } else if (coderResult.isOverflow()) {
                    break;
                } else {
                    if (this.inBuffer.limit() == this.inBuffer.capacity()) {
                        coderResult.throwException();
                    }
                }

                this.inBuffer.position(this.inBuffer.limit());
                this.inBuffer.limit(this.inBuffer.capacity());
            }

            this.inBuffer.clear();
            this.outBuffer.flip();
        }

        return (this.outBuffer.hasRemaining()) ? this.outBuffer.get() : -1;
    }

    @Override
    public int read(final char[] cbuf) throws IOException {
        return CharStreamUtil.readCharwise(this, cbuf);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        return CharStreamUtil.readCharwise(this, cbuf, off, len);
    }

    @Override
    public int read(final CharBuffer target) throws IOException {
        return CharStreamUtil.readCharwise(this, target);
    }

    @Override
    public long skip(final long n) throws IOException {
        return CharStreamUtil.skipCharwise(this, n);
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