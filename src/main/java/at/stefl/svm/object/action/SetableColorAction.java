package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public abstract class SetableColorAction extends ColorAction {

    private boolean set;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append(" [color=");
        builder.append(this.getColor());
        builder.append(", set=");
        builder.append(this.set);
        builder.append("]");
        return builder.toString();
    }

    public boolean isSet() {
        return this.set;
    }

    public void setSet(final boolean set) {
        this.set = set;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        super.serializeContent(out);
        out.writeBoolean(this.set);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        super.deserializeContent(in, version, length);
        this.set = in.readBoolean();
    }

}