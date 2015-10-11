package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;

public class ApplyFilterInputStream extends BytewiseFilterInputStream {

    private final ByteFilter filter;

    public ApplyFilterInputStream(final InputStream in, final ByteFilter filter) {
        super(in);

        this.filter = filter;
    }

    @Override
    public int read() throws IOException {
        int read;

        do {
            read = this.in.read();
            if (read == -1) {
                return -1;
            }
        } while (!this.filter.accept((byte) read));

        return read;
    }

}