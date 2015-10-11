package at.stefl.commons.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// TODO: improve
public class InputStreamPipe {

    private final InputStream in;
    private final OutputStream out;

    private final Thread pipeThread = new Thread() {

        @Override
        public void run() {
            try {
                int read;
                while (((read = InputStreamPipe.this.in.read()) != -1) && !Thread.interrupted()) {
                    final byte[] bytes = new byte[1 + InputStreamPipe.this.in.available()];
                    bytes[0] = (byte) read;
                    InputStreamPipe.this.in.read(bytes, 1, bytes.length - 1);

                    InputStreamPipe.this.out.write(bytes);
                    InputStreamPipe.this.out.flush();
                }
            } catch (final IOException e) {}
        }
    };

    public InputStreamPipe(final InputStream in, final OutputStream out) {
        this.in = in;
        this.out = out;
        this.pipeThread.start();
    }

    public void join() throws InterruptedException {
        this.pipeThread.join();
    }

    public void close() {
        this.pipeThread.interrupt();

        try {
            this.join();
        } catch (final InterruptedException e) {}
    }

}