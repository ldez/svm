package at.stefl.commons;

import org.junit.Test;

import at.stefl.commons.lwxml.writer.LWXMLEventListWriter;

public class LWXMLEventListWriterTest {

    @Test
    public void should_testName() throws Exception {
        final LWXMLEventListWriter writer = new LWXMLEventListWriter();

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
    }

}