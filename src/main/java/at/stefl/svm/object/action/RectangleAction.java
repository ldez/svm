package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.math.RectangleI;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class RectangleAction extends SVMAction {

    private RectangleI rectangle;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RectangleAction [rectangle=");
        builder.append(this.rectangle);
        builder.append("]");
        return builder.toString();
    }

    public RectangleI getRectangle() {
        return this.rectangle;
    }

    public void setRectangle(final RectangleI rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeRectangleI(this.rectangle);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.rectangle = in.readRectangleI();
    }

}