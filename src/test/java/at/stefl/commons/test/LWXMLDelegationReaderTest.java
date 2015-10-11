package at.stefl.commons.test;

import java.io.InputStream;

import org.junit.Test;

import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLElementDelegationReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLDelegationReaderTest {

    @Test
    public void should_testName() throws Exception {
        final InputStream inputStream = LWXMLDelegationReaderTest.class.getResourceAsStream("test.xml");
        final LWXMLReader lwxmlReader = new LWXMLStreamReader(new FluidInputStreamReader(inputStream));
        final LWXMLElementDelegationReader in = new LWXMLElementDelegationReader(lwxmlReader);

        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println(in.readEvent());
        System.out.println();

        System.out.println("---------------");

        while (true) {
            final LWXMLEvent event = in.getElementReader().readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }

            System.out.println(event);
            if (event.hasValue()) {
                System.out.println("value: " + in.getElementReader().readValue());
            }
            System.out.println();
        }

        System.out.println("---------------");

        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }

            System.out.println(event);
            if (event.hasValue()) {
                System.out.println("value: " + in.readValue());
            }
            System.out.println();
        }

        in.close();
    }

}