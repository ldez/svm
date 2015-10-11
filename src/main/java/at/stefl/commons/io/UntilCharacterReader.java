package at.stefl.commons.io;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import at.stefl.commons.util.StateMachine;
import at.stefl.commons.util.array.ArrayUtil;

// TODO: improve (boxing crap)
public class UntilCharacterReader extends CharwiseFilterReader implements StateMachine {

    private boolean found;

    private final Set<Character> characterSet;

    public UntilCharacterReader(final Reader in, final char... characters) {
        super(in);

        this.characterSet = ArrayUtil.toHashSet(characters);
    }

    public UntilCharacterReader(final Reader in, final Set<Character> characterSet) {
        super(in);

        this.characterSet = new HashSet<Character>(characterSet);
    }

    @Override
    public int read() throws IOException {
        if (this.found) {
            return -1;
        }

        final int read = this.in.read();

        if ((read == -1) || this.characterSet.contains((char) read)) {
            this.found = true;
            return -1;
        }

        return read;
    }

    @Override
    public void reset() {
        this.found = false;
    }

}