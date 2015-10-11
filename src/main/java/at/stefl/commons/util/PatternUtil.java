package at.stefl.commons.util;

import java.util.regex.Pattern;

public class PatternUtil {

    private static final String ANYTHING = ".*";
    private static final String END = "$";

    public static Pattern getEndsWithPattern(final String literal) {
        return getEndsWithPattern(Pattern.compile(literal, Pattern.LITERAL));
    }

    public static Pattern getEndsWithPattern(final Pattern pattern) {
        return Pattern.compile(ANYTHING + pattern.pattern() + END, Pattern.MULTILINE);
    }

    public static Pattern quote(final String literal) {
        return Pattern.compile(Pattern.quote(literal));
    }

    private PatternUtil() {}

}