package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class ApplyFilterWriter extends CharwiseFilterWriter {

    private final CharFilter filter;

    public ApplyFilterWriter(final Writer out, final CharFilter filter) {
        super(out);

        this.filter = filter;
    }

    @Override
    public void write(final int c) throws IOException {
        if (this.filter.accept((char) c)) {
            this.out.write(c);
        }
    }

}