package at.stefl.commons.lwxml.translator;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import at.stefl.commons.io.StreamableStringMap;
import at.stefl.commons.lwxml.LWXMLAttribute;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.LWXMLIllegalEventException;
import at.stefl.commons.lwxml.reader.LWXMLPushbackReader;
import at.stefl.commons.lwxml.writer.LWXMLWriter;
import at.stefl.commons.util.collection.OrderedPair;

// TODO: thread-safe
public abstract class LWXMLElementTranslator<C> extends LWXMLTranslator<LWXMLPushbackReader, LWXMLWriter, C> {

    private final StreamableStringMap<LWXMLSimpleAttributeTranslator<? super C>> attributeTranslatorMap = new StreamableStringMap<LWXMLSimpleAttributeTranslator<? super C>>();
    private final Map<LWXMLComplexAttributeTranslator<? super C>, String[]> complexAttributeTranslatorMap = new HashMap<LWXMLComplexAttributeTranslator<? super C>, String[]>();

    private final Set<String> parseAttributes = new HashSet<String>();
    private final Map<String, String> currentParsedAttributes = new HashMap<String, String>();

    private final Map<String, String> newAttributes = new HashMap<String, String>();

    public Map<String, String> getCurrentParsedAttributes() {
        return this.currentParsedAttributes;
    }

    public String getCurrentParsedAttribute(final String attribute) {
        return this.currentParsedAttributes.get(attribute);
    }

    public boolean addAttributeTranslator(final String attribute, final String newAttribute) {
        return this.addAttributeTranslator(attribute, new LWXMLStaticAttributeTranslator<C>(newAttribute));
    }

    public boolean addAttributeTranslator(final String attribute, final LWXMLSimpleAttributeTranslator<? super C> translator) {
        if (attribute == null) {
            throw new NullPointerException();
        }
        if (translator == null) {
            throw new NullPointerException();
        }

        this.attributeTranslatorMap.put(attribute, translator);
        return true;
    }

    public void addComplexAttributeTranslator(final LWXMLComplexAttributeTranslator<? super C> translator, final String... attributes) {
        this.complexAttributeTranslatorMap.put(translator, attributes.clone());

        for (final String attribute : attributes) {
            this.addParseAttribute(attribute);
        }
    }

    public void addParseAttribute(final String attribute) {
        if (attribute == null) {
            throw new NullPointerException();
        }

        this.parseAttributes.add(attribute);
        if (!this.attributeTranslatorMap.containsKey(attribute)) {
            this.attributeTranslatorMap.put(attribute, null);
        }
    }

    public void addNewAttribute(final LWXMLAttribute attribute) {
        if (attribute == null) {
            throw new NullPointerException();
        }

        this.newAttributes.put(attribute.getName(), attribute.getValue());
    }

    public void addNewAttribute(final String name, final String value) {
        this.addNewAttribute(new LWXMLAttribute(name, value));
    }

    public void removeAttributeTranslator(final String attribute) {
        this.attributeTranslatorMap.remove(attribute);
    }

    public void removeParseAttribute(final String attribute) {
        this.parseAttributes.remove(attribute);
    }

    public void removeNewAttribute(final String attribute) {
        this.newAttributes.remove(attribute);
    }

    @Override
    public final void translate(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        final LWXMLEvent event = in.readEvent();

        switch (event) {
            case START_ELEMENT:
                this.translateStartElement(in, out, context);
                this.translateAttributeList(in, out, context);
                this.translateEndAttributeList(in, out, context);
                this.translateChildren(in, out, context);
                this.currentParsedAttributes.clear();
                break;
            case END_EMPTY_ELEMENT:
            case END_ELEMENT:
                this.translateEndElement(in, out, context);
                break;
            default:
                throw new LWXMLIllegalEventException(event);
        }
    }

    public abstract void translateStartElement(LWXMLPushbackReader in, LWXMLWriter out, C context) throws IOException;

    public void translateAttribute(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        final OrderedPair<String, LWXMLSimpleAttributeTranslator<? super C>> match = this.attributeTranslatorMap.match(in);
        if (match == null) {
            return;
        }

        final String attributeName = match.getElement1();
        final String attributeValue = in.readFollowingValue();

        if (this.parseAttributes.contains(attributeName)) {
            this.currentParsedAttributes.put(attributeName, attributeValue);
        }

        final LWXMLSimpleAttributeTranslator<? super C> attributeTranslator = match.getElement2();
        if (attributeTranslator == null) {
            return;
        }
        attributeTranslator.translate(new LWXMLAttribute(attributeName, attributeValue), out, context);
    }

    public void translateAttributeList(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        for (final Map.Entry<String, String> attribute : this.newAttributes.entrySet()) {
            out.writeAttribute(attribute.getKey(), attribute.getValue());
        }

        loop: while (true) {
            final LWXMLEvent event = in.readEvent();

            switch (event) {
                case ATTRIBUTE_NAME:
                    this.translateAttribute(in, out, context);
                case ATTRIBUTE_VALUE:
                    break;
                case END_ATTRIBUTE_LIST:
                    break loop;
                default:
                    throw new LWXMLIllegalEventException(event);
            }
        }

        for (final Map.Entry<LWXMLComplexAttributeTranslator<? super C>, String[]> entry : this.complexAttributeTranslatorMap.entrySet()) {
            // TODO: improve? read only?
            final Map<String, String> attributes = new HashMap<String, String>(entry.getValue().length);

            for (final String name : entry.getValue()) {
                final String value = this.currentParsedAttributes.get(name);
                if (value == null) {
                    continue;
                }
                attributes.put(name, value);
            }

            entry.getKey().translate(attributes, out, context);
        }
    }

    public void translateEndAttributeList(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {
        out.writeEvent(LWXMLEvent.END_ATTRIBUTE_LIST);
    }

    public void translateChildren(final LWXMLPushbackReader in, final LWXMLWriter out, final C context) throws IOException {}

    public abstract void translateEndElement(LWXMLPushbackReader in, LWXMLWriter out, C context) throws IOException;

}