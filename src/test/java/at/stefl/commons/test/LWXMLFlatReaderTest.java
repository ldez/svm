package at.stefl.commons.test;

import java.io.InputStream;

import org.junit.Test;

import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLFlatReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLFlatReaderTest {

    @Test
    public void should_testName() throws Exception {
        final InputStream inputStream = LWXMLFlatReaderTest.class.getResourceAsStream("test.xml");
        final LWXMLReader lwxmlReader = new LWXMLStreamReader(new FluidInputStreamReader(inputStream));
        final LWXMLFlatReader in = new LWXMLFlatReader(lwxmlReader);

        lwxmlReader.readEvent();

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