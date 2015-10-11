package at.stefl.commons.codec;

// TODO: use bytes?
public class Base64 {

    public static void encode3Byte(final byte[] in, final char[] out, final Base64Settings settings) {
        encode3Byte(in, in.length, out, settings);
    }

    public static void encode3Byte(final byte[] in, final int len, final char[] out, final Base64Settings settings) {
        encode3Byte(in, 0, len, out, 0, settings);
    }

    public static void encode3Byte(final byte[] in, final int inOff, final int len, final char[] out, final int outOff, final Base64Settings settings) {
        int code = 0;

        switch (len) {
            case 3:
                code |= (in[inOff + 2] & 0xff);
            case 2:
                code |= (in[inOff + 1] & 0xff) << 8;
            case 1:
                code |= (in[inOff] & 0xff) << 16;
                break;
            default:
                throw new IllegalArgumentException("len = " + len);
        }

        encodeInt(code, len, out, outOff, settings);
    }

    public static void encodeInt(final int code, final int len, final char[] out, final int off, final Base64Settings settings) {
        switch (len) {
            case 3:
                out[off + 3] = settings.encodeTable[code & 0x3f];
            case 2:
                out[off + 2] = settings.encodeTable[(code >> 6) & 0x3f];
            case 1:
                out[off + 1] = settings.encodeTable[(code >> 12) & 0x3f];
                out[off] = settings.encodeTable[(code >> 18) & 0x3f];
                break;
            default:
                throw new IllegalArgumentException("len = " + len);
        }

        if (settings.padding) {
            switch (len) {
                case 1:
                    out[off + 2] = Base64Settings.PADDING_CHAR;
                case 2:
                    out[off + 3] = Base64Settings.PADDING_CHAR;
            }
        }
    }

    public static String encodeBytesAsString(final byte[] in, final Base64Settings settings) {
        return encodeBytesAsString(in, 0, in.length, settings);
    }

    public static String encodeBytesAsString(final byte[] in, final int off, final int len, final Base64Settings settings) {
        return new String(encodeBytes(in, off, len, settings));
    }

    public static char[] encodeBytes(final byte[] in, final Base64Settings settings) {
        return encodeBytes(in, 0, in.length, settings);
    }

    public static char[] encodeBytes(final byte[] in, final int off, final int len, final Base64Settings settings) {
        final int size = settings.encodedSize(len);
        final char[] result = new char[size];
        encodeBytes(in, off, len, result, 0, settings);
        return result;
    }

    public static void encodeBytes(final byte[] in, final int inOff, final int len, final char[] out, final int outOff, final Base64Settings settings) {
        final int end = inOff + len;

        for (int i = inOff, o = outOff; i < end; i += 3, o += 4) {
            encode3Byte(in, i, Math.min(3, end - i), out, o, settings);
        }
    }

    public static int decode3Byte(final char[] in, final byte[] out, final Base64Settings settings) {
        return decode3Byte(in, in.length, out, settings);
    }

    public static int decode3Byte(final char[] in, final int len, final byte[] out, final Base64Settings settings) {
        return decode3Byte(in, 0, len, out, 0, settings);
    }

    public static int decode3Byte(final char[] in, final int inOff, int len, final byte[] out, final int outOff, final Base64Settings settings) {
        int plain = 0;

        switch (len) {
            case 4:
                if (in[inOff + 3] == Base64Settings.PADDING_CHAR) {
                    len--;
                } else {
                    plain |= (settings.decode(in[inOff + 3]) & 0x3f);
                }
            case 3:
                if ((len == 3) & (in[inOff + 2] == Base64Settings.PADDING_CHAR)) {
                    len--;
                } else {
                    plain |= (settings.decode(in[inOff + 2]) & 0x3f) << 6;
                }
            case 2:
                plain |= (settings.decode(in[inOff + 1]) & 0x3f) << 12;
                plain |= (settings.decode(in[inOff]) & 0x3f) << 18;
                break;
            default:
                throw new IllegalArgumentException("len = " + len);
        }

        return decodeInt(plain, len, out, outOff, settings);
    }

    public static int decode3Byte(final CharSequence in, final byte[] out, final Base64Settings settings) {
        return decode3Byte(in, in.length(), out, settings);
    }

    public static int decode3Byte(final CharSequence in, final int end, final byte[] out, final Base64Settings settings) {
        return decode3Byte(in, 0, end, out, 0, settings);
    }

    public static int decode3Byte(final CharSequence in, final int start, final int end, final byte[] out, final int outOff, final Base64Settings settings) {
        int len = end - start;
        int plain = 0;

        switch (len) {
            case 4:
                if (settings.padding && (in.charAt(start + 3) == Base64Settings.PADDING_CHAR)) {
                    len--;
                } else {
                    plain |= (settings.decode(in.charAt(start + 3)) & 0x3f);
                }
            case 3:
                if (settings.padding && ((len == 3) && (in.charAt(start + 2) == Base64Settings.PADDING_CHAR))) {
                    len--;
                } else {
                    plain |= (settings.decode(in.charAt(start + 2)) & 0x3f) << 6;
                }
            case 2:
                plain |= (settings.decode(in.charAt(start + 1)) & 0x3f) << 12;
                plain |= (settings.decode(in.charAt(start)) & 0x3f) << 18;
                break;
            default:
                throw new IllegalArgumentException("len = " + len);
        }

        return decodeInt(plain, len, out, outOff, settings);
    }

    public static int decodeInt(final int plain, final int len, final byte[] out, final int off, final Base64Settings settings) {
        switch (len) {
            case 4:
                out[off + 2] = (byte) (plain & 0xff);
            case 3:
                out[off + 1] = (byte) ((plain >> 8) & 0xff);
            case 2:
                out[off] = (byte) ((plain >> 16) & 0xff);
                break;
            default:
                throw new IllegalArgumentException("len != 2,3,4");
        }

        return len - 1;
    }

    public static byte[] decodeChars(final char[] in, final Base64Settings settings) {
        return decodeChars(in, 0, in.length, settings);
    }

    public static byte[] decodeChars(final char[] in, final int off, final int len, final Base64Settings settings) {
        final int end = off + len;
        final int size = settings.decodedSize(len, in[end - 2], in[end - 1]);
        final byte[] result = new byte[size];
        decodeChars(in, off, len, result, 0, settings);
        return result;
    }

    public static void decodeChars(final char[] in, final int inOff, final int len, final byte[] out, final int outOff, final Base64Settings settings) {
        final int end = inOff + len;

        for (int i = inOff, o = outOff; i < end; i += 4, o += 3) {
            decode3Byte(in, i, Math.min(4, end - i), out, o, settings);
        }
    }

    public static byte[] decodeChars(final CharSequence in, final Base64Settings settings) {
        return decodeChars(in, 0, in.length(), settings);
    }

    public static byte[] decodeChars(final CharSequence in, final int start, final int end, final Base64Settings settings) {
        final int len = end - start;
        final int size = settings.decodedSize(len, in.charAt(end - 2), in.charAt(end - 1));
        final byte[] result = new byte[size];
        decodeChars(in, start, start + len, result, 0, settings);
        return result;
    }

    public static void decodeChars(final CharSequence in, final int start, final int end, final byte[] out, final int outOff, final Base64Settings settings) {
        for (int i = start, o = outOff; i < end; i += 4, o += 3) {
            decode3Byte(in, i, Math.min(i + 4, end), out, o, settings);
        }
    }

    private Base64() {}

}