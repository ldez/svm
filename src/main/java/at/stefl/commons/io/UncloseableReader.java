package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

public class UncloseableReader extends FilterReader {

    public UncloseableReader(final Reader in) {
        super(in);
    }

    @Override
    public void close() throws IOException {
        this.in = ClosedReader.CLOSED_READER;
    }

}