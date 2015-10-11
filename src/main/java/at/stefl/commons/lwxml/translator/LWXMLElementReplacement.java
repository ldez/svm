package at.stefl.commons.lwxml.translator;

import java.io.IOException;

import at.stefl.commons.lwxml.reader.LWXMLPushbackReader;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLElementReplacement<C> extends LWXMLElementTranslator<C> {

    protected final String elementName;

    public LWXMLElementReplacement(final String elementName) {
        this.elementName = elementName;
    }

    @Override
    public void translateStartElement(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        out.writeStartElement(this.elementName);
    }

    @Override
    public void translateEndElement(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        out.writeEndElement(this.elementName);
    }

}