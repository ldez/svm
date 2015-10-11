package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;

import at.stefl.commons.util.StateMachine;
import at.stefl.commons.util.collection.CharArrayQueue;

public class UntilCharSequenceReader extends CharwiseFilterReader implements StateMachine {

    private boolean found;

    private CharSequence charSequence;
    private CharArrayQueue queue;

    public UntilCharSequenceReader(final Reader in, final CharSequence charSequence) {
        super(in);

        if (charSequence == null) {
            throw new NullPointerException();
        }

        if (charSequence.length() > 0) {
            this.charSequence = charSequence;
            this.queue = new CharArrayQueue(charSequence.length());
        } else {
            this.found = true;
        }
    }

    @Override
    public int read() throws IOException {
        if (this.found) {
            return -1;
        }
        if (!this.queue.isEmpty()) {
            return this.queue.poll();
        }

        while (true) {
            final int read = this.in.read();
            if (read == -1) {
                break;
            }

            if (read != this.charSequence.charAt(this.queue.size())) {
                return read;
            }

            this.queue.add((char) read);
            if (this.queue.size() >= this.charSequence.length()) {
                break;
            }
        }

        this.found = true;
        return -1;
    }

    @Override
    public void reset() {
        this.found = false;
        this.queue.clear();
    }

}