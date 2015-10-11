package at.stefl.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class ApplyFilterOutputStream extends BytewiseFilterOutputStream {

    private final ByteFilter filter;

    public ApplyFilterOutputStream(final OutputStream out, final ByteFilter filter) {
        super(out);

        this.filter = filter;
    }

    @Override
    public void write(final int b) throws IOException {
        if (this.filter.accept((byte) b)) {
            this.out.write(b);
        }
    }

}