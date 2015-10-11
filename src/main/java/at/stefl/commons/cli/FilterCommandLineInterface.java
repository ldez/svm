package at.stefl.commons.cli;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class FilterCommandLineInterface implements CommandLineInterface {

    protected final CommandLineInterface src;
    protected final InputStream in;
    protected final OutputStream out;

    public FilterCommandLineInterface(final CommandLineInterface src) throws IOException {
        this.src = src;
        this.in = this.getFilterInputStream(src.getInputStream());
        this.out = this.getFilterOutputStream(src.getOutputStream());
    }

    protected InputStream getFilterInputStream(final InputStream in) {
        return in;
    }

    protected OutputStream getFilterOutputStream(final OutputStream out) {
        return out;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.in;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return this.out;
    }

    @Override
    public void close() throws IOException {
        this.src.close();
    }

}