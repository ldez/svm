package at.stefl.commons.lwxml.reader;

import at.stefl.commons.lwxml.LWXMLException;

public class LWXMLReaderException extends LWXMLException {

    private static final long serialVersionUID = -5114969261011687333L;

    public LWXMLReaderException() {}

    public LWXMLReaderException(final String message) {
        super(message);
    }

    public LWXMLReaderException(final Throwable cause) {
        super(cause);
    }

    public LWXMLReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }

}