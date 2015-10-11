package at.stefl.svm.tosvg;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import at.stefl.commons.lwxml.writer.LWXMLStreamWriter;
import at.stefl.commons.lwxml.writer.LWXMLWriter;
import at.stefl.commons.math.RectangleD;
import at.stefl.commons.math.vector.Vector2d;

public class SVGStateWriter {

    public interface StyleCallback {
        public void writeStyle(Writer out) throws IOException;
    }

    private final LWXMLWriter out;

    private boolean started;
    private boolean ended;
    private boolean openElement;
    private boolean attributeWriteable;

    public SVGStateWriter(final Writer out) {
        this.out = new LWXMLStreamWriter(out);
    }

    public SVGStateWriter(final LWXMLWriter out) {
        this.out = out;
    }

    public void writeHeader() throws IOException {
        this.out.writeStartElement("svg");
        this.out.writeAttribute("xmlns", "http://www.w3.org/2000/svg");
        this.out.writeAttribute("version", "1.1");

        this.started = true;
        this.attributeWriteable = true;
    }

    public void writeFooter() throws IOException {
        this.preWrite();

        this.out.writeEndElement("svg");

        this.ended = true;
        this.attributeWriteable = false;
    }

    public void writeAttribute(final String name, final String value) throws IOException {
        if (!this.attributeWriteable) {
            throw new IllegalStateException("cannot write attribute in current state");
        }

        this.out.writeAttribute(name, value);
    }

    private void closeOpenElement() throws IOException {
        this.out.writeEndEmptyElement();

        this.openElement = false;
        this.attributeWriteable = false;
    }

    private void preWrite() throws IOException {
        if (!this.started) {
            throw new IllegalStateException("header was not written since now");
        }
        if (this.ended) {
            throw new IllegalStateException("footer already writen");
        }
        if (this.openElement) {
            this.closeOpenElement();
        }
    }

    private void postWrite(final StyleCallback styleCallback) throws IOException {
        this.openElement = true;
        this.attributeWriteable = true;

        if (styleCallback != null) {
            this.out.writeAttribute("style", "");
            styleCallback.writeStyle(this.out);
        }
    }

    private void writeVector2iAttributes(final Vector2d vector, final String xAttribute, final String yAttribute) throws IOException {
        this.out.writeAttribute(xAttribute, "" + vector.getX());
        this.out.writeAttribute(yAttribute, "" + vector.getY());
    }

    private void writePointAttributes(final Vector2d point) throws IOException {
        this.writeVector2iAttributes(point, "x", "y");
    }

    private void writePoint1Attributes(final Vector2d point) throws IOException {
        this.writeVector2iAttributes(point, "x1", "y1");
    }

    private void writePoint2Attributes(final Vector2d point) throws IOException {
        this.writeVector2iAttributes(point, "x2", "y2");
    }

    private void writePointsAttributes(final Collection<Vector2d> points) throws IOException {
        this.out.writeAttribute("points", "");

        for (final Vector2d point : points) {
            this.out.write("" + point.getX());
            this.out.write(",");
            this.out.write("" + point.getY());
            // TODO: remove on last
            this.out.write(" ");
        }
    }

    private void writeCenterAttributes(final Vector2d center) throws IOException {
        this.writeVector2iAttributes(center, "cx", "cy");
    }

    private void writeRadiusAttribute(final double radius) throws IOException {
        this.writeRadiusAttributes(new Vector2d(radius));
    }

    private void writeRadiusAttributes(final Vector2d radius) throws IOException {
        this.writeVector2iAttributes(radius, "rx", "ry");
    }

    private void writeRectangleAttributes(final RectangleD rectangle) throws IOException {
        this.writePointAttributes(rectangle.getLeftTop());
        this.out.writeAttribute("width", "" + rectangle.getWidth());
        this.out.writeAttribute("height", "" + rectangle.getHeight());
    }

    public void writeLine(final Vector2d point1, final Vector2d point2) throws IOException {
        this.writeLine(point1, point2, null);
    }

    public void writeLine(final Vector2d point1, final Vector2d point2, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("line");
        this.writePoint1Attributes(point1);
        this.writePoint2Attributes(point2);

        this.postWrite(styleCallback);
    }

    public void writeRectange(final RectangleD rectangle) throws IOException {
        this.writeRectange(rectangle, (StyleCallback) null);
    }

    public void writeRectange(final RectangleD rectangle, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("rect");
        this.writeRectangleAttributes(rectangle);

        this.postWrite(styleCallback);
    }

    public void writeRectange(final RectangleD rectangle, final Vector2d radius) throws IOException {
        this.writeRectange(rectangle, radius, null);
    }

    public void writeRectange(final RectangleD rectangle, final Vector2d radius, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("rect");
        this.writeRectangleAttributes(rectangle);
        this.writeRadiusAttributes(radius);

        this.postWrite(styleCallback);
    }

    public void writeCircle(final Vector2d center, final double radius) throws IOException {
        this.writeCircle(center, radius, null);
    }

    public void writeCircle(final Vector2d center, final double radius, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("ellipse");
        this.writeCenterAttributes(center);
        this.writeRadiusAttribute(radius);

        this.postWrite(styleCallback);
    }

    public void writeEllipse(final Vector2d center, final Vector2d radius) throws IOException {
        this.writeEllipse(center, radius, null);
    }

    public void writeEllipse(final Vector2d center, final Vector2d radius, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("ellipse");
        this.writeCenterAttributes(center);

        this.postWrite(styleCallback);
    }

    public void writePolyLine(final Collection<Vector2d> points) throws IOException {
        this.writePolyLine(points, null);
    }

    public void writePolyLine(final Collection<Vector2d> points, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("polyline");
        this.writePointsAttributes(points);

        this.postWrite(styleCallback);
    }

    public void writePolygon(final Collection<Vector2d> points) throws IOException {
        this.writePolygon(points, null);
    }

    public void writePolygon(final Collection<Vector2d> points, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("polygon");
        this.writePointsAttributes(points);

        this.postWrite(styleCallback);
    }

    public void writeText(final Vector2d point, final String text) throws IOException {
        this.writeText(point, text, null);
    }

    public void writeText(final Vector2d point, final String text, final StyleCallback styleCallback) throws IOException {
        this.preWrite();

        this.out.writeStartElement("text");
        this.writePointAttributes(point);

        this.postWrite(styleCallback);

        this.out.writeCharacters(text);

        this.out.writeEndElement("text");
        this.openElement = false;
        this.attributeWriteable = false;
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        this.out.close();
    }

}