package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DelegationOutputStream extends OutputStream {

    protected OutputStream out;

    public DelegationOutputStream(final OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(final int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }

}