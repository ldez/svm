package at.stefl.commons;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import at.stefl.commons.io.TeeInputStream;

public class TeeInputStreamTest {

    @Test
    public void should_testName() throws Exception {
        final byte[] array = "hallo welt!".getBytes();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
        final TeeInputStream teeInputStream = new TeeInputStream(inputStream, System.out);

        teeInputStream.read(new byte[array.length]);

        teeInputStream.close();
    }

}