package at.stefl.commons.lwxml.visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLReader;

public abstract class LWXMLHost {

    private final List<LWXMLVisitor> visitors = new ArrayList<LWXMLVisitor>();

    private boolean hosting;

    public boolean isHosting() {
        return this.hosting;
    }

    public synchronized boolean hasVisitors() {
        return !this.visitors.isEmpty();
    }

    public synchronized void addVisitor(final LWXMLVisitor visitor) {
        visitor.visitHost(this);
        this.visitors.add(visitor);
    }

    public synchronized void removeVisitor(final LWXMLVisitor visitor) {
        this.visitors.remove(visitor);
    }

    public synchronized void host(final LWXMLReader reader) throws IOException {
        this.hosting = true;

        try {
            while (this.hasVisitors()) {
                final LWXMLEvent event = reader.readEvent();

                if (event == null) {
                    this.fireVisitEnd();
                    return;
                }

                this.fireEvent(event, reader);
            }
        } finally {
            this.hosting = false;
        }
    }

    protected synchronized void fireEvent(final LWXMLEvent event, final LWXMLReader reader) throws IOException {
        String value1 = null;
        String value2 = null;

        if (event.hasValue()) {
            value1 = reader.readValue();
        }
        if (event.hasFollowingValue()) {
            value2 = reader.readFollowingValue();
        }

        switch (event) {
            case PROCESSING_INSTRUCTION_TARGET:
                this.fireVisitProcessingInstruction(value1, value2);
                break;
            case COMMENT:
                this.fireVisitComment(value1);
                break;
            case START_ELEMENT:
                this.fireVisitStartElement(value1);
                break;
            case END_ATTRIBUTE_LIST:
                this.fireVisitEndAttributeList();
                break;
            case END_ELEMENT:
                this.fireVisitEndElement(value1);
                break;
            case ATTRIBUTE_NAME:
                this.fireVisitAttribute(value1, value2);
                break;
            case CHARACTERS:
                this.fireVisitCharacters(value1);
                break;
            case CDATA:
                this.fireVisitCDATA(value1);
                break;
            default:
                throw new IllegalStateException("unsupported event");
        }
    }

    protected synchronized void fireVisitEnd() {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitEnd();
        }
    }

    protected synchronized void fireVisitProcessingInstruction(final String target, final String data) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitProcessingInstruction(target, data);
        }
    }

    protected synchronized void fireVisitComment(final String text) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitComment(text);
        }
    }

    protected synchronized void fireVisitStartElement(final String name) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitStartElement(name);
        }
    }

    protected synchronized void fireVisitEndAttributeList() {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitEndAttributeList();
        }
    }

    protected synchronized void fireVisitEndElement(final String name) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitEndElement(name);
        }
    }

    protected synchronized void fireVisitAttribute(final String name, final String value) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitAttribute(name, value);
        }
    }

    protected synchronized void fireVisitCharacters(final String text) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitCharacters(text);
        }
    }

    protected synchronized void fireVisitCDATA(final String text) {
        for (final LWXMLVisitor visitor : this.visitors) {
            visitor.visitCDATA(text);
        }
    }

}