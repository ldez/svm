package at.stefl.commons.lwxml.writer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.lwxml.LWXMLAttribute;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalEventException;

public abstract class LWXMLWriter extends Writer {

    protected final CharStreamUtil streamUtil = new CharStreamUtil();

    public abstract LWXMLEvent getCurrentEvent();

    public abstract long getCurrentEventNumber();

    public abstract boolean isCurrentEventWritten();

    public abstract void writeEvent(LWXMLEvent event) throws IOException;

    private void checkEndAttributeList() throws IOException {
        if (this.getCurrentEvent() == null) {
            return;
        }

        switch (this.getCurrentEvent()) {
            case START_ELEMENT:
            case ATTRIBUTE_VALUE:
                this.writeEvent(LWXMLEvent.END_ATTRIBUTE_LIST);
            default:
                break;
        }
    }

    public void writeProcessingInstruction(final String target, final String data) throws IOException {
        if (target == null) {
            throw new NullPointerException();
        }
        if (data == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        this.writeEvent(LWXMLEvent.PROCESSING_INSTRUCTION_TARGET);
        this.write(target);
        this.writeEvent(LWXMLEvent.PROCESSING_INSTRUCTION_DATA);
        this.write(data);
    }

    public void writeStartElement(final String name) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        this.writeEvent(LWXMLEvent.START_ELEMENT);
        this.write(name);
    }

    public void writeEmptyStartElement(final String name) throws IOException {
        this.writeStartElement(name);
        this.writeEvent(LWXMLEvent.END_ATTRIBUTE_LIST);
    }

    public void writeEmptyElement(final String name) throws IOException {
        this.writeEmptyStartElement(name);
        this.writeEndEmptyElement();
    }

    public void writeAttribute(final LWXMLAttribute attribute) throws IOException {
        if (attribute == null) {
            throw new NullPointerException();
        }

        this.writeAttribute(attribute.getName(), attribute.getValue());
    }

    public void writeAttribute(final String name, final String value) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            throw new NullPointerException();
        }

        this.writeEvent(LWXMLEvent.ATTRIBUTE_NAME);
        this.write(name);
        this.writeEvent(LWXMLEvent.ATTRIBUTE_VALUE);
        this.write(value);
    }

    public void writeEndEmptyElement() throws IOException {
        this.checkEndAttributeList();

        if (this.getCurrentEvent() != LWXMLEvent.END_ATTRIBUTE_LIST) {
            throw new LWXMLIllegalEventException(LWXMLEvent.END_EMPTY_ELEMENT);
        }
        this.writeEvent(LWXMLEvent.END_EMPTY_ELEMENT);
    }

    public void writeEndElement(final String name) throws IOException {
        if (name == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        if (this.getCurrentEvent() == LWXMLEvent.END_ATTRIBUTE_LIST) {
            this.writeEvent(LWXMLEvent.END_EMPTY_ELEMENT);
        } else {
            this.writeEvent(LWXMLEvent.END_ELEMENT);
            this.write(name);
        }
    }

    public void writeCharacters(final String value) throws IOException {
        if (value == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        this.writeEvent(LWXMLEvent.CHARACTERS);
        this.write(value);
    }

    public void writeCDATA(final String value) throws IOException {
        if (value == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        this.writeEvent(LWXMLEvent.CDATA);
        this.write(value);
    }

    public void writeComment(final String value) throws IOException {
        if (value == null) {
            throw new NullPointerException();
        }

        this.checkEndAttributeList();

        this.writeEvent(LWXMLEvent.COMMENT);
        this.write(value);
    }

    @Override
    public abstract void write(int c) throws IOException;

    @Override
    public void write(final char[] cbuf) throws IOException {
        this.write(cbuf, 0, cbuf.length);
    }

    @Override
    public abstract void write(char[] cbuf, int off, int len) throws IOException;

    @Override
    public void write(final String str) throws IOException {
        CharStreamUtil.writeCharwise(this, str);
    }

    @Override
    public void write(final String str, final int off, final int len) throws IOException {
        CharStreamUtil.writeCharwise(this, str, off, len);
    }

    public int write(final Reader in) throws IOException {
        return this.streamUtil.writeStream(in, this);
    }

    @Override
    public LWXMLWriter append(final char c) throws IOException {
        this.write(c);
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq) throws IOException {
        CharStreamUtil.appendCharwise(this, csq);
        return this;
    }

    @Override
    public LWXMLWriter append(final CharSequence csq, final int start, final int end) throws IOException {
        CharStreamUtil.appendCharwise(this, csq, start, end);
        return this;
    }

    @Override
    public void flush() throws IOException {}

    @Override
    public void close() throws IOException {}

}