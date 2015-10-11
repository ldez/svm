package at.stefl.svm.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.RandomAccess;

import at.stefl.commons.io.ByteDataOutputStream;
import at.stefl.commons.math.RectangleI;
import at.stefl.commons.math.vector.Vector2i;
import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.svm.enumeration.SVMConstants;
import at.stefl.svm.enumeration.TextEncoding;
import at.stefl.svm.object.Color;
import at.stefl.svm.object.Fraction;

public class SVMDataOutputStream extends ByteDataOutputStream {

    private TextEncoding defaultEncoding = TextEncoding.ASCII_US;

    public SVMDataOutputStream(final OutputStream out) {
        super(out, SVMConstants.ENDIANNESS);
    }

    public SVMDataOutputStream(final OutputStream out, final SVMDataOutputStream state) {
        this(out);

        this.defaultEncoding = state.defaultEncoding;
    }

    public TextEncoding getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public void setDefaultEncoding(final TextEncoding defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    private void writeVector2i(final Vector2i vector) throws IOException {
        this.writeUnsignedInt(vector.getX());
        this.writeUnsignedInt(vector.getY());
    }

    public void writePoint(final Vector2i point) throws IOException {
        this.writeVector2i(point);
    }

    public void writeRectangleI(final RectangleI rectangle) throws IOException {
        this.writeInt(rectangle.getLeft());
        this.writeInt(rectangle.getTop());
        this.writeInt(rectangle.getRight());
        this.writeInt(rectangle.getBottom());
    }

    public void writePolygon(final List<Vector2i> polygon) throws IOException {
        final int pointCount = polygon.size();
        if ((pointCount & 0xffff0000) != 0) {
            throw new IllegalArgumentException("too much points");
        }

        this.writeUnsignedShort(pointCount);

        if (polygon instanceof RandomAccess) {
            for (int i = 0; i < pointCount; i++) {
                this.writePoint(polygon.get(i));
            }
        } else {
            for (final Vector2i point : polygon) {
                this.writePoint(point);
            }
        }
    }

    public void writePolyPolygon(final List<List<Vector2i>> polyPolygon) throws IOException {
        final int polygonCount = polyPolygon.size();
        PrimitiveUtil.checkUnsignedShort(polygonCount);

        this.writeUnsignedShort(polygonCount);

        if (polyPolygon instanceof RandomAccess) {
            for (int i = 0; i < polygonCount; i++) {
                this.writePolygon(polyPolygon.get(i));
            }
        } else {
            for (final List<Vector2i> polygon : polyPolygon) {
                this.writePolygon(polygon);
            }
        }
    }

    public void writeFraction(final Fraction fraction) throws IOException {
        this.writeInt(fraction.getNumeratior());
        this.writeInt(fraction.getDenominator());
    }

    public void writeSize(final Vector2i size) throws IOException {
        this.writeVector2i(size);
    }

    public void writeColor(final Color color) throws IOException {
        this.writeUnsignedShort(0x8000);
        this.writeUnsignedShort((color.getRed() << 8) | color.getRed());
        this.writeUnsignedShort((color.getGreen() << 8) | color.getGreen());
        this.writeUnsignedShort((color.getBlue() << 8) | color.getBlue());
    }

    public void writeColorInt(final Color color) throws IOException {
        this.writeInt(color.getARGB());
    }

    public void writeUnsignedShortPrefixedAsciiString(final String string) throws IOException {
        this.writeUnsignedShort(string.length());
        this.write(string.getBytes("US-ASCII"));
    }

    public void writeIntPrefixedUTF16String(final String string) throws IOException {
        this.writeInt(string.length());
        this.writeUnits(2, string.getBytes("UTF-16"));
    }

    public void writeUnsignedShortPrefixedUTF16String(final String string) throws IOException {
        this.writeUnsignedShort(string.length());
        this.writeUnits(2, string.getBytes("UTF-16"));
    }

    public void writeUnicodeOrAsciiString(final String string) throws IOException {
        if (this.defaultEncoding == TextEncoding.UCS2) {
            this.writeIntPrefixedUTF16String(string);
        } else {
            this.writeUnsignedShortPrefixedAsciiString(string);
        }
    }

}