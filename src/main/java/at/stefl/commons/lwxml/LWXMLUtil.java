package at.stefl.commons.lwxml;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.StreamableStringMap;
import at.stefl.commons.io.StreamableStringSet;
import at.stefl.commons.lwxml.path.LWXMLNodeIdentifier;
import at.stefl.commons.lwxml.path.LWXMLPath;
import at.stefl.commons.lwxml.reader.LWXMLBranchReader;
import at.stefl.commons.lwxml.reader.LWXMLElementReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;
import at.stefl.commons.util.collection.OrderedPair;

// TODO: make use of EOFException
public class LWXMLUtil {

    public static void flush(final LWXMLReader in) throws IOException {
        while (in.readEvent() != LWXMLEvent.END_DOCUMENT) {
            ;
        }
    }

    public static void flushBranch(final LWXMLReader in) throws IOException {
        flush(new LWXMLBranchReader(in));
    }

    public static void flushElement(final LWXMLReader in) throws IOException {
        flush(new LWXMLElementReader(in));
    }

    public static void flushUntilPath(final InputStream in, final LWXMLPath path) throws IOException {
        flushUntilPath(new LWXMLStreamReader(in), path);
    }

    public static void flushUntilPath(final Reader in, final LWXMLPath path) throws IOException {
        flushUntilPath(new LWXMLStreamReader(in), path);
    }

    public static void flushUntilPath(final LWXMLReader in, final LWXMLPath path) throws IOException {
        int depth = 0;
        int matchingIndex = 0;
        int nodeIndex = 0;

        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                throw new EOFException();
            }

            switch (event) {
                case START_ELEMENT:
                    depth++;
                    if (depth > (matchingIndex + 1)) {
                        break;
                    }

                    final LWXMLNodeIdentifier nodeIdentifier = path.getNodeIdentifier(matchingIndex);
                    if (!in.readValue().equals(nodeIdentifier.getElementName())) {
                        break;
                    }

                    if (nodeIndex < nodeIdentifier.getIndex()) {
                        nodeIndex++;
                    } else {
                        matchingIndex++;
                        nodeIndex = 0;

                        if (matchingIndex >= path.getDepth()) {
                            return;
                        }
                    }

                    break;
                case END_EMPTY_ELEMENT:
                case END_ELEMENT:
                    depth--;
                    if (matchingIndex > depth) {
                        throw new EOFException();
                    }

                    break;
                default:
                    break;
            }
        }
    }

    public static void flushUntilEventNumber(final LWXMLReader in, final long eventNumber) throws IOException {
        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                throw new EOFException();
            }

            if (in.getCurrentEventNumber() >= eventNumber) {
                return;
            }
        }
    }

    public static void flushEmptyElement(final LWXMLReader in) throws IOException {
        LWXMLEvent event = in.readEvent();
        if (event == LWXMLEvent.START_ELEMENT) {
            event = in.readEvent();
        }

        while (true) {
            switch (event) {
                case ATTRIBUTE_NAME:
                case ATTRIBUTE_VALUE:
                case END_ATTRIBUTE_LIST:
                    break;
                case END_EMPTY_ELEMENT:
                case END_ELEMENT:
                    return;
                default:
                    throw new LWXMLIllegalEventException(event);
            }

            event = in.readEvent();
        }
    }

    public static void flushStartElement(final LWXMLReader in) throws IOException {
        LWXMLEvent event = in.readEvent();
        if (event == LWXMLEvent.START_ELEMENT) {
            event = in.readEvent();
        }

        while (true) {
            switch (event) {
                case ATTRIBUTE_NAME:
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    return;
                default:
                    throw new LWXMLIllegalEventException(event);
            }

            event = in.readEvent();
        }
    }

    public static void flushUntilEvent(final LWXMLReader in, final LWXMLEvent event) throws IOException {
        if (!event.hasValue()) {
            throw new LWXMLIllegalEventException(event);
        }

        while (true) {
            final LWXMLEvent currentEvent = in.readEvent();
            if (currentEvent == LWXMLEvent.END_DOCUMENT) {
                throw new EOFException();
            }

            if (currentEvent == event) {
                return;
            }
        }
    }

    public static void flushUntilEventValue(final LWXMLReader in, final LWXMLEvent event, final String value) throws IOException {
        if (!event.hasValue()) {
            throw new LWXMLIllegalEventException(event);
        }

        while (true) {
            final LWXMLEvent currentEvent = in.readEvent();
            if (currentEvent == LWXMLEvent.END_DOCUMENT) {
                throw new EOFException();
            }

            if ((currentEvent == event) && in.readValue().equals(value)) {
                return;
            }
        }
    }

    public static void flushUntilStartElement(final LWXMLReader in, final String startElement) throws IOException {
        flushUntilEventValue(in, LWXMLEvent.START_ELEMENT, startElement);
    }

    public static void flushUntilEndElement(final LWXMLReader in, final String endElement) throws IOException {
        flushUntilEventValue(in, LWXMLEvent.END_ELEMENT, endElement);
    }

    public static LWXMLBranchReader getBranchReader(final InputStream in, final LWXMLPath path) throws IOException {
        return getBranchReader(new LWXMLStreamReader(in), path);
    }

    public static LWXMLBranchReader getBranchReader(final Reader in, final LWXMLPath path) throws IOException {
        return getBranchReader(new LWXMLStreamReader(in), path);
    }

    public static LWXMLBranchReader getBranchReader(final LWXMLReader in, final LWXMLPath path) throws IOException {
        flushUntilPath(in, path);
        return new LWXMLBranchReader(in);
    }

    public static String parseAttributeValue(final InputStream in, final LWXMLPath path, final String attributeName) throws IOException {
        return parseAttributeValue(new LWXMLStreamReader(in), path, attributeName);
    }

    public static String parseAttributeValue(final Reader in, final LWXMLPath path, final String attributeName) throws IOException {
        return parseAttributeValue(new LWXMLStreamReader(in), path, attributeName);
    }

    public static String parseAttributeValue(final LWXMLReader in, final LWXMLPath path, final String attributeName) throws IOException {
        flushUntilPath(in, path);

        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    if (!in.readValue().equals(attributeName)) {
                        continue;
                    }
                    return in.readFollowingValue();
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    return null;
                default:
                    break;
            }
        }
    }

    public static String parseFirstAttributeValue(final InputStream in, final String attributeName) throws IOException {
        return parseFirstAttributeValue(new LWXMLStreamReader(in), attributeName);
    }

    public static String parseFirstAttributeValue(final Reader in, final String attributeName) throws IOException {
        return parseFirstAttributeValue(new LWXMLStreamReader(in), attributeName);
    }

    public static String parseFirstAttributeValue(final LWXMLReader in, final String attributeName) throws IOException {
        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    if (CharStreamUtil.matchChars(in, attributeName)) {
                        return in.readFollowingValue();
                    }
                default:
                    break;
                case END_DOCUMENT:
                    return null;
            }
        }
    }

    public static List<String> parseAllAttributeValues(final InputStream in, final String attributeName) throws IOException {
        return parseAllAttributeValues(new LWXMLStreamReader(in), attributeName);
    }

    public static List<String> parseAllAttributeValues(final Reader in, final String attributeName) throws IOException {
        return parseAllAttributeValues(new LWXMLStreamReader(in), attributeName);
    }

    public static List<String> parseAllAttributeValues(final LWXMLReader in, final String attributeName) throws IOException {
        final List<String> result = new LinkedList<String>();

        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    if (in.readValue().equals(attributeName)) {
                        result.add(in.readFollowingValue());
                    }
                    break;
                case END_DOCUMENT:
                    return result;
                default:
                    break;
            }
        }
    }

    public static Map<String, String> parseAllAttributes(final LWXMLReader in) throws IOException {
        final Map<String, String> result = new HashMap<String, String>();

        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    result.put(in.readValue(), in.readFollowingValue());
                    break;
                case END_ATTRIBUTE_LIST:
                    return result;
                default:
                    throw new LWXMLIllegalEventException(event);
            }
        }
    }

    public static void parseAttributes(final LWXMLReader in, final StreamableStringSet attributes, final Map<String, String> result) throws IOException {
        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    final String attribute = attributes.match(in);
                    if (attribute == null) {
                        break;
                    }

                    result.put(attribute, in.readFollowingValue());
                    if (result.size() >= attributes.size()) {
                        return;
                    }
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    return;
                default:
                    throw new LWXMLIllegalEventException(event);
            }
        }
    }

    public static void parseAttributes(final LWXMLReader in, final StreamableStringMap<String> map) throws IOException {
        int parsed = 0;

        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    final OrderedPair<String, String> match = map.match(in);
                    if (match == null) {
                        break;
                    }

                    map.put(match.getElement1(), in.readFollowingValue());
                    if (++parsed >= map.size()) {
                        return;
                    }
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    return;
                default:
                    throw new LWXMLIllegalEventException(event);
            }
        }
    }

    public static HashMap<String, String> parseAttributes(final LWXMLReader in, final StreamableStringSet attributes) throws IOException {
        final HashMap<String, String> result = new HashMap<String, String>(attributes.size());
        parseAttributes(in, attributes, result);
        return result;
    }

    public static String parseSingleAttribute(final LWXMLReader in, final String attributeName) throws IOException {
        while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    if (CharStreamUtil.matchChars(in, attributeName)) {
                        return in.readFollowingValue();
                    }
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    return null;
                default:
                    throw new LWXMLIllegalEventException(event);
            }
        }
    }

    public static boolean isEmptyElement(final LWXMLReader in) throws IOException {
        if (in.getCurrentEvent() != LWXMLEvent.END_ATTRIBUTE_LIST) {
            flushStartElement(in);
        }

        switch (in.readEvent()) {
            case END_EMPTY_ELEMENT:
            case END_ELEMENT:
                return true;
            default:
                break;
        }

        return false;
    }

    private LWXMLUtil() {}

}