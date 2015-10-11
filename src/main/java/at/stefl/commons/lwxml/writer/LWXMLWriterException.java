package at.stefl.commons.lwxml.writer;

import at.stefl.commons.lwxml.LWXMLException;

public class LWXMLWriterException extends LWXMLException {

    private static final long serialVersionUID = -5114969261011687333L;

    public LWXMLWriterException() {
        super();
    }

    public LWXMLWriterException(final String message) {
        super(message);
    }

    public LWXMLWriterException(final Throwable cause) {
        super(cause);
    }

    public LWXMLWriterException(final String message, final Throwable cause) {
        super(message, cause);
    }

}