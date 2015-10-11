package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.commons.util.string.StringUtil;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class PushAction extends SVMAction {

    private int flags;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PushAction [flags=");
        builder.append(StringUtil.fillFront(Integer.toBinaryString(this.flags), '0', 16));
        builder.append("]");
        return builder.toString();
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(final int flags) {
        PrimitiveUtil.checkUnsignedShort(flags);
        this.flags = flags;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShort(this.flags);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.flags = in.readUnsignedShort();
    }

}