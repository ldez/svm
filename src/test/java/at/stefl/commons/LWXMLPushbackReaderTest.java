package at.stefl.commons;

import java.io.InputStream;

import org.junit.Test;

import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLPushbackReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLPushbackReaderTest {

    @Test
    public void should_testName() throws Exception {
        final InputStream inputStream = LWXMLPushbackReaderTest.class.getResourceAsStream("test.xml");
        final LWXMLReader lwxmlReader = new LWXMLStreamReader(new FluidInputStreamReader(inputStream));
        final LWXMLPushbackReader in = new LWXMLPushbackReader(lwxmlReader);

        System.out.println(in.readEvent());

        in.unreadEvent(LWXMLEvent.ATTRIBUTE_VALUE, "value");
        in.unreadEvent(LWXMLEvent.ATTRIBUTE_NAME, "name");

        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }

            System.out.println(event);
            if (event.hasValue()) {
                System.out.println(in.readValue());
            }
        }

        in.close();
    }

}