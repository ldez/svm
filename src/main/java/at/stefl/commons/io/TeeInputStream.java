package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TeeInputStream extends FilterInputStream {

    private final OutputStream tee;

    public TeeInputStream(final InputStream in, final OutputStream tee) {
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
    public int read(final byte[] b) throws IOException {
        final int read = this.in.read(b);

        if (read != -1) {
            this.tee.write(b, 0, read);
            this.tee.flush();
        }

        return read;
    }

    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int read = this.in.read(b, off, len);

        if (read != -1) {
            this.tee.write(b, off, read);
            this.tee.flush();
        }

        return read;
    }

}