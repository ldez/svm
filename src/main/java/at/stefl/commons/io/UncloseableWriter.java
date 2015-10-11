package at.stefl.commons.io;

import java.io.IOException;
import java.io.Writer;

public class UncloseableWriter extends FilterWriter {

    public UncloseableWriter(final Writer out) {
        super(out);
    }

    @Override
    public void close() throws IOException {
        this.out = ClosedWriter.CLOSED_WRITER;
    }

}