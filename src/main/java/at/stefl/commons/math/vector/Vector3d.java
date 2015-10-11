package at.stefl.commons.math.vector;

import at.stefl.commons.math.MathUtil;
import at.stefl.commons.math.matrix.Matrix3d;

public class Vector3d {

    public static final Vector3d NULL = new Vector3d();
    public static final Vector3d X = new Vector3d(1, 0, 0);
    public static final Vector3d Y = new Vector3d(0, 1, 0);
    public static final Vector3d Z = new Vector3d(0, 0, 1);

    private double x;
    private double y;
    private double z;

    public Vector3d() {}

    public Vector3d(final double all) {
        this(all, all, all);
    }

    public Vector3d(final double x, final double y) {
        this(x, y, 0);
    }

    public Vector3d(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d(final double... components) {
        switch (components.length) {
            case 0:
                this.x = this.y = this.z = 0d;
                break;
            case 1:
                this.x = this.y = this.z = components[0];
                break;
            case 2:
                this.x = components[0];
                this.y = components[1];
                this.z = 0d;
                break;
            default:
                this.x = components[0];
                this.y = components[1];
                this.z = components[2];
                break;
        }
    }

    public Vector3d(final Vector2d xy, final double z) {
        this.x = xy.getX();
        this.y = xy.getY();
        this.z = z;
    }

    public Vector3d(final double x, final Vector2d yz) {
        this.x = x;
        this.y = yz.getX();
        this.z = yz.getY();
    }

    public Vector3d(final Vector3i xyz) {
        this(xyz.getX(), xyz.getY(), xyz.getZ());
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vector3d)) {
            return false;
        }
        final Vector3d vector = (Vector3d) obj;

        return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.x);
        bits += java.lang.Double.doubleToLongBits(this.y) * 37;
        bits += java.lang.Double.doubleToLongBits(this.z) * 41;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Vector2d getXY() {
        return new Vector2d(this.x, this.y);
    }

    public Vector2d getXZ() {
        return new Vector2d(this.x, this.z);
    }

    public Vector2d getYX() {
        return new Vector2d(this.y, this.x);
    }

    public Vector2d getYZ() {
        return new Vector2d(this.y, this.z);
    }

    public Vector2d getZX() {
        return new Vector2d(this.z, this.x);
    }

    public Vector2d getZY() {
        return new Vector2d(this.z, this.y);
    }

    public Vector3d getXYZ() {
        return this;
    }

    public Vector3d getXZY() {
        return new Vector3d(this.x, this.z, this.y);
    }

    public Vector3d getYXZ() {
        return new Vector3d(this.y, this.x, this.z);
    }

    public Vector3d getYZX() {
        return new Vector3d(this.y, this.z, this.x);
    }

    public Vector3d getZXY() {
        return new Vector3d(this.z, this.x, this.y);
    }

    public Vector3d getZYX() {
        return new Vector3d(this.z, this.y, this.x);
    }

    public Vector3d setX(final double x) {
        return new Vector3d(x, this.y, this.z);
    }

    public Vector3d setY(final double y) {
        return new Vector3d(this.x, y, this.z);
    }

    public Vector3d setZ(final double z) {
        return new Vector3d(this.x, this.y, z);
    }

    public Vector3d setXY(final Vector2d xy) {
        return new Vector3d(xy, this.z);
    }

    public Vector3d setXZ(final Vector2d xz) {
        return new Vector3d(xz.getX(), this.y, xz.getY());
    }

    public Vector3d setYX(final Vector2d yx) {
        return new Vector3d(yx.getY(), yx.getX(), this.z);
    }

    public Vector3d setYZ(final Vector2d yz) {
        return new Vector3d(this.x, yz);
    }

    public Vector3d setZX(final Vector2d zx) {
        return new Vector3d(zx.getY(), this.y, zx.getX());
    }

    public Vector3d setZY(final Vector2d zy) {
        return new Vector3d(this.x, zy.getY(), zy.getX());
    }

    public Vector3d setXYZ(final Vector3d xyz) {
        return xyz;
    }

    public Vector3d setXZY(final Vector3d xzy) {
        return new Vector3d(xzy.x, xzy.z, xzy.y);
    }

    public Vector3d setYXZ(final Vector3d yxz) {
        return new Vector3d(yxz.y, yxz.x, yxz.z);
    }

    public Vector3d setYZX(final Vector3d yzx) {
        return new Vector3d(yzx.y, yzx.z, yzx.x);
    }

    public Vector3d setZXY(final Vector3d zxy) {
        return new Vector3d(zxy.z, zxy.x, zxy.y);
    }

    public Vector3d setZYX(final Vector3d zyx) {
        return new Vector3d(zyx.z, zyx.y, zyx.x);
    }

    public Vector3b lessThan(final Vector3d b) {
        return new Vector3b(this.x < b.x, this.y < b.y, this.z < b.z);
    }

    public Vector3b lessThanOrEqual(final Vector3d b) {
        return new Vector3b(this.x <= b.x, this.y <= b.y, this.z <= b.z);
    }

    public Vector3b greaterThan(final Vector3d b) {
        return new Vector3b(this.x > b.x, this.y > b.y, this.z > b.z);
    }

    public Vector3b greaterThanOrEqual(final Vector3d b) {
        return new Vector3b(this.x >= b.x, this.y >= b.y, this.z >= b.z);
    }

    public Vector3b equal(final Vector3d b) {
        return new Vector3b(this.x == b.x, this.y == b.y, this.z == b.z);
    }

    public Vector3b notEqual(final Vector3d b) {
        return new Vector3b(this.x != b.x, this.y != b.y, this.z != b.z);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3d negate() {
        return new Vector3d(-this.x, -this.y, -this.z);
    }

    public Vector3d abs() {
        return new Vector3d(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    public Vector3d min(final Vector3d b) {
        return new Vector3d(Math.min(this.x, b.x), Math.min(this.y, b.y), Math.min(this.z, b.z));
    }

    public Vector3d max(final Vector3d b) {
        return new Vector3d(Math.max(this.x, b.x), Math.max(this.y, b.y), Math.max(this.z, b.z));
    }

    public Vector3d normalize() {
        final double length = this.length();
        if (length == 0d) {
            return new Vector3d();
        }
        return new Vector3d(this.x / length, this.y / length, this.z / length);
    }

    public Vector3d radians() {
        final Vector3d result = new Vector3d();

        result.x = this.x * MathUtil.DEG2RAD;
        result.y = this.y * MathUtil.DEG2RAD;
        result.z = this.z * MathUtil.DEG2RAD;

        return result;
    }

    public Vector3d degrees() {
        final Vector3d result = new Vector3d();

        result.x = this.x * MathUtil.RAD2DEG;
        result.y = this.y * MathUtil.RAD2DEG;
        result.z = this.z * MathUtil.RAD2DEG;

        return result;
    }

    public Vector3d sin() {
        final Vector3d result = new Vector3d();

        result.x = Math.sin(this.x);
        result.y = Math.sin(this.y);
        result.z = Math.sin(this.z);

        return result;
    }

    public Vector3d cos() {
        final Vector3d result = new Vector3d();

        result.x = Math.cos(this.x);
        result.y = Math.cos(this.y);
        result.z = Math.cos(this.z);

        return result;
    }

    public Vector3d tan() {
        final Vector3d result = new Vector3d();

        result.x = Math.tan(this.x);
        result.y = Math.tan(this.y);
        result.z = Math.tan(this.z);

        return result;
    }

    public Vector3d asin() {
        final Vector3d result = new Vector3d();

        result.x = Math.asin(this.x);
        result.y = Math.asin(this.y);
        result.z = Math.asin(this.z);

        return result;
    }

    public Vector3d acos() {
        final Vector3d result = new Vector3d();

        result.x = Math.acos(this.x);
        result.y = Math.acos(this.y);
        result.z = Math.acos(this.z);

        return result;
    }

    public Vector3d atan() {
        final Vector3d result = new Vector3d();

        result.x = Math.atan(this.x);
        result.y = Math.atan(this.y);
        result.z = Math.atan(this.z);

        return result;
    }

    public Vector3d atan2(final Vector3d b) {
        final Vector3d result = new Vector3d();

        result.x = Math.atan2(this.x, b.x);
        result.y = Math.atan2(this.y, b.y);
        result.z = Math.atan2(this.z, b.z);

        return result;
    }

    public Vector3d add(final double b) {
        final Vector3d result = new Vector3d();

        result.x = this.x + b;
        result.y = this.y + b;
        result.z = this.z + b;

        return result;
    }

    public Vector3d add(final Vector3d b) {
        final Vector3d result = new Vector3d();

        result.x = this.x + b.x;
        result.y = this.y + b.y;
        result.z = this.z + b.z;

        return result;
    }

    public Vector3d sub(final double b) {
        final Vector3d result = new Vector3d();

        result.x = this.x - b;
        result.y = this.y - b;
        result.z = this.z - b;

        return result;
    }

    public Vector3d sub(final Vector3d b) {
        final Vector3d result = new Vector3d();

        result.x = this.x - b.x;
        result.y = this.y - b.y;
        result.z = this.z - b.z;

        return result;
    }

    public Vector3d mul(final double b) {
        final Vector3d result = new Vector3d();

        result.x = this.x * b;
        result.y = this.y * b;
        result.z = this.z * b;

        return result;
    }

    public Vector3d mul(final Vector3d b) {
        final Vector3d result = new Vector3d();

        result.x = this.x * b.x;
        result.y = this.y * b.y;
        result.z = this.z * b.z;

        return result;
    }

    public Vector3d mul(final Matrix3d b) {
        final Vector3d result = new Vector3d();

        result.x = this.x * b.getM00() + this.y * b.getM10() + this.z * b.getM20();
        result.y = this.x * b.getM01() + this.y * b.getM11() + this.z * b.getM21();
        result.z = this.x * b.getM02() + this.y * b.getM12() + this.z * b.getM22();

        return result;
    }

    public Vector3d div(final double b) {
        final Vector3d result = new Vector3d();

        result.x = this.x / b;
        result.y = this.y / b;
        result.z = this.z / b;

        return result;
    }

    public Vector3d div(final Vector3d b) {
        final Vector3d result = new Vector3d();

        result.x = this.x / b.x;
        result.y = this.y / b.y;
        result.z = this.z / b.z;

        return result;
    }

    public double dot(final Vector3d b) {
        return this.x * b.x + this.y * b.y + this.z * b.z;
    }

    public Vector3d cross(final Vector3d b) {
        return new Vector3d(this.y * b.z - this.z * b.y, this.z * b.x - this.x * b.z, this.x * b.y - this.y * b.x);
    }

    public Vector3i getAsVector3i() {
        return new Vector3i(this);
    }

}