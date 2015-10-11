package at.stefl.svm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.svm.tosvg.SVGStateWriter;
import at.stefl.svm.tosvg.SVGStateWriter.StyleCallback;

public class SVGStateWriterTest {

    @Test
    public void should_testName() throws Exception {

        final String fileName = "/home/andreas/svg.svg";

        final FileWriter writer = new FileWriter(fileName);
        final SVGStateWriter out = new SVGStateWriter(writer);

        out.writeHeader();

        out.writeCircle(new Vector2d(50, 50), 50, new StyleCallback() {
            @Override
            public void writeStyle(final Writer out1) throws IOException {
                out1.write("fill:rgb(255, 0, 0);");
            }
        });

        out.writeFooter();
        out.close();

        Runtime.getRuntime().exec("google-chrome " + fileName);
    }

}