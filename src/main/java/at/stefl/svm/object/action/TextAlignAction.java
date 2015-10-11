package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class TextAlignAction extends SVMAction {

    private int align;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("TextAlignAction [align=");
        builder.append(this.align);
        builder.append("]");
        return builder.toString();
    }

    public int getAlign() {
        return this.align;
    }

    public void setAlign(final int align) {
        this.align = align;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShort(this.align);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.align = in.readUnsignedShort();
    }

}