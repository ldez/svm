package at.stefl.commons.math;

import at.stefl.commons.math.vector.Vector2d;

public class RectangleD {

    private final double left;
    private final double top;
    private final double right;
    private final double bottom;

    public RectangleD(final double left, final double top, final double right, final double bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public RectangleD(final RectangleI r) {
        this(r.getLeft(), r.getTop(), r.getRight(), r.getBottom());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long tmp;

        tmp = Double.doubleToLongBits(this.bottom);
        result = prime * result + (int) (tmp ^ (tmp >>> 32));
        tmp = Double.doubleToLongBits(this.left);
        result = prime * result + (int) (tmp ^ (tmp >>> 32));
        tmp = Double.doubleToLongBits(this.right);
        result = prime * result + (int) (tmp ^ (tmp >>> 32));
        tmp = Double.doubleToLongBits(this.top);
        result = prime * result + (int) (tmp ^ (tmp >>> 32));

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
        final RectangleD other = (RectangleD) obj;

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

        builder.append("RectangeD [left=");
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

    public double getLeft() {
        return this.left;
    }

    public double getTop() {
        return this.top;
    }

    public double getRight() {
        return this.right;
    }

    public double getBottom() {
        return this.bottom;
    }

    public Vector2d getCenter() {
        return new Vector2d((this.left + this.right) / 2d, (this.top + this.bottom) / 2d);
    }

    public Vector2d getLeftTop() {
        return new Vector2d(this.left, this.top);
    }

    public Vector2d getRightTop() {
        return new Vector2d(this.right, this.top);
    }

    public Vector2d getRightBottom() {
        return new Vector2d(this.right, this.bottom);
    }

    public Vector2d getLeftBottom() {
        return new Vector2d(this.left, this.bottom);
    }

    public double getWidth() {
        return this.right - this.left;
    }

    public double getHeight() {
        return this.bottom - this.top;
    }

    public Vector2d getSize() {
        return new Vector2d(this.getWidth(), this.getHeight());
    }

    public RectangleI getAsRectangleI() {
        return new RectangleI(this);
    }

}