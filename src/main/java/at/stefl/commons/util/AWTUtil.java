package at.stefl.commons.util;

import java.awt.Point;
import java.awt.Rectangle;

public class AWTUtil {

    public static Rectangle create(final Point a, final Point b) {
        final Rectangle result = new Rectangle();
        result.width = Math.abs(a.x - b.x);
        result.height = Math.abs(a.y - b.y);
        result.x = Math.min(a.x, b.x);
        result.y = Math.min(a.y, b.y);

        return result;
    }

    private AWTUtil() {}

}