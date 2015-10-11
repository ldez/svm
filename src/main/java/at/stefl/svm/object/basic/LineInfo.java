package at.stefl.svm.object.basic;

import java.io.IOException;

import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.SVMVersionObject;

public class LineInfo extends SVMVersionObject {

    private int lineStyle;
    private int width;

    private int dashCount;
    private int dashLength;
    private int dotCount;
    private int dotLength;
    private int distance;

    private int lineJoin;

    // TODO: implement version 4

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LineInfo [lineStyle=");
        builder.append(this.lineStyle);
        builder.append(", width=");
        builder.append(this.width);
        builder.append(", dashCount=");
        builder.append(this.dashCount);
        builder.append(", dashLength=");
        builder.append(this.dashLength);
        builder.append(", dotCount=");
        builder.append(this.dotCount);
        builder.append(", dotLength=");
        builder.append(this.dotLength);
        builder.append(", distance=");
        builder.append(this.distance);
        builder.append(", lineJoin=");
        builder.append(this.lineJoin);
        builder.append("]");
        return builder.toString();
    }

    public int getLineStyle() {
        return this.lineStyle;
    }

    public int getWidth() {
        return this.width;
    }

    public int getDashCount() {
        return this.dashCount;
    }

    public int getDashLength() {
        return this.dashLength;
    }

    public int getDotCount() {
        return this.dotCount;
    }

    public int getDotLength() {
        return this.dotLength;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getLineJoin() {
        return this.lineJoin;
    }

    public void setLineStyle(final int lineStyle) {
        PrimitiveUtil.checkUnsignedShort(lineStyle);
        this.lineStyle = lineStyle;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public void setDashCount(final int dashCount) {
        PrimitiveUtil.checkUnsignedShort(dashCount);
        this.dashCount = dashCount;
    }

    public void setDashLength(final int dashLength) {
        this.dashLength = dashLength;
    }

    public void setDotCount(final int dotCount) {
        PrimitiveUtil.checkUnsignedShort(dotCount);
        this.dotCount = dotCount;
    }

    public void setDotLength(final int dotLength) {
        this.dotLength = dotLength;
    }

    public void setDistance(final int distance) {
        this.distance = distance;
    }

    public void setLineJoin(final int lineJoin) {
        PrimitiveUtil.checkUnsignedShort(lineJoin);
        this.lineJoin = lineJoin;
    }

    @Override
    public int getVersion() {
        return 3;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShort(this.lineStyle);
        out.writeInt(this.width);

        // version 2
        out.writeUnsignedShort(this.dashCount);
        out.writeInt(this.dashLength);
        out.writeUnsignedShort(this.dotCount);
        out.writeInt(this.dotLength);
        out.writeInt(this.distance);

        // version 3
        out.writeUnsignedShort(this.lineJoin);

        // TODO: implement version 4
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.lineStyle = in.readUnsignedShort();
        this.width = in.readInt();

        if (version >= 2) {
            this.dashCount = in.readUnsignedShort();
            this.dashLength = in.readInt();
            this.dotCount = in.readUnsignedShort();
            this.dotLength = in.readInt();
            this.distance = in.readInt();

            if (version >= 3) {
                this.lineJoin = in.readUnsignedShort();
            }

            // TODO: implement version 4
        }
    }

}