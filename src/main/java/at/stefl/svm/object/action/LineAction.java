package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.basic.LineInfo;

public class LineAction extends SVMAction {

    private LineInfo lineInfo;
    private Vector2i startPoint;
    private Vector2i endPoint;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LineAction [lineInfo=");
        builder.append(this.lineInfo);
        builder.append(", startPoint=");
        builder.append(this.startPoint);
        builder.append(", endPoint=");
        builder.append(this.endPoint);
        builder.append("]");
        return builder.toString();
    }

    public LineInfo getLineInfo() {
        return this.lineInfo;
    }

    public Vector2i getStartPoint() {
        return this.startPoint;
    }

    public Vector2i getEndPoint() {
        return this.endPoint;
    }

    public void setLineInfo(final LineInfo lineInfo) {
        this.lineInfo = lineInfo;
    }

    public void setStartPoint(final Vector2i startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(final Vector2i endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    protected int getVersion() {
        return 2;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePoint(this.startPoint);
        out.writePoint(this.endPoint);

        // version 2
        this.lineInfo.serialize(out);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.startPoint = in.readPoint();
        this.endPoint = in.readPoint();

        if (version >= 2) {
            this.lineInfo = new LineInfo();
            this.lineInfo.deserialize(in);
        }
    }

}