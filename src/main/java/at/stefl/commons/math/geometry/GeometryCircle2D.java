package at.stefl.commons.math.geometry;

import at.stefl.commons.math.matrix.Matrix3d;
import at.stefl.commons.math.vector.Vector2d;

public class GeometryCircle2D extends GeometryLineObject2D {

    private final Vector2d center;
    private final double radius;

    public GeometryCircle2D(final Vector2d center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return GeometryCircle2D.class.getCanonicalName() + "[" + this.center + ", " + this.radius + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeometryCircle2D)) {
            return false;
        }
        final GeometryCircle2D geometryCircle2D = (GeometryCircle2D) obj;

        return this.center.equals(geometryCircle2D.center) && (this.radius == geometryCircle2D.radius);
    }

    @Override
    public int hashCode() {
        final long bits = java.lang.Double.doubleToLongBits(this.radius);
        return this.center.hashCode() * (((int) bits) ^ ((int) (bits >> 32)));
    }

    public Vector2d getCenter() {
        return this.center;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public GeometryCircle2D transform(final Matrix3d transform) {
        final Vector2d center = transform.mul(this.center);

        return new GeometryCircle2D(center, this.radius);
    }

}