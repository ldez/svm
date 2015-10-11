package at.stefl.commons.util.string;

import java.util.ArrayList;
import java.util.List;

// TODO: clean up
public final class StringUtil {

    public static final String NULL = "null";

    public static final String NEW_LINE = System.getProperty("line.separator");

    public static String trimLeft(final String s) {
        int start = 0;
        for (; (start < s.length()) && (s.charAt(start) <= ' '); start++) {
            ;
        }
        return s.substring(start);
    }

    public static String trimRight(final String s) {
        int end = s.length();
        for (; (end > 0) && (s.charAt(end - 1) <= ' '); end--) {
            ;
        }
        return s.substring(0, end);
    }

    public static String fillFront(final String string, final char c, final int length) {
        if (string.length() >= length) {
            return string;
        }
        return multiply(c, length - string.length()) + string;
    }

    public static String multiply(final char c, final int times) {
        if (times < 0) {
            throw new IllegalArgumentException();
        }
        if (times == 0) {
            return "";
        }

        final StringBuilder builder = new StringBuilder(times);

        for (int i = 0; i < times; i++) {
            builder.append(c);
        }

        return builder.toString();
    }

    public static String multiply(final String string, final int times) {
        if (times < 0) {
            throw new IllegalArgumentException();
        }
        if (times == 0) {
            return "";
        }

        final StringBuilder builder = new StringBuilder(string.length() * times);

        for (int i = 0; i < times; i++) {
            builder.append(string);
        }

        return builder.toString();
    }

    public static int width(final String string) {
        int result = 0;

        final String[] lines = string.split(NEW_LINE);

        for (final String line : lines) {
            final int length = line.length();
            if (result < length) {
                result = length;
            }
        }

        return result;
    }

    public static int height(final String string) {
        int result = 1;

        for (int i = string.indexOf(NEW_LINE); i != -1; i = string.indexOf(NEW_LINE, i + 1)) {
            result++;
        }

        return result;
    }

    public static String[] lines(final String string) {
        int index = string.indexOf(NEW_LINE);

        if (index == -1) {
            return new String[] { string };
        }

        final List<String> lines = new ArrayList<String>();
        lines.add(string.substring(0, index));

        int offset = index + NEW_LINE.length();

        while (true) {
            index = string.indexOf(NEW_LINE, offset);

            if (index == -1) {
                lines.add(string.substring(offset));
                break;
            }

            lines.add(string.substring(offset, index));

            offset = index + NEW_LINE.length();
        }

        return lines.toArray(new String[lines.size()]);
    }

    public static String widthCenter(final String string, final int width) {
        final String[] lines = lines(string);
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            final int spaceCount = width - lines[i].length();
            final int spaceLeft = spaceCount / 2;
            final int spaceRight = spaceCount - spaceLeft;

            lines[i] = lines[i] + multiply(" ", spaceRight);
            lines[i] = multiply(" ", spaceLeft) + lines[i];

            builder.append(lines[i]);
            if (i < (lines.length - 1)) {
                builder.append(NEW_LINE);
            }
        }

        return builder.toString();
    }

    public static String heightCenter(final String string, final int height) {
        final int stringHeight = height(string);

        if (stringHeight >= height) {
            return string;
        }

        final int spaceCount = height - stringHeight;
        final int spaceTop = spaceCount / 2;
        final int spaceBottom = spaceCount - spaceTop;

        final StringBuilder builder = new StringBuilder();

        builder.append(multiply(NEW_LINE, spaceTop));
        builder.append(string);
        builder.append(multiply(NEW_LINE, spaceBottom));

        return builder.toString();
    }

    public static String center(String string, final int width, final int height) {
        string = heightCenter(string, height);
        string = widthCenter(string, width);

        return string;
    }

    public static String concate(final String separator, final String... strings) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                builder.append(separator);
            }
            builder.append(strings[i]);
        }

        return builder.toString();
    }

    public static String concateNotNull(final String separator, final String... strings) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0, j = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                continue;
            }
            if (j++ > 0) {
                builder.append(separator);
            }
            builder.append(strings[i]);
        }

        return builder.toString();
    }

    private StringUtil() {}

}