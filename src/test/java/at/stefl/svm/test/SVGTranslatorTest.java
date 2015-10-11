package at.stefl.svm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

import org.junit.Test;

import at.stefl.svm.tosvg.SVGTranslator;

public class SVGTranslatorTest {
    @Test
    public void should_testName() throws Exception {
        final JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        final InputStream in = new FileInputStream(chooser.getSelectedFile());

        // InputStream in =
        // SVMListingTest.class.getResourceAsStream("test.svm");

        final File file = File.createTempFile("svm2svg", ".svg");
        final OutputStream out = new FileOutputStream(file);

        SVGTranslator.TRANSLATOR.translate(in, out);

        Runtime.getRuntime().exec("google-chrome " + file.getAbsolutePath());
    }

}