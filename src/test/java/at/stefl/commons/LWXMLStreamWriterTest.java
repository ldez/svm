package at.stefl.commons;

import java.io.StringWriter;

import org.junit.Test;

import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLStreamWriterTest {

    @Test
    public void should_testName() throws Exception {
        final StringWriter writer = new StringWriter();
        final LWXMLWriter lwxmlWriter = new LWXMLStreamWriter(writer);

        lwxmlWriter.writeStartElement("html");
        lwxmlWriter.writeAttribute("name", "value");
        lwxmlWriter.writeStartElement("head");
        lwxmlWriter.writeStartElement("title");
        lwxmlWriter.writeCharacters("html");
        lwxmlWriter.writeEndElement("title");
        lwxmlWriter.writeEndElement("head");
        lwxmlWriter.writeStartElement("body");
        lwxmlWriter.writeStartElement("empty");
        lwxmlWriter.writeEndElement("empty");
        lwxmlWriter.writeEndElement("body");
        lwxmlWriter.writeEndElement("html");

        lwxmlWriter.close();
        System.out.println(writer.toString());
    }

}