package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream extends FilterOutputStream {

    private long count;

    public CountingOutputStream(final OutputStream out) {
        super(out);
    }

    public long count() {
        return this.count;
    }

    @Override
    public void write(final int b) throws IOException {
        this.out.write(b);
        this.count++;
    }

    @Override
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
        this.count += b.length;
    }

    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
        this.count += len;
    }

}