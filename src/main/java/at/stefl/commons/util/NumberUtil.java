package at.stefl.commons.util;

public class NumberUtil {

    public static byte parseByte(final String value, final byte nullValue) {
        return (value == null) ? nullValue : Byte.parseByte(value);
    }

    public static short parseShort(final String value, final short nullValue) {
        return (value == null) ? nullValue : Short.parseShort(value);
    }

    public static int parseInt(final String value, final int nullValue) {
        return (value == null) ? nullValue : Integer.parseInt(value);
    }

    public static long parseLong(final String value, final long nullValue) {
        return (value == null) ? nullValue : Long.parseLong(value);
    }

    public static float parseFloat(final String value, final float nullValue) {
        return (value == null) ? nullValue : Float.parseFloat(value);
    }

    public static double parseDouble(final String value, final double nullValue) {
        return (value == null) ? nullValue : Double.parseDouble(value);
    }

    public static boolean isValidUnsignedByte(final int value) {
        return (value & 0xff00) == 0;
    }

    public static boolean isValidUnsignedShort(final int value) {
        return (value & 0xffff0000) == 0;
    }

    public static boolean isValidUnsignedInt(final long value) {
        return (value & 0xffffffff00000000l) == 0;
    }

    private NumberUtil() {}

}