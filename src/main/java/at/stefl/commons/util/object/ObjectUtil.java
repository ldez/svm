package at.stefl.commons.util.object;

public class ObjectUtil {

    public static int hashCode(final Object o) {
        return (o == null) ? 0 : o.hashCode();
    }

    public static boolean equals(final Object a, final Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static boolean fastEquals(final Object a, final Object b) {
        return (a == null) ? (b == null) : ((b != null) && (a.hashCode() == b.hashCode()) && a.equals(b));
    }

    private ObjectUtil() {}

}