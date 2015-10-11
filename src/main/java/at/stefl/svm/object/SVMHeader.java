package at.stefl.svm.object;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.basic.MapMode;

public class SVMHeader extends SVMVersionObject {

    private long compressionMode;
    private MapMode mapMode;
    private Vector2i size;
    private long actionCount;

    private short renderGraphicReplacements;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MetaHeader [compressionMode=");
        builder.append(this.compressionMode);
        builder.append(", mapMode=");
        builder.append(this.mapMode);
        builder.append(", size=");
        builder.append(this.size);
        builder.append(", actionCount=");
        builder.append(this.actionCount);
        builder.append(", renderGraphicReplacements=");
        builder.append(this.renderGraphicReplacements);
        builder.append("]");
        return builder.toString();
    }

    public long getCompressionMode() {
        return this.compressionMode;
    }

    public MapMode getMapMode() {
        return this.mapMode;
    }

    public Vector2i getSize() {
        return this.size;
    }

    public long getActionCount() {
        return this.actionCount;
    }

    public short getRenderGraphicReplacements() {
        return this.renderGraphicReplacements;
    }

    public void setCompressionMode(final long compressionMode) {
        PrimitiveUtil.checkUnsignedInt(compressionMode);
        this.compressionMode = compressionMode;
    }

    public void setMapMode(final MapMode mapMode) {
        this.mapMode = mapMode;
    }

    public void setSize(final Vector2i size) {
        this.size = size;
    }

    public void setActionCount(final long actionCount) {
        PrimitiveUtil.checkUnsignedInt(this.compressionMode);
        this.actionCount = actionCount;
    }

    public void setRenderGraphicReplacements(final short renderGraphicReplacements) {
        PrimitiveUtil.checkUnsignedShort(renderGraphicReplacements);
        this.renderGraphicReplacements = renderGraphicReplacements;
    }

    @Override
    public SVMHeader deserialize(final SVMDataInputStream in) throws IOException {
        return (SVMHeader) super.deserialize(in);
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedInt(this.compressionMode);
        this.mapMode.serialize(out);
        out.writeSize(this.size);
        out.writeUnsignedInt(this.actionCount);

        // version 2
        out.writeUnsignedByte(this.renderGraphicReplacements);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.compressionMode = in.readUnsignedInt();
        this.mapMode = new MapMode();
        this.mapMode.deserialize(in);
        this.size = in.readSize();
        this.actionCount = in.readUnsignedInt();

        if (version >= 2) {
            this.renderGraphicReplacements = in.readUnsignedByte();
        }
    }

}