package at.stefl.commons;

import java.io.InputStream;

import org.junit.Test;

import at.stefl.commons.io.DividedCharArrayWriter;
import at.stefl.commons.io.FluidInputStreamReader;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;
import at.stefl.commons.lwxml.translator.LWXMLHierarchyTranslator;
import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;

public class LWXMLHierarchyTranslatorTest {

    @Test
    public void should_testName() throws Exception {
        final InputStream inputStream = LWXMLHierarchyTranslatorTest.class.getResourceAsStream("test.xml");
        final LWXMLReader in = new LWXMLStreamReader(new FluidInputStreamReader(inputStream));

        final DividedCharArrayWriter writer = new DividedCharArrayWriter();
        final LWXMLWriter out = new LWXMLStreamWriter(writer);

        final LWXMLHierarchyTranslator<Object> lwxmlTranslator = new LWXMLHierarchyTranslator<Object>();
        lwxmlTranslator.addElementTranslator("html", "xml");
        lwxmlTranslator.addElementTranslator("head", "kopf");
        lwxmlTranslator.addElementTranslator("title", "name");
        lwxmlTranslator.addElementTranslator("body", "bauch");
        lwxmlTranslator.addElementTranslator("empty", "leer");
        lwxmlTranslator.translate(in, out, null);

        out.close();

        System.out.println(writer);
    }

}