package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class TextAction extends SVMAction {

    private Vector2i point;
    private String string;
    private int index;
    private int length;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TextAction [point=");
        builder.append(this.point);
        builder.append(", string=");
        builder.append(this.string);
        builder.append(", index=");
        builder.append(this.index);
        builder.append(", length=");
        builder.append(this.length);
        builder.append("]");
        return builder.toString();
    }

    public Vector2i getPoint() {
        return this.point;
    }

    public String getString() {
        return this.string;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLength() {
        return this.length;
    }

    public void setPoint(final Vector2i point) {
        this.point = point;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    @Override
    protected int getVersion() {
        return 2;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePoint(this.point);
        out.writeUnicodeOrAsciiString(this.string);
        out.writeUnsignedShort(this.index);
        out.writeUnsignedShort(this.length);

        // version 2
        out.writeUnsignedShortPrefixedUTF16String(this.string);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.point = in.readPoint();
        this.string = in.readUnicodeOrAsciiString();
        this.index = in.readUnsignedShort();
        this.length = in.readUnsignedShort();

        if (version >= 2) {
            this.string = in.readUnsignedShortPrefixedUTF16String();
        }
    }

}