package at.stefl.commons.math.vector;

public class Vector3b {

    private boolean x;
    private boolean y;
    private boolean z;

    public Vector3b() {
        this.x = this.y = this.z = false;
    }

    public Vector3b(final boolean all) {
        this.x = this.y = this.z = all;
    }

    public Vector3b(final boolean x, final boolean y) {
        this.x = x;
        this.y = y;
        this.z = false;
    }

    public Vector3b(final boolean x, final boolean y, final boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3b(final boolean... components) {
        switch (components.length) {
            case 0:
                this.x = this.y = this.z = false;
                break;
            case 1:
                this.x = this.y = this.z = components[0];
                break;
            case 2:
                this.x = components[0];
                this.y = components[1];
                this.z = false;
                break;
            default:
                this.x = components[0];
                this.y = components[1];
                this.z = components[2];
                break;
        }
    }

    public Vector3b(final Vector3b xy, final boolean z) {
        this.x = xy.x;
        this.y = xy.y;
        this.z = z;
    }

    public Vector3b(final boolean x, final Vector3b yz) {
        this.x = x;
        this.y = yz.x;
        this.z = yz.y;
    }

    public Vector3b(final Vector3b xyz) {
        this.x = xyz.x;
        this.y = xyz.y;
        this.z = xyz.z;
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
        if (!(obj instanceof Vector3b)) {
            return false;
        }
        final Vector3b vector = (Vector3b) obj;

        return (this.x == vector.x) && (this.y == vector.y) && (this.z == vector.z);
    }

    public boolean getX() {
        return this.x;
    }

    public boolean getY() {
        return this.y;
    }

    public boolean getZ() {
        return this.z;
    }

    public Vector2b getXY() {
        return new Vector2b(this.x, this.y);
    }

    public Vector2b getXZ() {
        return new Vector2b(this.x, this.z);
    }

    public Vector2b getYX() {
        return new Vector2b(this.y, this.x);
    }

    public Vector2b getYZ() {
        return new Vector2b(this.y, this.z);
    }

    public Vector2b getZX() {
        return new Vector2b(this.z, this.x);
    }

    public Vector2b getZY() {
        return new Vector2b(this.z, this.y);
    }

    public Vector3b getXYZ() {
        return this;
    }

    public Vector3b getXZY() {
        return new Vector3b(this.x, this.z, this.y);
    }

    public Vector3b getYXZ() {
        return new Vector3b(this.y, this.x, this.z);
    }

    public Vector3b getYZX() {
        return new Vector3b(this.y, this.z, this.x);
    }

    public Vector3b getZXY() {
        return new Vector3b(this.z, this.x, this.y);
    }

    public Vector3b getZYX() {
        return new Vector3b(this.z, this.y, this.x);
    }

    public Vector3b setX(final boolean x) {
        return new Vector3b(x, this.y, this.z);
    }

    public Vector3b setY(final boolean y) {
        return new Vector3b(this.x, y, this.z);
    }

    public Vector3b setZ(final boolean z) {
        return new Vector3b(this.x, this.y, z);
    }

    public Vector3b setXY(final Vector3b xy) {
        return new Vector3b(xy, this.z);
    }

    public Vector3b setXZ(final Vector3b xz) {
        return new Vector3b(xz.x, this.y, xz.y);
    }

    public Vector3b setYX(final Vector3b yx) {
        return new Vector3b(yx.y, yx.x, this.z);
    }

    public Vector3b setYZ(final Vector3b yz) {
        return new Vector3b(this.x, yz);
    }

    public Vector3b setZX(final Vector3b zx) {
        return new Vector3b(zx.y, this.y, zx.x);
    }

    public Vector3b setZY(final Vector3b zy) {
        return new Vector3b(this.x, zy.y, zy.x);
    }

    public Vector3b setXYZ(final Vector3b xyz) {
        return xyz;
    }

    public Vector3b setXZY(final Vector3b xzy) {
        return new Vector3b(xzy.x, xzy.z, xzy.y);
    }

    public Vector3b setYXZ(final Vector3b yxz) {
        return new Vector3b(yxz.y, yxz.x, yxz.z);
    }

    public Vector3b setYZX(final Vector3b yzx) {
        return new Vector3b(yzx.y, yzx.z, yzx.x);
    }

    public Vector3b setZXY(final Vector3b zxy) {
        return new Vector3b(zxy.z, zxy.x, zxy.y);
    }

    public Vector3b setZYX(final Vector3b zyx) {
        return new Vector3b(zyx.z, zyx.y, zyx.x);
    }

    public Vector3b equal(final Vector3b b) {
        final Vector3b result = new Vector3b();

        result.x = this.x == b.x;
        result.y = this.y == b.y;
        result.z = this.z == b.z;

        return result;
    }

    public Vector3b notEqual(final Vector3b b) {
        final Vector3b result = new Vector3b();

        result.x = this.x != b.x;
        result.y = this.y != b.y;
        result.z = this.z != b.z;

        return result;
    }

    public boolean any() {
        return this.x | this.y | this.z;
    }

    public boolean all() {
        return this.x & this.y & this.z;
    }

    public Vector3b not() {
        return new Vector3b(!this.x, !this.y, !this.z);
    }

    public Vector3b or(final Vector3b b) {
        final Vector3b result = new Vector3b();

        result.x = this.x | b.x;
        result.y = this.y | b.y;
        result.z = this.z | b.z;

        return result;
    }

    public Vector3b and(final Vector3b b) {
        final Vector3b result = new Vector3b();

        result.x = this.x & b.x;
        result.y = this.y & b.y;
        result.z = this.z & b.z;

        return result;
    }

}