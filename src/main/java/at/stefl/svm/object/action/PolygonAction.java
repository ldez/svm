package at.stefl.svm.object.action;

import java.io.IOException;
import java.util.List;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class PolygonAction extends SVMAction {

    private List<Vector2i> simplePolygon;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PolygonAction [simplePolygon=");
        builder.append(this.simplePolygon);
        builder.append("]");
        return builder.toString();
    }

    public List<Vector2i> getSimplePolygon() {
        return this.simplePolygon;
    }

    public void setSimplePolygon(final List<Vector2i> simplePolygon) {
        this.simplePolygon = simplePolygon;
    }

    @Override
    protected int getVersion() {
        return 2;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePolygon(this.simplePolygon);

        // version 2
        // TODO: write polygon with flags
        out.writeBoolean(false);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.simplePolygon = in.readPolygon();

        if (version >= 2) {
            final boolean hasFlags = in.readBoolean();
            if (hasFlags) {
                // TODO: read polygon with flags
            }
        }
    }

}