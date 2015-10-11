package at.stefl.commons;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import at.stefl.commons.io.TeeOutputStream;

public class TeeOutputStreamTest {

    @Test
    public void should_testName() throws Exception {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final TeeOutputStream teeOutputStream = new TeeOutputStream(outputStream, System.out);

        teeOutputStream.write("hallo welt!".getBytes());
        teeOutputStream.flush();

        teeOutputStream.close();
    }

}