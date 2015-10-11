package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.Color;

public class PixelAction extends SVMAction {

    private Vector2i point;
    private Color color;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PixelAction [point=");
        builder.append(this.point);
        builder.append(", color=");
        builder.append(this.color);
        builder.append("]");
        return builder.toString();
    }

    public Vector2i getPoint() {
        return this.point;
    }

    public Color getColorDefinition() {
        return this.color;
    }

    public void setPoint(final Vector2i point) {
        this.point = point;
    }

    public void setColorDefinition(final Color color) {
        this.color = color;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePoint(this.point);
        out.writeColorInt(this.color);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.point = in.readPoint();
        this.color = in.readColorInt();
    }

}