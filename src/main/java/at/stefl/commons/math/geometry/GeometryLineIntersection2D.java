package at.stefl.commons.math.geometry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import at.stefl.commons.math.matrix.Matrix2d;
import at.stefl.commons.math.vector.Vector2d;

public class GeometryLineIntersection2D extends GeometryLineObjectIntersection2D<GeometryLine2D, GeometryLine2D, GeometryPoint2D, GeometryLine2D> {

    private final Set<GeometryPoint2D> intersectionPoints = new HashSet<GeometryPoint2D>();
    private final Set<GeometryLine2D> intersectionLines = new HashSet<GeometryLine2D>();

    public GeometryLineIntersection2D(final GeometryLine2D line1, final GeometryLine2D line2) {
        super(line1, line2);
    }

    @Override
    protected boolean testIntersectionImpl() {
        final Vector2d a1 = this.geometryObject1.pointA;
        final Vector2d b1 = this.geometryObject1.pointB;
        final Vector2d a2 = this.geometryObject2.pointA;
        final Vector2d b2 = this.geometryObject2.pointB;
        final Vector2d ab1 = this.geometryObject1.vectorAB;
        final Vector2d ab2 = this.geometryObject2.vectorAB;

        if ((a1.equals(b1)) && (a2.equals(b2)) && (a1.equals(a2))) {
            this.intersectionPoints.add(new GeometryPoint2D(a1));
            return true;
        }

        final Vector2d coefficient1 = ab1;
        final Vector2d coefficient2 = ab2.negate();
        final Vector2d result = a2.sub(a1);

        final Matrix2d A = new Matrix2d(coefficient1, coefficient2);
        final Matrix2d A1 = new Matrix2d(result, coefficient2);
        final Matrix2d A2 = new Matrix2d(coefficient1, result);

        final double detA = A.determinant();
        final double detA1 = A1.determinant();
        final double detA2 = A2.determinant();

        if ((detA == 0d) & (detA1 == 0d) & (detA2 == 0d)) {
            this.intersectionLines.add(calcualteIntersectionLine(a1, b1, a2, b2));
            return true;
        }

        final Vector2d lambda = new Vector2d(detA1, detA2).div(detA);

        final boolean intersection = lambda.greaterThanOrEqual(new Vector2d(0d)).all() & lambda.lessThanOrEqual(new Vector2d(1d)).all();
        if (intersection) {
            this.intersectionPoints.add(new GeometryPoint2D(a1.add(ab1.mul(lambda.getX()))));
            return true;
        }

        return false;
    }

    private static GeometryLine2D calcualteIntersectionLine(final Vector2d a1, final Vector2d b1, final Vector2d a2, final Vector2d b2) {
        final ArrayList<Vector2d> linePoints = new ArrayList<Vector2d>();
        linePoints.add(a1);
        linePoints.add(b1);
        linePoints.add(a2);
        linePoints.add(b2);

        Vector2d middle = new Vector2d();
        for (final Vector2d point : linePoints) {
            middle = middle.add(point);
        }
        middle = middle.div(linePoints.size());

        for (int i = 0; i < 2; i++) {
            double maxLength = 0;
            Vector2d removePoint = null;
            for (final Vector2d point : linePoints) {
                final Vector2d distance = middle.sub(point);
                final double length = distance.length();
                if (length > maxLength) {
                    maxLength = length;
                    removePoint = point;
                }
            }
            linePoints.remove(removePoint);
        }

        return new GeometryLine2D(linePoints.get(0), linePoints.get(1));
    }

    @Override
    protected Set<GeometryPoint2D> calcIntersectionPointsImpl() {
        this.testIntersection();
        return this.intersectionPoints;
    }

    @Override
    protected Set<GeometryLine2D> calcIntersectionLinesImpl() {
        this.testIntersection();
        return this.intersectionLines;
    }

}