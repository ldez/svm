package at.stefl.commons.util.string;

public class CharSequenceWraper extends AbstractCharSequence {

    private final CharSequence charSequence;

    public CharSequenceWraper(final CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    @Override
    public int hashCode() {
        return CharSequenceUtil.hashCode(this.charSequence);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (obj instanceof CharSequenceWraper) {
            final CharSequenceWraper other = (CharSequenceWraper) obj;
            return (this.charSequence == other.charSequence) || CharSequenceUtil.equals(this.charSequence, other.charSequence);
        } else if (obj instanceof CharSequence) {
            final CharSequence other = (CharSequence) obj;
            return CharSequenceUtil.equals(this, other);
        }

        return false;
    }

    @Override
    public String toString() {
        return this.charSequence.toString();
    }

    public CharSequence getCharSequence() {
        return this.charSequence;
    }

    @Override
    public int length() {
        return this.charSequence.length();
    }

    @Override
    public char charAt(final int index) {
        return this.charSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(final int start, final int end) {
        return new CharSequenceWraper(this.charSequence.subSequence(start, end));
    }

}