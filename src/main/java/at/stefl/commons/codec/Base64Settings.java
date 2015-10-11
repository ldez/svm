package at.stefl.commons.codec;

// TODO: use bytes?
public class Base64Settings {

    private static final char[] ENCODE_TABLE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 0, 0 };

    private static final byte[] DECODE_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, 53,
            54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };

    public static final char PADDING_CHAR = '=';

    public static final Base64Settings ORIGINAL = new Base64Settings('+', '/', true);
    public static final Base64Settings FILENAME = new Base64Settings('+', '-', false);
    public static final Base64Settings URL = new Base64Settings('-', '_', false);

    final char[] encodeTable;
    final byte[] decodeTable;
    final boolean padding;

    public Base64Settings(final char index62, final char index63, final boolean padding) {
        this.encodeTable = ENCODE_TABLE.clone();
        this.decodeTable = DECODE_TABLE.clone();

        this.encodeTable[62] = index62;
        this.encodeTable[63] = index63;
        this.decodeTable[index62] = 62;
        this.decodeTable[index63] = 63;

        this.padding = padding;
    }

    public char[] getEncodeTable() {
        return this.encodeTable.clone();
    }

    public byte[] getDecodeTable() {
        return this.decodeTable.clone();
    }

    public boolean isPadding() {
        return this.padding;
    }

    public boolean canEncode(final byte b) {
        return (b >= 0) & (b < 64);
    }

    public boolean canDecode(final char c) {
        return this.decodeTable[c] != -1;
    }

    public char encode(final byte b) {
        if (!this.canEncode(b)) {
            throw new IllegalArgumentException("unmapped byte: " + b);
        }
        return this.encodeTable[b];
    }

    public byte decode(final char c) {
        if (!this.canDecode(c)) {
            throw new IllegalArgumentException("unmapped char: " + c);
        }
        return this.decodeTable[c];
    }

    public int encodedSize(final int size) {
        int result = (size / 3) * 4;
        if ((size % 3) > 0) {
            result += this.padding ? 4 : ((size % 3) + 1);
        }
        return result;
    }

    public int decodedSize(final char[] in) {
        return this.decodedSize(in, 0, in.length);
    }

    public int decodedSize(final char[] in, final int off, final int len) {
        final int end = off + len;
        return this.decodedSize(len, in[end - 2], in[end - 1]);
    }

    public int decodedSize(final CharSequence in) {
        return this.decodedSize(in, 0, in.length());
    }

    public int decodedSize(final CharSequence in, final int start, final int end) {
        final int len = end - start;
        return this.decodedSize(len, in.charAt(end - 2), in.charAt(end - 1));
    }

    public int decodedSize(final int size, final char lastButOne, final char last) {
        int result = (size / 4) * 3;
        final int lenMod4 = size % 4;
        if (lenMod4 > 0) {
            if (this.padding || (lenMod4 == 1)) {
                throw new IllegalArgumentException("illegal length");
            }
            result += lenMod4 - 1;
        }
        if (this.padding) {
            if (last == PADDING_CHAR) {
                result--;
                if (lastButOne == PADDING_CHAR) {
                    result--;
                }
            }
        }
        return result;
    }

}