package at.stefl.commons.util.string;

public class CharSequenceArrayWrapper extends AbstractCharSequence {

    private final char[] array;
    private final int off;
    private final int len;
    private final int end;

    public CharSequenceArrayWrapper(final char[] array) {
        this(array, 0, array.length);
    }

    public CharSequenceArrayWrapper(final char[] array, final int off, final int len) {
        if (off < 0) {
            throw new IndexOutOfBoundsException("off < 0");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("len < 0");
        }
        this.end = off + len;
        if (this.end > array.length) {
            throw new IndexOutOfBoundsException("end > len");
        }

        this.array = array;
        this.off = off;
        this.len = len;
    }

    @Override
    public String toString() {
        return new String(this.array, this.off, this.len);
    }

    @Override
    public int length() {
        return this.len;
    }

    @Override
    public char charAt(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < 0");
        }
        index += this.off;
        if (index > this.end) {
            throw new IndexOutOfBoundsException("index > end");
        }
        return this.array[index];
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return new CharSequenceArrayWrapper(this.array, this.off + start, end - start);
    }

}