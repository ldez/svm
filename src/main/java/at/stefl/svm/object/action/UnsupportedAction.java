package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.io.ByteStreamUtil;
import at.stefl.commons.util.array.ArrayUtil;
import at.stefl.svm.enumeration.ActionType;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

// TODO: remove me (debugging only)
public class UnsupportedAction extends SVMAction {

    private ActionType actionType;
    private int version;
    private byte[] data;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UnsupportedAction [actionType=");
        builder.append(this.actionType);
        builder.append(", version=");
        builder.append(this.version);
        builder.append(", length=");
        builder.append(this.data.length);
        builder.append(", data=");
        builder.append(ArrayUtil.toHexString(this.data));
        builder.append("]");
        return builder.toString();
    }

    @Override
    public ActionType getActionType() {
        return this.actionType;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setActionType(final ActionType actionType) {
        this.actionType = actionType;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.write(this.data);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.version = version;

        if (length <= Integer.MAX_VALUE) {
            this.data = ByteStreamUtil.readFully(in, (int) length);
        } else {
            this.data = null;
        }
    }

}