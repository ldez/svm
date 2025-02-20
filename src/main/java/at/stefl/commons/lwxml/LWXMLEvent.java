package at.stefl.commons.lwxml;

import java.util.Set;

import at.stefl.commons.util.array.ArrayUtil;

// TODO: remove START_DOCUMENT, END_DOCUMENT
public enum LWXMLEvent {

    START_DOCUMENT(false), END_DOCUMENT(false),

    PROCESSING_INSTRUCTION_TARGET(true), PROCESSING_INSTRUCTION_DATA(true),

    COMMENT(true), CDATA(true),

    CHARACTERS(true),

    START_ELEMENT(true), END_EMPTY_ELEMENT(false), END_ELEMENT(true),

    ATTRIBUTE_NAME(true), ATTRIBUTE_VALUE(true), END_ATTRIBUTE_LIST(false);

    static {
        final Set<LWXMLEvent> defaultFollowers = ArrayUtil.toHashSet(PROCESSING_INSTRUCTION_TARGET, COMMENT, START_ELEMENT, END_ELEMENT, CHARACTERS, CDATA, END_DOCUMENT);

        START_DOCUMENT.setFollowingEvents(PROCESSING_INSTRUCTION_TARGET, COMMENT, START_ELEMENT, CHARACTERS, CDATA, END_DOCUMENT);
        END_DOCUMENT.setFollowingEvents();

        PROCESSING_INSTRUCTION_TARGET.setFollowingEvents(PROCESSING_INSTRUCTION_DATA);
        PROCESSING_INSTRUCTION_DATA.setFollowingEvents(defaultFollowers);

        CHARACTERS.setFollowingEvents(defaultFollowers);

        COMMENT.setFollowingEvents(defaultFollowers);
        CDATA.setFollowingEvents(defaultFollowers);

        START_ELEMENT.setFollowingEvents(ATTRIBUTE_NAME, END_ATTRIBUTE_LIST);
        END_EMPTY_ELEMENT.setFollowingEvents(defaultFollowers);
        END_ELEMENT.setFollowingEvents(defaultFollowers);

        ATTRIBUTE_NAME.setFollowingEvents(ATTRIBUTE_VALUE);
        ATTRIBUTE_VALUE.setFollowingEvents(ATTRIBUTE_NAME, END_ATTRIBUTE_LIST);
        END_ATTRIBUTE_LIST.setFollowingEvents(PROCESSING_INSTRUCTION_TARGET, COMMENT, START_ELEMENT, END_EMPTY_ELEMENT, END_ELEMENT, CHARACTERS, CDATA, END_DOCUMENT);

        for (final LWXMLEvent event : values()) {
            if (event.followingEvents.size() != 1) {
                continue;
            }

            final LWXMLEvent followingEvent = event.followingEvents.iterator().next();
            event.hasFollowingValue = followingEvent.hasValue;
        }
    }

    public static boolean isStarting(final LWXMLEvent event) {
        return isFollowingEvent(START_DOCUMENT, event);
    }

    public static boolean isEnding(final LWXMLEvent event) {
        return isFollowingEvent(event, END_DOCUMENT);
    }

    public static boolean isFollowingEvent(final LWXMLEvent event, final LWXMLEvent followingEvent) {
        if (event == null) {
            return false;
        }
        return event.followingEvents.contains(followingEvent);
    }

    public static boolean hasValue(final LWXMLEvent event) {
        if (event == null) {
            return false;
        }
        return event.hasValue;
    }

    public static boolean hasFollowingValue(final LWXMLEvent event) {
        if (event == null) {
            return false;
        }
        return event.hasFollowingValue;
    }

    private final boolean hasValue;

    private Set<LWXMLEvent> followingEvents;
    private boolean hasFollowingValue;

    private LWXMLEvent(final boolean hasValue) {
        this.hasValue = hasValue;
    }

    private void setFollowingEvents(final Set<LWXMLEvent> followingEvents) {
        this.followingEvents = followingEvents;
    }

    private void setFollowingEvents(final LWXMLEvent... followingEvents) {
        this.setFollowingEvents(ArrayUtil.toHashSet(followingEvents));
    }

    public boolean isEndElement() {
        return (this == END_EMPTY_ELEMENT) || (this == END_ELEMENT);
    }

    public boolean isStartingEvent() {
        return isStarting(this);
    }

    public boolean isEndingEvent() {
        return isEnding(this);
    }

    public boolean isFollowingEvent(final LWXMLEvent followingEvent) {
        return isFollowingEvent(this, followingEvent);
    }

    public boolean hasValue() {
        return this.hasValue;
    }

    public boolean hasFollowingValue() {
        return this.hasFollowingValue;
    }

}