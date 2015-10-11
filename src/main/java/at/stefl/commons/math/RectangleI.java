package at.stefl.commons.math;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.commons.math.vector.Vector2i;

public class RectangleI {

    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    public RectangleI(final int left, final int top, final int right, final int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public RectangleI(final RectangleD r) {
        this((int) r.getLeft(), (int) r.getTop(), (int) r.getRight(), (int) r.getBottom());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.bottom;
        result = prime * result + this.left;
        result = prime * result + this.right;
        result = prime * result + this.top;

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final RectangleI other = (RectangleI) obj;

        if (this.bottom != other.bottom) {
            return false;
        }
        if (this.left != other.left) {
            return false;
        }
        if (this.right != other.right) {
            return false;
        }
        if (this.top != other.top) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("RectangeI [left=");
        builder.append(this.left);
        builder.append(", top=");
        builder.append(this.top);
        builder.append(", right=");
        builder.append(this.right);
        builder.append(", bottom=");
        builder.append(this.bottom);
        builder.append("]");

        return builder.toString();
    }

    public int getLeft() {
        return this.left;
    }

    public int getTop() {
        return this.top;
    }

    public int getRight() {
        return this.right;
    }

    public int getBottom() {
        return this.bottom;
    }

    public Vector2i getCenter() {
        return new Vector2i((this.left + this.right) >> 1, (this.top + this.bottom) >> 1);
    }

    public Vector2d getCenterD() {
        return new Vector2d((this.left + this.right) / 2d, (this.top + this.bottom) / 2d);
    }

    public Vector2i getLeftTop() {
        return new Vector2i(this.left, this.top);
    }

    public Vector2i getRightTop() {
        return new Vector2i(this.right, this.top);
    }

    public Vector2i getRightBottom() {
        return new Vector2i(this.right, this.bottom);
    }

    public Vector2i getLeftBottom() {
        return new Vector2i(this.left, this.bottom);
    }

    public int getWidth() {
        return this.right - this.left;
    }

    public int getHeight() {
        return this.bottom - this.top;
    }

    public Vector2i getSize() {
        return new Vector2i(this.getWidth(), this.getHeight());
    }

    public RectangleD getAsRectangleD() {
        return new RectangleD(this);
    }

}