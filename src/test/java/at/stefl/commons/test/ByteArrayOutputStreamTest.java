package at.stefl.commons.test;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import at.stefl.commons.io.DividedByteArrayOutputStream;

public class ByteArrayOutputStreamTest {

    @Test
    public void should_testName() throws Exception {
        long start;
        long end;

        start = System.nanoTime();
        {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();

            for (int i = 0; i < 100000000; i++) {
                out.write(i);
            }

            out.toByteArray();
            out.close();
        }
        end = System.nanoTime();
        System.out.println("java " + (end - start) / 1000000000d);

        start = System.nanoTime();
        {
            final DividedByteArrayOutputStream out = new DividedByteArrayOutputStream();

            for (int i = 0; i < 100000000; i++) {
                out.write(i);
            }

            out.toByteArray();
            out.close();
        }
        end = System.nanoTime();
        System.out.println("me " + (end - start) / 1000000000d);
    }

}