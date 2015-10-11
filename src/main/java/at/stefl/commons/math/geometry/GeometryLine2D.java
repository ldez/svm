package at.stefl.commons.math.geometry;

import at.stefl.commons.math.matrix.Matrix3d;
import at.stefl.commons.math.vector.Vector2d;

public class GeometryLine2D extends GeometryLineObject2D {

    public final Vector2d pointA;
    public final Vector2d pointB;
    public final Vector2d vectorAB;
    public final Vector2d normal;

    public GeometryLine2D(final Vector2d pointA, final Vector2d pointB) {
        this.pointA = pointA;
        this.pointB = pointB;

        this.vectorAB = pointB.sub(pointA);
        this.normal = this.vectorAB.turnLeft().normalize();
    }

    @Override
    public String toString() {
        return GeometryLine2D.class.getCanonicalName() + "[" + this.pointA + ", " + this.pointB + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeometryLine2D)) {
            return false;
        }
        final GeometryLine2D geometryLine2D = (GeometryLine2D) obj;

        return (this.pointA.equals(geometryLine2D.pointA) && this.pointB.equals(geometryLine2D.pointB)) || (this.pointA.equals(geometryLine2D.pointB) && this.pointB.equals(geometryLine2D.pointA));
    }

    @Override
    public int hashCode() {
        return this.pointA.hashCode() * this.pointB.hashCode();
    }

    public Vector2d getPointA() {
        return this.pointA;
    }

    public Vector2d getPointB() {
        return this.pointB;
    }

    public Vector2d getVectorAB() {
        return this.vectorAB;
    }

    public Vector2d getVectorBA() {
        return this.vectorAB.negate();
    }

    @Override
    public GeometryLine2D transform(final Matrix3d transform) {
        final Vector2d pointA = transform.mul(this.pointA);
        final Vector2d pointB = transform.mul(this.pointB);

        return new GeometryLine2D(pointA, pointB);
    }

}