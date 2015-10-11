package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class TeeOutputStream extends FilterOutputStream {

    private final OutputStream tee;
    private final boolean autoFlushTee;

    public TeeOutputStream(final OutputStream out, final OutputStream tee) {
        this(out, tee, false);
    }

    public TeeOutputStream(final OutputStream out, final OutputStream tee, final boolean autoFlushTee) {
        super(out);

        this.tee = tee;
        this.autoFlushTee = autoFlushTee;
    }

    @Override
    public void write(final int b) throws IOException {
        this.out.write(b);
        this.tee.write(b);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
        this.tee.write(b);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
        this.tee.write(b, off, len);
        if (this.autoFlushTee) {
            this.tee.flush();
        }
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
        this.tee.flush();
    }

}