package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

public class ApplyFilterReader extends CharwiseFilterReader {

    private final CharFilter filter;

    public ApplyFilterReader(final Reader in, final CharFilter filter) {
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
        } while (!this.filter.accept((char) read));

        return read;
    }

}