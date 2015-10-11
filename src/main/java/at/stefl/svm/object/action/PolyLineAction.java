package at.stefl.svm.object.action;

import java.io.IOException;
import java.util.List;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.basic.LineInfo;

public class PolyLineAction extends SVMAction {

    private List<Vector2i> simplePolygon;

    private LineInfo lineInfo;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PolyLineAction [simplePolygon=");
        builder.append(this.simplePolygon);
        builder.append(", lineInfo=");
        builder.append(this.lineInfo);
        builder.append("]");
        return builder.toString();
    }

    public List<Vector2i> getSimplePolygon() {
        return this.simplePolygon;
    }

    public LineInfo getLineInfo() {
        return this.lineInfo;
    }

    public void setSimplePolygon(final List<Vector2i> simplePolygon) {
        this.simplePolygon = simplePolygon;
    }

    public void setLineInfo(final LineInfo lineInfo) {
        this.lineInfo = lineInfo;
    }

    @Override
    protected int getVersion() {
        return 3;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePolygon(this.simplePolygon);

        // version 2
        this.lineInfo.serialize(out);

        // version 3
        // TODO: write polygon with flags
        out.writeBoolean(false);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.simplePolygon = in.readPolygon();

        if (version >= 2) {
            this.lineInfo = new LineInfo();
            this.lineInfo.deserialize(in);

            if (version >= 3) {
                final boolean hasFlags = in.readBoolean();
                if (hasFlags) {
                    // TODO: read polygon with flags
                }
            }
        }
    }

}