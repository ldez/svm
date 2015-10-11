package at.stefl.svm.test;

import java.io.FileWriter;

import org.junit.Test;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.svm.tosvg.SVGStateWriter;

public class SVGStateWriterTest {

    @Test
    public void should_testName() throws Exception {

        final String fileName = "/home/andreas/svg.svg";

        final FileWriter writer = new FileWriter(fileName);
        final SVGStateWriter out = new SVGStateWriter(writer);

        out.writeHeader();

        out.writeCircle(new Vector2d(50, 50), 50, out1 -> out1.write("fill:rgb(255, 0, 0);"));

        out.writeFooter();
        out.close();

        Runtime.getRuntime().exec("google-chrome " + fileName);
    }

}