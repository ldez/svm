package at.stefl.commons.math.vector;

public class Vector2i {

    public static final Vector2i NULL = new Vector2i();
    public static final Vector2i X = new Vector2i(1, 0);
    public static final Vector2i Y = new Vector2i(0, 1);

    private int x;
    private int y;

    public Vector2i() {}

    public Vector2i(final int xy) {
        this(xy, xy);
    }

    public Vector2i(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(final Vector3i xy) {
        this(xy.getX(), xy.getY());
    }

    public Vector2i(final Vector2d xy) {
        this((int) xy.getX(), (int) xy.getY());
    }

    public Vector2i(final Vector3d xyz) {
        this((int) xyz.getX(), (int) xyz.getY());
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

        if (!(obj instanceof Vector2i)) {
            return false;
        }
        final Vector2i other = (Vector2i) obj;

        return (this.x == other.x) && (this.y == other.y);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.x);
        bits += java.lang.Double.doubleToLongBits(this.y) * 37;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Vector2i getXY() {
        return this;
    }

    public Vector2i getYX() {
        return new Vector2i(this.y, this.x);
    }

    public Vector2i setX(final int x) {
        return new Vector2i(x, this.y);
    }

    public Vector2i setY(final int y) {
        return new Vector2i(this.x, y);
    }

    public Vector2i setXY(final Vector2i xy) {
        return xy;
    }

    public Vector2i setYX(final Vector2i yx) {
        return new Vector2i(yx.y, yx.x);
    }

    public Vector2b lessThan(final Vector2i b) {
        return new Vector2b(this.x < b.x, this.y < b.y);
    }

    public Vector2b lessThanOrEqual(final Vector2i b) {
        return new Vector2b(this.x <= b.x, this.y <= b.y);
    }

    public Vector2b greaterThan(final Vector2i b) {
        return new Vector2b(this.x > b.x, this.y > b.y);
    }

    public Vector2b greaterThanOrEqual(final Vector2i b) {
        return new Vector2b(this.x >= b.x, this.y >= b.y);
    }

    public Vector2b equal(final Vector2i b) {
        return new Vector2b(this.x == b.x, this.y == b.y);
    }

    public Vector2b notEqual(final Vector2i b) {
        return new Vector2b(this.x != b.x, this.y != b.y);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2i negate() {
        return new Vector2i(-this.x, -this.y);
    }

    public Vector2i abs() {
        return new Vector2i(Math.abs(this.x), Math.abs(this.y));
    }

    public Vector2i min(final Vector2i b) {
        return new Vector2i(Math.min(this.x, b.x), Math.min(this.y, b.y));
    }

    public Vector2i max(final Vector2i b) {
        return new Vector2i(Math.max(this.x, b.x), Math.max(this.y, b.y));
    }

    public Vector2i turnLeft() {
        return new Vector2i(-this.y, this.x);
    }

    public Vector2i turnRight() {
        return new Vector2i(this.y, -this.x);
    }

    public Vector2i add(final int b) {
        final Vector2i result = new Vector2i();

        result.x = this.x + b;
        result.y = this.y + b;

        return result;
    }

    public Vector2i add(final Vector2i b) {
        final Vector2i result = new Vector2i();

        result.x = this.x + b.x;
        result.y = this.y + b.y;

        return result;
    }

    public Vector2i sub(final int b) {
        final Vector2i result = new Vector2i();

        result.x = this.x - b;
        result.y = this.y - b;

        return result;
    }

    public Vector2i sub(final Vector2i b) {
        final Vector2i result = new Vector2i();

        result.x = this.x - b.x;
        result.y = this.y - b.y;

        return result;
    }

    public Vector2i mul(final int b) {
        final Vector2i result = new Vector2i();

        result.x = this.x * b;
        result.y = this.y * b;

        return result;
    }

    public Vector2i mul(final Vector2i b) {
        final Vector2i result = new Vector2i();

        result.x = this.x * b.x;
        result.y = this.y * b.y;

        return result;
    }

    // TODO: implement
    // public Vector2i mul(Matrix2i b) {
    // Vector2i result = new Vector2i();
    //
    // result.x = x * b.getM00() + y * b.getM10();
    // result.y = x * b.getM01() + y * b.getM11();
    //
    // return result;
    // }

    public Vector2i div(final int b) {
        final Vector2i result = new Vector2i();

        result.x = this.x / b;
        result.y = this.y / b;

        return result;
    }

    public Vector2i div(final Vector2i b) {
        final Vector2i result = new Vector2i();

        result.x = this.x / b.x;
        result.y = this.y / b.y;

        return result;
    }

    public double dot(final Vector2i b) {
        return this.x * b.x + this.y * b.y;
    }

    public Vector2d getAsVector2d() {
        return new Vector2d(this);
    }

}