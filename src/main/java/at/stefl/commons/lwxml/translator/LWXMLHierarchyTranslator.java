package at.stefl.commons.lwxml.translator;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import at.stefl.commons.io.StreamableStringMap;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLPushbackReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.writer.LWXMLWriter;
import at.stefl.commons.util.collection.OrderedPair;

public class LWXMLHierarchyTranslator<C> extends LWXMLTranslator<LWXMLReader, LWXMLWriter, C> {

    private final StreamableStringMap<LWXMLElementTranslator<? super C>> elementTranslators = new StreamableStringMap<LWXMLElementTranslator<? super C>>();

    public void addElementTranslator(final String element, final LWXMLElementTranslator<? super C> translator) {
        if (element == null) {
            throw new NullPointerException();
        }
        this.elementTranslators.put(element, translator);
    }

    public void addElementTranslator(final String element, final String newElement) {
        this.addElementTranslator(element, new LWXMLElementReplacement<C>(newElement));
    }

    public void removeElementTranslator(final String element) {
        this.elementTranslators.remove(element);
    }

    public Collection<LWXMLElementTranslator<? super C>> elementTranslators() {
        return Collections.unmodifiableCollection(this.elementTranslators.values());
    }

    @Override
    public void translate(final LWXMLReader in, final LWXMLWriter out, final C context) throws IOException {
        final LWXMLPushbackReader pin = new LWXMLPushbackReader(in);

        OrderedPair<String, LWXMLElementTranslator<? super C>> match = null;

        LWXMLEvent event;
        while (true) {
            event = pin.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }

            switch (event) {
                case START_ELEMENT:
                case END_ELEMENT:
                    match = this.elementTranslators.match(pin);
                case END_EMPTY_ELEMENT:
                    if (match == null) {
                        break;
                    }

                    final String elementName = match.getElement1();
                    final LWXMLElementTranslator<? super C> translator = match.getElement2();

                    pin.unreadEvent(elementName);
                    translator.translate(pin, out, context);

                    break;
                case CHARACTERS:
                    out.writeEvent(LWXMLEvent.CHARACTERS);
                    out.write(pin);
                    break;
                default:
                    break;
            }
        }
    }

}