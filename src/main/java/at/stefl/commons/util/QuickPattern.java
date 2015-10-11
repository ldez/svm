package at.stefl.commons.util;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: improve
public class QuickPattern {

    private final Pattern pattern;
    private final int group;

    private Matcher lastMatcher;

    public QuickPattern(final Pattern pattern) {
        this(pattern, -1);
    }

    public QuickPattern(final String regex) {
        this(regex, -1);
    }

    public QuickPattern(final Pattern pattern, final int group) {
        this.pattern = pattern;
        this.group = group;
    }

    public QuickPattern(final String regex, final int group) {
        this(Pattern.compile(regex), group);
    }

    public QuickPattern(final String regex, final int flags, final int group) {
        this(Pattern.compile(regex, flags), group);
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public int getGroup() {
        return this.group;
    }

    public Matcher getLastMatcher() {
        return this.lastMatcher;
    }

    public String matchGroup(final String string) {
        this.lastMatcher = this.pattern.matcher(string);
        if (!this.lastMatcher.matches()) {
            return null;
        }
        return this.lastMatcher.group(this.group);
    }

    public String[] matchGroups(final String string) {
        this.lastMatcher = this.pattern.matcher(string);
        if (!this.lastMatcher.matches()) {
            return null;
        }

        final String[] result = new String[this.lastMatcher.groupCount()];
        for (int i = 1; i <= this.lastMatcher.groupCount(); i++) {
            result[i] = this.lastMatcher.group(i);
        }
        return result;
    }

    public String findGroup(final String string) {
        return this.findGroup(string, 0);
    }

    public String findGroup(final String string, final int offset) {
        this.lastMatcher = this.pattern.matcher(string);
        if (!this.lastMatcher.find(offset)) {
            return null;
        }
        return this.lastMatcher.group(this.group);
    }

    public String[] findGroups(final String string) {
        this.lastMatcher = this.pattern.matcher(string);
        if (!this.lastMatcher.find()) {
            return null;
        }

        final String[] result = new String[this.lastMatcher.groupCount()];
        for (int i = 1; i <= this.lastMatcher.groupCount(); i++) {
            result[i - 1] = this.lastMatcher.group(i);
        }
        return result;
    }

    public List<String> findGroupAll(final String string) {
        final List<String> result = new LinkedList<String>();

        int offset = 0;
        while (true) {
            final String find = this.findGroup(string, offset);
            if (find == null) {
                break;
            }
            result.add(find);
            offset = this.lastMatcher.end();
        }

        return result;
    }
}