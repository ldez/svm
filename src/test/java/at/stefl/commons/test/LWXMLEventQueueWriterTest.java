package at.stefl.commons.test;

import java.io.StringWriter;

import org.junit.Test;

import at.stefl.commons.lwxml.writer.LWXMLEventQueueWriter;
import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLEventQueueWriterTest {

    @Test
    public void should_testName() throws Exception {
        final LWXMLEventQueueWriter writer = new LWXMLEventQueueWriter();

        writer.writeStartElement("html");
        writer.writeAttribute("name", "value");
        writer.writeStartElement("head");
        writer.writeStartElement("title");
        writer.writeCharacters("html");
        writer.writeEndElement("title");
        writer.writeEndElement("head");
        writer.writeStartElement("body");
        writer.writeStartElement("empty");
        writer.writeEndElement("empty");
        writer.writeEndElement("body");
        writer.writeEndElement("html");

        writer.close();

        final StringWriter stringWriter = new StringWriter();
        final LWXMLWriter tmpWriter = new LWXMLStreamWriter(stringWriter);

        writer.writeTo(tmpWriter);

        tmpWriter.close();
        System.out.println(stringWriter.toString());
    }

}