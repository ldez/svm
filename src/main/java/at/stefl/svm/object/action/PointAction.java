package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class PointAction extends SVMAction {

    private Vector2i point;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PointAction [point=");
        builder.append(this.point);
        builder.append("]");
        return builder.toString();
    }

    public Vector2i getPoint() {
        return this.point;
    }

    public void setPoint(final Vector2i point) {
        this.point = point;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePoint(this.point);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.point = in.readPoint();
    }

}