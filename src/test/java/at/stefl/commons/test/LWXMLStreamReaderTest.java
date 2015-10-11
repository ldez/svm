package at.stefl.commons.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLStreamReaderTest {

    @Test
    public void should_testName() throws Exception {
        InputStream inputStream = LWXMLStreamReaderTest.class.getResourceAsStream("test.xml");
        inputStream = new FileInputStream("/home/andreas/test.odt.html");
        final LWXMLReader in = new LWXMLStreamReader(new FluidInputStreamReader(inputStream));

        final long start = System.nanoTime();

        while (true) {
            final LWXMLEvent event = in.readEvent();
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }

            System.out.println(event);

            switch (event) {
                case PROCESSING_INSTRUCTION_TARGET:
                    System.out.println("pi target: " + in.readValue());
                    in.readEvent();
                    System.out.println("pi data: " + in.readValue());
                    break;
                case START_ELEMENT:
                    System.out.println("name: " + in.readValue());
                    break;
                case ATTRIBUTE_NAME:
                    System.out.println("attribute name: " + in.readValue());
                    in.readEvent();
                    System.out.println("attribute value: " + in.readValue());
                    break;
                case END_ATTRIBUTE_LIST:
                    break;
                case END_EMPTY_ELEMENT:
                    break;
                case END_ELEMENT:
                    System.out.println("name: " + in.readValue());
                    break;
                case COMMENT:
                    System.out.println("comment: " + in.readValue());
                    break;
                case CHARACTERS:
                    System.out.println("characters: " + in.readValue());
                    break;
                default:
                    break;
            }

            System.out.println();
        }

        final long end = System.nanoTime();
        System.out.println("time: " + (end - start) / 1000000000d);

        in.close();
    }

}