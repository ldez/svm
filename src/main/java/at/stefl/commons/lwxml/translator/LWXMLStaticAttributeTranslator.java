package at.stefl.commons.lwxml.translator;

import java.io.IOException;

import at.stefl.commons.lwxml.LWXMLAttribute;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLStaticAttributeTranslator<C> implements LWXMLSimpleAttributeTranslator<C> {

    private final String newAttributeName;

    public LWXMLStaticAttributeTranslator(final String newAttributeName) {
        if (newAttributeName == null) {
            throw new NullPointerException();
        }

        this.newAttributeName = newAttributeName;
    }

    @Override
    public void translate(final LWXMLAttribute in, final LWXMLWriter out, final C context) throws IOException {
        out.writeAttribute(this.newAttributeName, in.getValue());
    }

}