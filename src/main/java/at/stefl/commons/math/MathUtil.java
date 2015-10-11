package at.stefl.commons.math;

import at.stefl.commons.math.vector.Vector2d;

public class MathUtil {

    public static final double DEG2RAD = Math.PI / 180;

    public static final double RAD2DEG = 1 / DEG2RAD;

    public static double atan2(final Vector2d vector) {
        return Math.atan2(vector.getY(), vector.getX());
    }

    public static int min(final int... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int min = Integer.MAX_VALUE;

        for (final int i : array) {
            if (i < min) {
                min = i;
            }
        }

        return min;
    }

    public static long min(final long... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        long min = Long.MAX_VALUE;

        for (final long l : array) {
            if (l < min) {
                min = l;
            }
        }

        return min;
    }

    public static float min(final float... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        float min = Float.MAX_VALUE;

        for (final float f : array) {
            if (f < min) {
                min = f;
            }
        }

        return min;
    }

    public static double min(final double... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double min = Double.MAX_VALUE;

        for (final double d : array) {
            if (d < min) {
                min = d;
            }
        }

        return min;
    }

    public static int max(final int... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int max = Integer.MAX_VALUE;

        for (final int i : array) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }

    public static long max(final long... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        long max = Long.MAX_VALUE;

        for (final long l : array) {
            if (l > max) {
                max = l;
            }
        }

        return max;
    }

    public static float max(final float... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        float max = Float.MAX_VALUE;

        for (final float f : array) {
            if (f > max) {
                max = f;
            }
        }

        return max;
    }

    public static double max(final double... array) {
        if (array.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double max = Double.MAX_VALUE;

        for (final double d : array) {
            if (d > max) {
                max = d;
            }
        }

        return max;
    }

    public static int floor(final int number, final int divisor) {
        return (number / divisor) * divisor;
    }

    private MathUtil() {}

}