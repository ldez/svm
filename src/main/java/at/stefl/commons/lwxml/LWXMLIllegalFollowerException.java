package at.stefl.commons.lwxml;

public class LWXMLIllegalFollowerException extends LWXMLException {

    private static final long serialVersionUID = -911523786526389710L;

    public LWXMLIllegalFollowerException(final LWXMLEvent event, final LWXMLEvent followingEvent) {
        super(followingEvent.toString() + " cannot follow " + event);
    }

}