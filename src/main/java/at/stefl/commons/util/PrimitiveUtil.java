package at.stefl.commons.util;

public abstract class PrimitiveUtil {

    public static boolean isValidUnsignedByte(final short v) {
        return (v & 0xff00) == 0;
    }

    public static boolean isValidUnsignedShort(final int v) {
        return (v & 0xffff0000) == 0;
    }

    public static boolean isValidUnsignedInt(final long v) {
        return (v & 0xffffffff00000000l) == 0;
    }

    public static void checkUnsignedByte(final short v) {
        if (!isValidUnsignedByte(v)) {
            throw new IllegalArgumentException("unsigned byte out of range");
        }
    }

    public static void checkUnsignedShort(final int v) {
        if (!isValidUnsignedShort(v)) {
            throw new IllegalArgumentException("unsigned short out of range");
        }
    }

    public static void checkUnsignedInt(final long v) {
        if (!isValidUnsignedInt(v)) {
            throw new IllegalArgumentException("unsigned int out of range");
        }
    }

    private PrimitiveUtil() {}

}