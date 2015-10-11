package at.stefl.commons;

import java.io.Reader;

import org.junit.Test;

import at.stefl.commons.io.CharStreamUtil;
import at.stefl.commons.io.DividedCharArrayWriter;

public class DividedCharArrayWriterTest {

    @Test
    public void should_testName() throws Exception {
        final DividedCharArrayWriter out = new DividedCharArrayWriter();

        out.write("HAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLOOOOOO");

        final Reader in = out.getReader();
        System.out.println(CharStreamUtil.readString(in));

        out.reset();
        try {
            System.out.println(in.read());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        out.close();
    }

}