package at.stefl.svm.object.action;

import java.io.IOException;

import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.basic.MapMode;

public class MapModeAction extends SVMAction {

    private MapMode mapMode;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MapModeAction [mapMode=");
        builder.append(this.mapMode);
        builder.append("]");
        return builder.toString();
    }

    public MapMode getMapMode() {
        return this.mapMode;
    }

    public void setMapMode(final MapMode mapMode) {
        this.mapMode = mapMode;
    }

    @Override
    protected int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        this.mapMode.serialize(out);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.mapMode = new MapMode();
        this.mapMode.deserialize(in);
    }

}