package at.stefl.commons.lwxml;

public class LWXMLException extends RuntimeException {

    private static final long serialVersionUID = -3390546202583727574L;

    public LWXMLException() {}

    public LWXMLException(final String message) {
        super(message);
    }

    public LWXMLException(final Throwable cause) {
        super(cause);
    }

    public LWXMLException(final String message, final Throwable cause) {
        super(message, cause);
    }

}