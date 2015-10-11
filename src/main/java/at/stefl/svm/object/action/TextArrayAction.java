package at.stefl.svm.object.action;

import java.io.IOException;
import java.util.Arrays;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class TextArrayAction extends SVMAction {

    private Vector2i startPoint;
    private String string;
    private int[] dxArray;
    private int index;
    private int length;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TextArrayAction [startPoint=");
        builder.append(this.startPoint);
        builder.append(", string=");
        builder.append(this.string);
        builder.append(", dxArray=");
        builder.append(Arrays.toString(this.dxArray));
        builder.append(", index=");
        builder.append(this.index);
        builder.append(", length=");
        builder.append(this.length);
        builder.append("]");
        return builder.toString();
    }

    public Vector2i getStartPoint() {
        return this.startPoint;
    }

    public String getString() {
        return this.string;
    }

    public int[] getDxArray() {
        return this.dxArray;
    }

    public int getIndex() {
        return this.index;
    }

    public int getLength() {
        return this.length;
    }

    public void setStartPoint(final Vector2i startPoint) {
        this.startPoint = startPoint;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public void setDxArray(final int[] dxArray) {
        this.dxArray = dxArray;
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
        out.writePoint(this.startPoint);
        out.writeUnicodeOrAsciiString(this.string);
        out.writeUnsignedShort(this.index);
        out.writeUnsignedShort(this.length);

        // TODO: unsigned
        out.writeInt(this.dxArray.length);
        for (final int element : this.dxArray) {
            out.writeInt(element);
        }

        // version 2
        out.writeUnsignedShortPrefixedUTF16String(this.string);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.startPoint = in.readPoint();
        this.string = in.readUnicodeOrAsciiString();
        this.index = in.readUnsignedShort();
        this.length = in.readUnsignedShort();

        // TODO: unsigned
        final int dxArrayLength = in.readInt();
        this.dxArray = new int[dxArrayLength];
        for (int i = 0; i < dxArrayLength; i++) {
            this.dxArray[i] = in.readInt();
        }

        if (version >= 2) {
            this.string = in.readUnsignedShortPrefixedUTF16String();
        }
    }

}