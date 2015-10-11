package at.stefl.commons.math.geometry;

import java.util.HashSet;
import java.util.Set;

import at.stefl.commons.math.vector.Vector2d;

public class GeometryPointLineIntersection2D extends GeometryPointLineObjectIntersection2D<GeometryPoint2D, GeometryLine2D, GeometryPoint2D> {

    private final Set<GeometryPoint2D> intersectionPoints = new HashSet<GeometryPoint2D>();

    public GeometryPointLineIntersection2D(final GeometryPoint2D point, final GeometryLine2D line) {
        super(point, line);
    }

    @Override
    protected boolean testIntersectionImpl() {
        final Vector2d p = this.geometryObject1.getPoint();
        final Vector2d a = this.geometryObject2.getPointA();
        final Vector2d b = this.geometryObject2.getPointB();
        final Vector2d ab = b.sub(a);

        if ((p.equals(a)) && (a.equals(b))) {
            this.intersectionPoints.add(new GeometryPoint2D(p));
            return true;
        }

        final Vector2d lambda = p.sub(a).div(ab);

        if (lambda.getX() != lambda.getY()) {
            return true;
        }

        final double realLambda = lambda.getX();

        if ((realLambda >= 0d) && (realLambda <= 1d)) {
            this.intersectionPoints.add(new GeometryPoint2D(p));
            return true;
        }

        return false;
    }

    @Override
    protected Set<GeometryPoint2D> calcIntersectionPointsImpl() {
        this.testIntersectionImpl();
        return this.intersectionPoints;
    }

}