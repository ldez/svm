package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

// TODO: improve
public class IgnoreCharacterReader extends CharwiseFilterReader {

    private final Set<Character> characterSet;

    public IgnoreCharacterReader(final Reader in, final char c) {
        super(in);

        this.characterSet = new HashSet<Character>(1);
        this.characterSet.add(c);
    }

    public IgnoreCharacterReader(final Reader in, final Set<Character> characterSet) {
        super(in);

        this.characterSet = new HashSet<Character>(characterSet);
    }

    @Override
    public int read() throws IOException {
        int read;

        do {
            read = this.in.read();
            if (read == -1) {
                return -1;
            }
        } while (this.characterSet.contains((char) read));

        return read;
    }

}