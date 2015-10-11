package at.stefl.commons.math;

import at.stefl.commons.math.vector.Vector2d;

public class VectorUtil {

    public static Vector2d normalComponent(final Vector2d direction, final Vector2d vector) {
        return vector.sub(radialComponent(direction, vector));
    }

    public static Vector2d radialComponent(final Vector2d direction, final Vector2d vector) {
        final Vector2d directionNorm = direction.normalize();
        return directionNorm.mul(directionNorm.dot(vector));
    }

}