package at.stefl.commons.util;

public class GenericsUtil {

    @SuppressWarnings("unchecked")
    public static <T> T castObject(final Object object) {
        return (T) object;
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(final Class<?> clazz) {
        return (Class<T>) clazz;
    }

    private GenericsUtil() {}

}