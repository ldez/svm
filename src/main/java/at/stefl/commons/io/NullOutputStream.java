package at.stefl.commons.io;

import java.io.OutputStream;

public class NullOutputStream extends OutputStream {

    public static final NullOutputStream NULL = new NullOutputStream();

    private NullOutputStream() {}

    @Override
    public void write(final int b) {}

    @Override
    public void write(final byte[] b) {}

    @Override
    public void write(final byte[] b, final int off, final int len) {}

}