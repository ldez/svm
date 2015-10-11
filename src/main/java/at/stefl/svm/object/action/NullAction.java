package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class NullAction extends SVMAction {

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NullAction []");
        return builder.toString();
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {}

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {}

}