package at.stefl.commons.lwxml;

import at.stefl.commons.lwxml.reader.LWXMLReader;

public class LWXMLIllegalEventException extends LWXMLException {

    private static final long serialVersionUID = -3888861098441580428L;

    public LWXMLIllegalEventException(final LWXMLEvent event) {
        super(event.toString());
    }

    public LWXMLIllegalEventException(final LWXMLReader in) {
        this(in.getCurrentEvent());
    }

}