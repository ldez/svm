package at.stefl.svm.object.action;

import java.io.IOException;
import java.util.List;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;

public class PolyPolygonAction extends SVMAction {

    private List<List<Vector2i>> simplePolyPolygon;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PolyPolygonAction [simplePolyPolygon=");
        builder.append(this.simplePolyPolygon);
        builder.append("]");
        return builder.toString();
    }

    public List<List<Vector2i>> getSimplePolyPolygon() {
        return this.simplePolyPolygon;
    }

    public void setSimplePolyPolygon(final List<List<Vector2i>> simplePolyPolygon) {
        this.simplePolyPolygon = simplePolyPolygon;
    }

    @Override
    protected int getVersion() {
        return 2;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writePolyPolygon(this.simplePolyPolygon);

        // version 2
        // TODO: write complex polygons
        out.writeUnsignedShort(0);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.simplePolyPolygon = in.readPolyPolygon();

        if (version >= 2) {
            final int complexPolygons = in.readUnsignedShort();
            for (int i = 0; i < complexPolygons; i++) {
                // TODO: read complex polygon
            }
        }
    }
}