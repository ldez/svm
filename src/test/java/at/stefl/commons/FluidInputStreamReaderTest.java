package at.stefl.commons;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import at.stefl.commons.io.TeeInputStream;

public class FluidInputStreamReaderTest {

    @Test
    public void should_testName() throws Exception {
        final String charsetName = "ISO-8859-1";

        final byte[] buffer = "¬".getBytes(charsetName);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        final TeeInputStream teeInputStream = new TeeInputStream(inputStream, System.out);
        final InputStreamReader reader = new InputStreamReader(teeInputStream, charsetName);

        System.out.println((char) reader.read());

        reader.close();
    }

}