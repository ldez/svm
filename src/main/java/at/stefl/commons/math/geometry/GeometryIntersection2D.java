package at.stefl.commons.math.geometry;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class GeometryIntersection2D<G1 extends GeometryObject2D, G2 extends GeometryObject2D, P extends GeometryObject2D, L extends GeometryLineObject2D, A extends GeometryAreaObject2D> {

    protected final G1 geometryObject1;
    protected final G2 geometryObject2;

    private boolean intersectionTested;
    private boolean intersection;

    private Set<P> intersectionPoints;
    private Set<L> intersectionLines;
    private Set<A> intersectionAreas;

    public GeometryIntersection2D(final G1 geometryObject1, final G2 geometryObject2) {
        this.geometryObject1 = geometryObject1;
        this.geometryObject2 = geometryObject2;
    }

    public G1 getGeometryObject1() {
        return this.geometryObject1;
    }

    public G2 getGeometryObject2() {
        return this.geometryObject2;
    }

    protected abstract boolean testIntersectionImpl();

    public final boolean testIntersection() {
        if (!this.intersectionTested) {
            this.intersection = this.testIntersectionImpl();
        }

        if (!this.intersection) {
            this.intersectionPoints = Collections.unmodifiableSet(new HashSet<P>());
            this.intersectionLines = Collections.unmodifiableSet(new HashSet<L>());
            this.intersectionAreas = Collections.unmodifiableSet(new HashSet<A>());
        }

        return this.intersection;
    }

    protected abstract Set<P> calcIntersectionPointsImpl();

    public final Set<P> calcIntersectionPoints() {
        if (this.intersectionPoints == null) {
            this.intersectionPoints = this.calcIntersectionPointsImpl();
            this.intersectionPoints = Collections.unmodifiableSet(this.intersectionPoints);
        }

        return this.intersectionPoints;
    }

    protected abstract Set<L> calcIntersectionLinesImpl();

    public final Set<L> calcIntersectionLines() {
        if (this.intersectionLines == null) {
            this.intersectionLines = this.calcIntersectionLinesImpl();
            this.intersectionLines = Collections.unmodifiableSet(this.intersectionLines);
        }

        return this.intersectionLines;
    }

    protected abstract Set<A> calcIntersectionAreasImpl();

    public final Set<A> calcIntersectionAreas() {
        if (this.intersectionAreas == null) {
            this.intersectionAreas = this.calcIntersectionAreasImpl();
            this.intersectionAreas = Collections.unmodifiableSet(this.intersectionAreas);
        }

        return this.intersectionAreas;
    }

}