package at.stefl.svm.tosvg;

import java.util.ArrayList;
import java.util.Collection;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.commons.math.vector.Vector2i;
import at.stefl.svm.object.Color;

public class SVGUtil {

    public static String getColorAttribute(final Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }

    // TODO: improve
    public static ArrayList<Vector2d> getPoints(final Collection<Vector2i> points) {
        final ArrayList<Vector2d> result = new ArrayList<Vector2d>(points.size());

        for (final Vector2i point : points) {
            result.add(point.getAsVector2d());
        }

        return result;
    }

    private SVGUtil() {}

}