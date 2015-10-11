package at.stefl.commons.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.junit.Test;

import at.stefl.commons.lwxml.LWXMLEvent;
import at.stefl.commons.lwxml.reader.LWXMLReader;
import at.stefl.commons.lwxml.reader.LWXMLStreamReader;

public class LWXMLPerformanceTest {

    @Test
    public void should_testName() throws Exception {
        final JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        final File file = fileChooser.getSelectedFile();

        int eventCount;
        long start, end;

        final LWXMLReader lwxmlReader = new LWXMLStreamReader(new BufferedReader(new FileReader(file)));

        final XMLInputFactory factory = XMLInputFactory.newInstance();
        final XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(file));

        eventCount = 0;
        start = System.nanoTime();

        while (true) {
            final LWXMLEvent event = lwxmlReader.readEvent();
            eventCount++;
            if (event == LWXMLEvent.END_DOCUMENT) {
                break;
            }
        }

        end = System.nanoTime();
        lwxmlReader.close();
        System.out.println("my time: " + (end - start) / 1000000000d);
        System.out.println(eventCount);

        Thread.sleep(1000);

        eventCount = 0;
        start = System.nanoTime();

        while (parser.hasNext()) {
            parser.next();
            eventCount++;
        }

        end = System.nanoTime();
        System.out.println("java time: " + (end - start) / 1000000000d);
        System.out.println(eventCount);
    }

}