package at.stefl.commons.math.vector;

import java.awt.Point;

import at.stefl.commons.math.MathUtil;
import at.stefl.commons.math.matrix.Matrix2d;

public class Vector2d {

    public static final Vector2d NULL = new Vector2d();
    public static final Vector2d X = new Vector2d(1, 0);
    public static final Vector2d Y = new Vector2d(0, 1);

    private double x;
    private double y;

    public Vector2d() {
        this.x = this.y = 0d;
    }

    public Vector2d(final double all) {
        this.x = this.y = all;
    }

    public Vector2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(final double... components) {
        switch (components.length) {
            case 0:
                this.x = this.y = 0d;
                break;
            case 1:
                this.x = this.y = components[0];
                break;
            default:
                this.x = components[0];
                this.y = components[1];
                break;
        }
    }

    public Vector2d(final Vector3d xyz) {
        this(xyz.getX(), xyz.getY());
    }

    public Vector2d(final Vector2i xy) {
        this(xy.getX(), xy.getY());
    }

    public Vector2d(final Vector3i xyz) {
        this(xyz.getX(), xyz.getY());
    }

    public Vector2d(final Point point) {
        this(point.x, point.y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector2d)) {
            return false;
        }
        final Vector2d vector = (Vector2d) obj;

        return (this.x == vector.x) && (this.y == vector.y);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.x);
        bits += java.lang.Double.doubleToLongBits(this.y) * 37;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vector2d getXY() {
        return this;
    }

    public Vector2d getYX() {
        return new Vector2d(this.y, this.x);
    }

    public Vector2d setX(final double x) {
        return new Vector2d(x, this.y);
    }

    public Vector2d setY(final double y) {
        return new Vector2d(this.x, y);
    }

    public Vector2d setXY(final Vector2d xy) {
        return xy;
    }

    public Vector2d setYX(final Vector2d yx) {
        return new Vector2d(yx.y, yx.x);
    }

    public Vector2b lessThan(final Vector2d b) {
        return new Vector2b(this.x < b.x, this.y < b.y);
    }

    public Vector2b lessThanOrEqual(final Vector2d b) {
        return new Vector2b(this.x <= b.x, this.y <= b.y);
    }

    public Vector2b greaterThan(final Vector2d b) {
        return new Vector2b(this.x > b.x, this.y > b.y);
    }

    public Vector2b greaterThanOrEqual(final Vector2d b) {
        return new Vector2b(this.x >= b.x, this.y >= b.y);
    }

    public Vector2b equal(final Vector2d b) {
        return new Vector2b(this.x == b.x, this.y == b.y);
    }

    public Vector2b notEqual(final Vector2d b) {
        return new Vector2b(this.x != b.x, this.y != b.y);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2d negate() {
        return new Vector2d(-this.x, -this.y);
    }

    public Vector2d abs() {
        return new Vector2d(Math.abs(this.x), Math.abs(this.y));
    }

    public Vector2d min(final Vector2d b) {
        return new Vector2d(Math.min(this.x, b.x), Math.min(this.y, b.y));
    }

    public Vector2d max(final Vector2d b) {
        return new Vector2d(Math.max(this.x, b.x), Math.max(this.y, b.y));
    }

    public Vector2d turnLeft() {
        return new Vector2d(-this.y, this.x);
    }

    public Vector2d turnRight() {
        return new Vector2d(this.y, -this.x);
    }

    public Vector2d normalize() {
        final double length = this.length();
        if (length == 0d) {
            return new Vector2d();
        }
        return new Vector2d(this.x / length, this.y / length);
    }

    public Vector2d radians() {
        final Vector2d result = new Vector2d();

        result.x = this.x * MathUtil.DEG2RAD;
        result.y = this.y * MathUtil.DEG2RAD;

        return result;
    }

    public Vector2d degrees() {
        final Vector2d result = new Vector2d();

        result.x = this.x * MathUtil.RAD2DEG;
        result.y = this.y * MathUtil.RAD2DEG;

        return result;
    }

    public Vector2d sin() {
        final Vector2d result = new Vector2d();

        result.x = Math.sin(this.x);
        result.y = Math.sin(this.y);

        return result;
    }

    public Vector2d cos() {
        final Vector2d result = new Vector2d();

        result.x = Math.cos(this.x);
        result.y = Math.cos(this.y);

        return result;
    }

    public Vector2d tan() {
        final Vector2d result = new Vector2d();

        result.x = Math.tan(this.x);
        result.y = Math.tan(this.y);

        return result;
    }

    public Vector2d asin() {
        final Vector2d result = new Vector2d();

        result.x = Math.asin(this.x);
        result.y = Math.asin(this.y);

        return result;
    }

    public Vector2d acos() {
        final Vector2d result = new Vector2d();

        result.x = Math.acos(this.x);
        result.y = Math.acos(this.y);

        return result;
    }

    public Vector2d atan() {
        final Vector2d result = new Vector2d();

        result.x = Math.atan(this.x);
        result.y = Math.atan(this.y);

        return result;
    }

    public Vector2d atan2(final Vector2d b) {
        final Vector2d result = new Vector2d();

        result.x = Math.atan2(this.x, b.x);
        result.y = Math.atan2(this.y, b.y);

        return result;
    }

    public Vector2d add(final double b) {
        final Vector2d result = new Vector2d();

        result.x = this.x + b;
        result.y = this.y + b;

        return result;
    }

    public Vector2d add(final Vector2d b) {
        final Vector2d result = new Vector2d();

        result.x = this.x + b.x;
        result.y = this.y + b.y;

        return result;
    }

    public Vector2d sub(final double b) {
        final Vector2d result = new Vector2d();

        result.x = this.x - b;
        result.y = this.y - b;

        return result;
    }

    public Vector2d sub(final Vector2d b) {
        final Vector2d result = new Vector2d();

        result.x = this.x - b.x;
        result.y = this.y - b.y;

        return result;
    }

    public Vector2d mul(final double b) {
        final Vector2d result = new Vector2d();

        result.x = this.x * b;
        result.y = this.y * b;

        return result;
    }

    public Vector2d mul(final Vector2d b) {
        final Vector2d result = new Vector2d();

        result.x = this.x * b.x;
        result.y = this.y * b.y;

        return result;
    }

    public Vector2d mul(final Matrix2d b) {
        final Vector2d result = new Vector2d();

        result.x = this.x * b.getM00() + this.y * b.getM10();
        result.y = this.x * b.getM01() + this.y * b.getM11();

        return result;
    }

    public Vector2d div(final double b) {
        final Vector2d result = new Vector2d();

        result.x = this.x / b;
        result.y = this.y / b;

        return result;
    }

    public Vector2d div(final Vector2d b) {
        final Vector2d result = new Vector2d();

        result.x = this.x / b.x;
        result.y = this.y / b.y;

        return result;
    }

    public double dot(final Vector2d b) {
        return this.x * b.x + this.y * b.y;
    }

}