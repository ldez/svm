package at.stefl.commons.lwxml.translator;

import java.io.IOException;

import at.stefl.commons.lwxml.LWXMLIllegalEventException;
import at.stefl.commons.lwxml.LWXMLUtil;
import at.stefl.commons.lwxml.reader.LWXMLPushbackReader;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLNothingTranslator<C> extends LWXMLElementTranslator<C> {

    @Override
    public void translateStartElement(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        LWXMLUtil.flushElement(in);
    }

    @Override
    public void translateAttributeList(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {}

    @Override
    public void translateEndAttributeList(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {}

    @Override
    public void translateChildren(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {}

    @Override
    public void translateEndElement(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        throw new LWXMLIllegalEventException(in);
    }

}