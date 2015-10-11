package at.stefl.commons.codec;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

// TODO: encode block-wise
// TODO: use bytes?
public class Base64OutputStream extends OutputStream {

    private final Writer out;
    private boolean closed;

    private final Base64Settings settings;

    private final byte[] inBuffer = new byte[3];
    private final char[] outBuffer = new char[4];
    private int index;

    public Base64OutputStream(final Writer out, final Base64Settings settings) {
        this.out = out;
        this.settings = settings;
    }

    @Override
    public void write(final int b) throws IOException {
        if (this.closed) {
            throw new IOException("stream is already closed");
        }

        this.inBuffer[this.index++] = (byte) b;

        if (this.index >= 3) {
            Base64.encode3Byte(this.inBuffer, this.outBuffer, this.settings);
            this.out.write(this.outBuffer);
            this.index = 0;
        }
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;

        if (this.index > 0) {
            Base64.encode3Byte(this.inBuffer, this.index, this.outBuffer, this.settings);
            this.out.write(this.outBuffer);
            this.out.flush();
        }

        this.out.close();
    }

}