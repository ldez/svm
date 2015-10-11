package at.stefl.commons.math.vector;

public class Vector3i {

    public static final Vector3i NULL = new Vector3i();
    public static final Vector3i X = new Vector3i(1, 0, 0);
    public static final Vector3i Y = new Vector3i(0, 1, 0);
    public static final Vector3i Z = new Vector3i(0, 0, 1);

    private int x;
    private int y;
    private int z;

    public Vector3i() {}

    public Vector3i(final int xyz) {
        this(xyz, xyz, xyz);
    }

    public Vector3i(final int x, final int y) {
        this(x, y, 0);
    }

    public Vector3i(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(final Vector2i xy, final int z) {
        this(xy.getX(), xy.getY(), z);
    }

    public Vector3i(final int x, final Vector2i yz) {
        this(x, yz.getX(), yz.getY());
    }

    public Vector3i(final Vector3d xyz) {
        this((int) xyz.getX(), (int) xyz.getY(), (int) xyz.getZ());
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
        if (!(obj instanceof Vector3i)) {
            return false;
        }
        final Vector3i vector = (Vector3i) obj;

        return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.x);
        bits += java.lang.Double.doubleToLongBits(this.y) * 37;
        bits += java.lang.Double.doubleToLongBits(this.z) * 41;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public Vector2i getXY() {
        return new Vector2i(this.x, this.y);
    }

    public Vector2i getXZ() {
        return new Vector2i(this.x, this.z);
    }

    public Vector2i getYX() {
        return new Vector2i(this.y, this.x);
    }

    public Vector2i getYZ() {
        return new Vector2i(this.y, this.z);
    }

    public Vector2i getZX() {
        return new Vector2i(this.z, this.x);
    }

    public Vector2i getZY() {
        return new Vector2i(this.z, this.y);
    }

    public Vector3i getXYZ() {
        return this;
    }

    public Vector3i getXZY() {
        return new Vector3i(this.x, this.z, this.y);
    }

    public Vector3i getYXZ() {
        return new Vector3i(this.y, this.x, this.z);
    }

    public Vector3i getYZX() {
        return new Vector3i(this.y, this.z, this.x);
    }

    public Vector3i getZXY() {
        return new Vector3i(this.z, this.x, this.y);
    }

    public Vector3i getZYX() {
        return new Vector3i(this.z, this.y, this.x);
    }

    public Vector3i setX(final int x) {
        return new Vector3i(x, this.y, this.z);
    }

    public Vector3i setY(final int y) {
        return new Vector3i(this.x, y, this.z);
    }

    public Vector3i setZ(final int z) {
        return new Vector3i(this.x, this.y, z);
    }

    public Vector3i setXY(final Vector2i xy) {
        return new Vector3i(xy, this.z);
    }

    public Vector3i setXZ(final Vector2i xz) {
        return new Vector3i(xz.getX(), this.y, xz.getY());
    }

    public Vector3i setYX(final Vector2i yx) {
        return new Vector3i(yx.getY(), yx.getX(), this.z);
    }

    public Vector3i setYZ(final Vector2i yz) {
        return new Vector3i(this.x, yz);
    }

    public Vector3i setZX(final Vector2i zx) {
        return new Vector3i(zx.getY(), this.y, zx.getX());
    }

    public Vector3i setZY(final Vector2i zy) {
        return new Vector3i(this.x, zy.getY(), zy.getX());
    }

    public Vector3i setXYZ(final Vector3i xyz) {
        return xyz;
    }

    public Vector3i setXZY(final Vector3i xzy) {
        return new Vector3i(xzy.x, xzy.z, xzy.y);
    }

    public Vector3i setYXZ(final Vector3i yxz) {
        return new Vector3i(yxz.y, yxz.x, yxz.z);
    }

    public Vector3i setYZX(final Vector3i yzx) {
        return new Vector3i(yzx.y, yzx.z, yzx.x);
    }

    public Vector3i setZXY(final Vector3i zxy) {
        return new Vector3i(zxy.z, zxy.x, zxy.y);
    }

    public Vector3i setZYX(final Vector3i zyx) {
        return new Vector3i(zyx.z, zyx.y, zyx.x);
    }

    public Vector3b lessThan(final Vector3i b) {
        return new Vector3b(this.x < b.x, this.y < b.y, this.z < b.z);
    }

    public Vector3b lessThanOrEqual(final Vector3i b) {
        return new Vector3b(this.x <= b.x, this.y <= b.y, this.z <= b.z);
    }

    public Vector3b greaterThan(final Vector3i b) {
        return new Vector3b(this.x > b.x, this.y > b.y, this.z > b.z);
    }

    public Vector3b greaterThanOrEqual(final Vector3i b) {
        return new Vector3b(this.x >= b.x, this.y >= b.y, this.z >= b.z);
    }

    public Vector3b equal(final Vector3i b) {
        return new Vector3b(this.x == b.x, this.y == b.y, this.z == b.z);
    }

    public Vector3b notEqual(final Vector3i b) {
        return new Vector3b(this.x != b.x, this.y != b.y, this.z != b.z);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3i negate() {
        return new Vector3i(-this.x, -this.y, -this.z);
    }

    public Vector3i abs() {
        return new Vector3i(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    public Vector3i min(final Vector3i b) {
        return new Vector3i(Math.min(this.x, b.x), Math.min(this.y, b.y), Math.min(this.z, b.z));
    }

    public Vector3i max(final Vector3i b) {
        return new Vector3i(Math.max(this.x, b.x), Math.max(this.y, b.y), Math.max(this.z, b.z));
    }

    public Vector3i add(final int b) {
        final Vector3i result = new Vector3i();

        result.x = this.x + b;
        result.y = this.y + b;
        result.z = this.z + b;

        return result;
    }

    public Vector3i add(final Vector3i b) {
        final Vector3i result = new Vector3i();

        result.x = this.x + b.x;
        result.y = this.y + b.y;
        result.z = this.z + b.z;

        return result;
    }

    public Vector3i sub(final int b) {
        final Vector3i result = new Vector3i();

        result.x = this.x - b;
        result.y = this.y - b;
        result.z = this.z - b;

        return result;
    }

    public Vector3i sub(final Vector3i b) {
        final Vector3i result = new Vector3i();

        result.x = this.x - b.x;
        result.y = this.y - b.y;
        result.z = this.z - b.z;

        return result;
    }

    public Vector3i mul(final int b) {
        final Vector3i result = new Vector3i();

        result.x = this.x * b;
        result.y = this.y * b;
        result.z = this.z * b;

        return result;
    }

    public Vector3i mul(final Vector3i b) {
        final Vector3i result = new Vector3i();

        result.x = this.x * b.x;
        result.y = this.y * b.y;
        result.z = this.z * b.z;

        return result;
    }

    // TODO: implement
    // public Vector3i mul(Matrix3i b) {
    // Vector3i result = new Vector3i();
    //
    // result.x = x * b.getM00() + y * b.getM10() + z * b.getM20();
    // result.y = x * b.getM01() + y * b.getM11() + z * b.getM21();
    // result.z = x * b.getM02() + y * b.getM12() + z * b.getM22();
    //
    // return result;
    // }

    public Vector3i div(final int b) {
        final Vector3i result = new Vector3i();

        result.x = this.x / b;
        result.y = this.y / b;
        result.z = this.z / b;

        return result;
    }

    public Vector3i div(final Vector3i b) {
        final Vector3i result = new Vector3i();

        result.x = this.x / b.x;
        result.y = this.y / b.y;
        result.z = this.z / b.z;

        return result;
    }

    public double dot(final Vector3i b) {
        return this.x * b.x + this.y * b.y + this.z * b.z;
    }

    public Vector3i cross(final Vector3i b) {
        return new Vector3i(this.y * b.z - this.z * b.y, this.z * b.x - this.x * b.z, this.x * b.y - this.y * b.x);
    }

    public Vector3d getAsVector3d() {
        return new Vector3d(this);
    }

}