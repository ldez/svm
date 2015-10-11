package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class FlushingOutputStream extends FilterOutputStream {

    public FlushingOutputStream(final OutputStream out) {
        super(out);
    }

    @Override
    public void write(final int b) throws IOException {
        this.out.write(b);
        this.out.flush();
    }

    @Override
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
        this.out.flush();
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
        this.out.flush();
    }

}