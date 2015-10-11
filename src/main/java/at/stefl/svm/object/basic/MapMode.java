package at.stefl.svm.object.basic;

import java.io.IOException;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.commons.util.PrimitiveUtil;
import at.stefl.svm.io.SVMDataInputStream;
import at.stefl.svm.io.SVMDataOutputStream;
import at.stefl.svm.object.Fraction;
import at.stefl.svm.object.SVMVersionObject;

public class MapMode extends SVMVersionObject {

    private int unit;
    private Vector2i origin;
    private Fraction scaleX;
    private Fraction scaleY;
    private boolean simple;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MapMode [unit=");
        builder.append(this.unit);
        builder.append(", origin=");
        builder.append(this.origin);
        builder.append(", scaleX=");
        builder.append(this.scaleX);
        builder.append(", scaleY=");
        builder.append(this.scaleY);
        builder.append(", isSimple=");
        builder.append(this.simple);
        builder.append("]");
        return builder.toString();
    }

    public int getUnit() {
        return this.unit;
    }

    public Vector2i getOrigin() {
        return this.origin;
    }

    public Fraction getScaleX() {
        return this.scaleX;
    }

    public Fraction getScaleY() {
        return this.scaleY;
    }

    public boolean isSimple() {
        return this.simple;
    }

    public void setUnit(final int unit) {
        PrimitiveUtil.checkUnsignedShort(unit);
        this.unit = unit;
    }

    public void setOrigin(final Vector2i origin) {
        this.origin = origin;
    }

    public void setScaleX(final Fraction scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(final Fraction scaleY) {
        this.scaleY = scaleY;
    }

    public void setSimple(final boolean isSimple) {
        this.simple = isSimple;
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    protected void serializeContent(final SVMDataOutputStream out) throws IOException {
        out.writeUnsignedShort(this.unit);
        out.writePoint(this.origin);
        out.writeFraction(this.scaleX);
        out.writeFraction(this.scaleY);
        out.writeBoolean(this.simple);
    }

    @Override
    protected void deserializeContent(final SVMDataInputStream in, final int version, final long length) throws IOException {
        this.unit = in.readUnsignedShort();
        this.origin = in.readPoint();
        this.scaleX = in.readFraction();
        this.scaleY = in.readFraction();
        this.simple = in.readBoolean();
    }

}