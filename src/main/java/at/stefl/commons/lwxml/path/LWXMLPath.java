package at.stefl.commons.lwxml.path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LWXMLPath {

    private static final String NODE_SEPARATOR = "/";
    private static final Pattern PATTERN = Pattern.compile("(.+?)(\\[(.+)\\])?$");

    private final List<LWXMLNodeIdentifier> nodeIdentifierList;

    private LWXMLPath(final List<LWXMLNodeIdentifier> nodeIdentifierList, final boolean dummy) {
        this.nodeIdentifierList = nodeIdentifierList;
    }

    public LWXMLPath(final List<LWXMLNodeIdentifier> nodeIdentifierList) {
        this.nodeIdentifierList = new ArrayList<LWXMLNodeIdentifier>(nodeIdentifierList);
    }

    public LWXMLPath(final String path) {
        this.nodeIdentifierList = new LinkedList<LWXMLNodeIdentifier>();

        for (final String segment : path.split(NODE_SEPARATOR)) {
            final Matcher matcher = PATTERN.matcher(segment);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("invalid syntax");
            }

            final String node = matcher.group(1);
            final int index = (matcher.group(3) == null) ? 0 : Integer.parseInt(matcher.group(3));
            final LWXMLNodeIdentifier nodeIdentifier = new LWXMLNodeIdentifier(node, index);
            this.nodeIdentifierList.add(nodeIdentifier);
        }
    }

    public LWXMLPath(final LWXMLPath path, final LWXMLPath child) {
        this.nodeIdentifierList = new ArrayList<LWXMLNodeIdentifier>(path.getDepth() + child.getDepth());

        this.nodeIdentifierList.addAll(path.nodeIdentifierList);
        this.nodeIdentifierList.addAll(child.nodeIdentifierList);
    }

    public LWXMLPath(final LWXMLPath path, final String child) {
        this(path, new LWXMLPath(child));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        for (final LWXMLNodeIdentifier nodeIdentifier : this.nodeIdentifierList) {
            builder.append(NODE_SEPARATOR);
            builder.append(nodeIdentifier);
        }

        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LWXMLPath)) {
            return false;
        }
        final LWXMLPath path = (LWXMLPath) obj;

        return this.nodeIdentifierList.equals(path.nodeIdentifierList);
    }

    @Override
    public int hashCode() {
        return this.nodeIdentifierList.hashCode();
    }

    public int getDepth() {
        return this.nodeIdentifierList.size();
    }

    public LWXMLNodeIdentifier getNodeIdentifier(final int index) {
        return this.nodeIdentifierList.get(index);
    }

    public List<LWXMLNodeIdentifier> getNodeIdentifierList() {
        return new ArrayList<LWXMLNodeIdentifier>(this.nodeIdentifierList);
    }

    public LWXMLPath append(final LWXMLPath child) {
        return new LWXMLPath(this, child);
    }

    public LWXMLPath subPath(final int start) {
        return this.subPath(start, this.getDepth());
    }

    public LWXMLPath subPath(final int start, final int end) {
        if (start < 0) {
            throw new IndexOutOfBoundsException("start cannot be less than 0");
        }
        if (end < 0) {
            throw new IndexOutOfBoundsException("start cannot be less than 0");
        }
        if (start > end) {
            throw new IllegalArgumentException("end must be greater than start");
        }

        return new LWXMLPath(this.nodeIdentifierList.subList(start, end), true);
    }

    public boolean startsWith(final LWXMLPath path) {
        if (path.getDepth() > this.getDepth()) {
            throw new IllegalArgumentException("the argument is too big");
        }

        for (int i = 0; i < path.getDepth(); i++) {
            if (!this.getNodeIdentifier(i).equals(path.getNodeIdentifier(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean endsWith(final LWXMLPath path) {
        if (path.getDepth() > this.getDepth()) {
            throw new IllegalArgumentException("the argument is too big");
        }

        for (int i = 1; i <= path.getDepth(); i++) {
            if (!this.getNodeIdentifier(this.getDepth() - i).equals(path.getNodeIdentifier(path.getDepth() - i))) {
                return false;
            }
        }

        return true;
    }

}