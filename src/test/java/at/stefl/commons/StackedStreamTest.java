package at.stefl.commons;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;

import org.junit.Test;

import at.stefl.commons.io.ByteStreamUtil;
import at.stefl.commons.io.CountingInputStream;

public class StackedStreamTest {

    @Test
    public void should_testName() throws Exception {
        final JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        final File file = fileChooser.getSelectedFile();

        long start, end;

        InputStream in1 = new FileInputStream(file);
        in1 = new BufferedInputStream(in1);
        in1 = new CountingInputStream(in1);

        InputStream in2 = new FileInputStream(file);
        in2 = new BufferedInputStream(in2);
        in2 = new CountingInputStream(in2);
        in2 = new CountingInputStream(in2);
        in2 = new CountingInputStream(in2);
        in2 = new CountingInputStream(in2);
        in2 = new CountingInputStream(in2);

        start = System.nanoTime();

        ByteStreamUtil.flushBytewise(in1);

        end = System.nanoTime();
        System.out.println("time1: " + (end - start) / 1000000000d);

        start = System.nanoTime();

        ByteStreamUtil.flushBytewise(in2);

        end = System.nanoTime();
        System.out.println("time2: " + (end - start) / 1000000000d);
    }

}