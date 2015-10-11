package at.stefl.svm.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import at.stefl.commons.io.ByteDataInputStream;
import at.stefl.commons.math.RectangleI;
import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.enumeration.SVMConstants;
import at.stefl.svm.enumeration.TextEncoding;
import at.stefl.svm.object.Color;
import at.stefl.svm.object.Fraction;

public class SVMDataInputStream extends ByteDataInputStream {

    private TextEncoding defaultEncoding = TextEncoding.ASCII_US;

    public SVMDataInputStream(final InputStream in) {
        super(in, SVMConstants.ENDIANNESS);
    }

    public SVMDataInputStream(final InputStream in, final SVMDataInputStream state) {
        this(in);

        this.defaultEncoding = state.defaultEncoding;
    }

    public TextEncoding getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public void setDefaultEncoding(final TextEncoding defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    private Vector2i readVector2i() throws IOException {
        return new Vector2i(this.readInt(), this.readInt());
    }

    public Vector2i readPoint() throws IOException {
        return this.readVector2i();
    }

    public RectangleI readRectangleI() throws IOException {
        return new RectangleI(this.readInt(), this.readInt(), this.readInt(), this.readInt());
    }

    public List<Vector2i> readPolygon() throws IOException {
        final int pointCount = this.readUnsignedShort();
        final List<Vector2i> result = new ArrayList<Vector2i>(pointCount);

        for (int i = 0; i < pointCount; i++) {
            final Vector2i point = this.readPoint();
            result.add(point);
        }

        return result;
    }

    public List<List<Vector2i>> readPolyPolygon() throws IOException {
        final int polygonCount = this.readUnsignedShort();
        final List<List<Vector2i>> result = new ArrayList<List<Vector2i>>();

        for (int i = 0; i < polygonCount; i++) {
            final List<Vector2i> polygon = this.readPolygon();
            result.add(polygon);
        }

        return result;
    }

    public Fraction readFraction() throws IOException {
        final Fraction result = new Fraction();

        result.setNumeratior(this.readInt());
        result.setDenominator(this.readInt());

        return result;
    }

    public Vector2i readSize() throws IOException {
        return this.readVector2i();
    }

    public Color readColor() throws IOException {
        final int name = this.readUnsignedShort();
        if ((name & 0x8000) != 0) {
            throw new IllegalStateException();
        }

        final int red = this.readUnsignedShort() >> 8;
        final int green = this.readUnsignedShort() >> 8;
        final int blue = this.readUnsignedShort() >> 8;

        return new Color(red, green, blue);
    }

    public Color readColorInt() throws IOException {
        return new Color(this.readInt());
    }

    public String readUnsignedShortPrefixedAsciiString() throws IOException {
        final int size = this.readUnsignedShort();
        final byte[] bytes = new byte[size];
        this.readFully(bytes);
        return new String(bytes, "US-ASCII");
    }

    public String readIntPrefixedUTF16String() throws IOException {
        final int size = this.readInt();
        final byte[] bytes = new byte[size * 2];
        this.readUnits(2, bytes);
        return new String(bytes, "UTF-16");
    }

    public String readUnsignedShortPrefixedUTF16String() throws IOException {
        final int size = this.readUnsignedShort();
        final byte[] bytes = new byte[size * 2];
        this.readUnits(2, bytes);
        return new String(bytes, "UTF-16");
    }

    public String readUnicodeOrAsciiString() throws IOException {
        if (this.defaultEncoding == TextEncoding.UCS2) {
            return this.readIntPrefixedUTF16String();
        }
        return this.readUnsignedShortPrefixedAsciiString();
    }

}