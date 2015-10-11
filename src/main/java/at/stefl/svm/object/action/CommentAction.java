package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class CommentAction extends SVMAction {

    private String comment;
    private int value;
    private byte[] data;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CommentAction [comment=");
        builder.append(this.comment);
        builder.append(", value=");
        builder.append(this.value);
        builder.append(", data=");
        builder.append(ArrayUtil.toHexString(this.data));
        builder.append("]");
        return builder.toString();
    }

    public String getComment() {
        return this.comment;
    }

    public int getValue() {
        return this.value;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShortPrefixedAsciiString(this.comment);
        out.writeInt(this.value);
        out.writeUnsignedInt(this.data.length);
        out.write(this.data);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.comment = in.readUnsignedShortPrefixedAsciiString();
        this.value = in.readInt();
        final long dataSize = in.readUnsignedInt();
        this.data = in.readFully((int) dataSize);
    }

}