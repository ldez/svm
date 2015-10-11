package at.stefl.commons.test;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.DividedByteArrayOutputStream;

public class DividedByteArrayOutputStreamTest {

    @Test
    public void should_testName() throws Exception {
        final DividedByteArrayOutputStream out = new DividedByteArrayOutputStream();

        out.write("HAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLOOOOOO".getBytes());

        final InputStream in = out.getInputStream();
        System.out.println(CharStreamUtil.readString(new InputStreamReader(in)));

        out.reset();
        try {
            System.out.println(in.read());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        out.close();
    }

}