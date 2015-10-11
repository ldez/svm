package at.stefl.commons.util.string;

public class CharSequenceUtil {

    public static final CharSequence NULL = StringUtil.NULL;

    public static int hashCode(final CharSequence charSequence) {
        int result = 0;
        final int len = charSequence.length();

        for (int i = 0; i < len; i++) {
            result = 31 * result + charSequence.charAt(i);
        }

        return result;
    }

    public static boolean equals(final CharSequence a, final CharSequence b) {
        final int len = a.length();
        if (len != b.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final CharSequence a, final int aOff, final CharSequence b, final int bOff, final int len) {
        for (int i = 0; i < len; i++) {
            if (a.charAt(aOff + i) != b.charAt(bOff + i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final CharSequence a, final char[] b) {
        final int len = a.length();
        if (len != b.length) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean equals(final CharSequence a, final int aOff, final char[] b, final int bOff, final int len) {
        for (int i = 0; i < len; i++) {
            if (a.charAt(aOff + i) != b[bOff + i]) {
                return false;
            }
        }

        return true;
    }

    public static String toString(final CharSequence charSequence) {
        return new String(getAsCharArray(charSequence));
    }

    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    public static boolean statsWith(final CharSequence a, final CharSequence b) {
        final int len = b.length();
        if (len > a.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean statsWith(final CharSequence a, final int off, final CharSequence b) {
        if (off < 0) {
            return false;
        }

        final int length = b.length();
        if (length > (a.length() - off)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (a.charAt(off + i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean endsWith(final CharSequence a, final CharSequence b) {
        final int len = b.length();
        final int off = len - a.length();
        if (off < 0) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (a.charAt(off + i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static CharSequence trim(final CharSequence charSequence) {
        int start = 0;
        int end = charSequence.length();

        for (; (start < end) && (charSequence.charAt(start) <= ' '); start++) {
            ;
        }
        for (; (end > 0) && (charSequence.charAt(end - 1) <= ' '); end--) {
            ;
        }

        return charSequence.subSequence(start, end);
    }

    public static CharSequence trimLeft(final CharSequence charSequence) {
        int start = 0;
        final int end = charSequence.length();

        for (; (start < end) && (charSequence.charAt(start) <= ' '); start++) {
            ;
        }

        return charSequence.subSequence(start, end);
    }

    public static CharSequence trimRight(final CharSequence charSequence) {
        int length = charSequence.length();

        for (; (length > 0) && (charSequence.charAt(length - 1) <= ' '); length--) {
            ;
        }

        return charSequence.subSequence(0, length);
    }

    public static void copy(final CharSequence source, final char[] destiantion) {
        copy(source, destiantion, 0);
    }

    public static void copy(final CharSequence src, final char[] dst, final int off) {
        copy(src, dst, off, src.length());
    }

    public static void copy(final CharSequence src, final char[] dst, final int off, final int len) {
        for (int i = 0; i < len; i++) {
            dst[off + i] = src.charAt(i);
        }
    }

    public static char[] getAsCharArray(final CharSequence charSequence) {
        final int length = charSequence.length();
        final char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            result[i] = charSequence.charAt(i);
        }

        return result;
    }

    private CharSequenceUtil() {}

}