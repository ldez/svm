package at.stefl.commons.math.vector;

public class Vector2b {

    private boolean x;
    private boolean y;

    public Vector2b() {
        this.x = this.y = false;
    }

    public Vector2b(final boolean all) {
        this.x = this.y = all;
    }

    public Vector2b(final boolean x, final boolean y) {
        this.x = x;
        this.y = y;
    }

    public Vector2b(final boolean... components) {
        switch (components.length) {
            case 0:
                this.x = this.y = false;
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

    public Vector2b(final Vector2b xy) {
        this.x = xy.x;
        this.y = xy.y;
    }

    public Vector2b(final Vector3b xyz) {
        this.x = xyz.getX();
        this.y = xyz.getY();
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
        if (!(obj instanceof Vector2b)) {
            return false;
        }
        final Vector2b vector = (Vector2b) obj;

        return (this.x == vector.x) && (this.y == vector.y);
    }

    public boolean getX() {
        return this.x;
    }

    public boolean getY() {
        return this.y;
    }

    public Vector2b getXY() {
        return this;
    }

    public Vector2b getYX() {
        return new Vector2b(this.y, this.x);
    }

    public Vector2b setX(final boolean x) {
        return new Vector2b(x, this.y);
    }

    public Vector2b setY(final boolean y) {
        return new Vector2b(this.x, y);
    }

    public Vector2b setXY(final Vector2b xy) {
        return xy;
    }

    public Vector2b setYX(final Vector2b yx) {
        return new Vector2b(yx.y, yx.x);
    }

    public Vector2b equal(final Vector2b b) {
        final Vector2b result = new Vector2b();

        result.x = this.x == b.x;
        result.y = this.y == b.y;

        return result;
    }

    public Vector2b notEqual(final Vector2b b) {
        final Vector2b result = new Vector2b();

        result.x = this.x != b.x;
        result.y = this.y != b.y;

        return result;
    }

    public boolean any() {
        return this.x | this.y;
    }

    public boolean all() {
        return this.x & this.y;
    }

    public Vector2b not() {
        return new Vector2b(!this.x, !this.y);
    }

    public Vector2b or(final Vector2b b) {
        final Vector2b result = new Vector2b();

        result.x = this.x | b.x;
        result.y = this.y | b.y;

        return result;
    }

    public Vector2b and(final Vector2b b) {
        final Vector2b result = new Vector2b();

        result.x = this.x & b.x;
        result.y = this.y & b.y;

        return result;
    }

}