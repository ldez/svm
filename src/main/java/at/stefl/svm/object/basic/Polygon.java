package at.stefl.svm.object.basic;

import java.util.List;

import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.enumeration.PolygonFlag;

// TODO: extend MetaObject
// TODO: figure out format
public class Polygon {

    private List<Vector2i> points;
    private List<PolygonFlag> flags;

    public List<Vector2i> getPoints() {
        return this.points;
    }

    public boolean hasFlags() {
        return this.flags != null;
    }

    public List<PolygonFlag> getFlags() {
        return this.flags;
    }

    public void setPoints(final List<Vector2i> points) {
        this.points = points;
    }

    public void setFlags(final List<PolygonFlag> flags) {
        this.flags = flags;
    }

}