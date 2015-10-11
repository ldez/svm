package at.stefl.commons.math.matrix;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.commons.util.string.StringUtil;

public class Matrix2d {

    public static final Matrix2d IDENTITY = new Matrix2d(1);

    private double m00, m01;
    private double m10, m11;

    public Matrix2d() {
        this.m00 = 0d;
        this.m01 = 0d;
        this.m10 = 0d;
        this.m11 = 0d;
    }

    public Matrix2d(final double diagonal) {
        this.m00 = diagonal;
        this.m01 = 0d;
        this.m10 = 0d;
        this.m11 = diagonal;
    }

    public Matrix2d(final double m00, final double m10) {
        this.m00 = m00;
        this.m01 = 0d;
        this.m10 = m10;
        this.m11 = 0d;
    }

    public Matrix2d(final double m00, final double m10, final double m01) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = 0d;
    }

    public Matrix2d(final double m00, final double m10, final double m01, final double m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    public Matrix2d(final double... components) {
        switch (components.length) {
            case 0:
                this.m00 = 0d;
                this.m01 = 0d;
                this.m10 = 0d;
                this.m11 = 0d;
                break;
            case 1:
                this.m00 = components[0];
                this.m01 = 0d;
                this.m10 = 0d;
                this.m11 = components[0];
                break;
            case 2:
                this.m00 = components[0];
                this.m01 = 0d;
                this.m10 = components[1];
                this.m11 = 0d;
                break;
            case 3:
                this.m00 = components[0];
                this.m01 = components[2];
                this.m10 = components[1];
                this.m11 = 0d;
                break;
            default:
                this.m00 = components[0];
                this.m01 = components[2];
                this.m10 = components[1];
                this.m11 = components[3];
                break;
        }
    }

    public Matrix2d(final Vector2d column0, final double m01, final double m11) {
        this.m00 = column0.getX();
        this.m01 = m01;
        this.m10 = column0.getY();
        this.m11 = m11;
    }

    public Matrix2d(final double m00, final double m10, final Vector2d column1) {
        this.m00 = m00;
        this.m01 = column1.getX();
        this.m10 = m10;
        this.m11 = column1.getY();
    }

    public Matrix2d(final Vector2d column0, final Vector2d column1) {
        this.m00 = column0.getX();
        this.m01 = column1.getX();
        this.m10 = column0.getY();
        this.m11 = column1.getY();
    }

    public Matrix2d(final Matrix3d matrix3) {
        this.m00 = matrix3.getM00();
        this.m01 = matrix3.getM01();
        this.m10 = matrix3.getM10();
        this.m11 = matrix3.getM11();
    }

    @Override
    public String toString() {
        return "[" + this.m00 + ", " + this.m01 + "]" + StringUtil.NEW_LINE + "[" + this.m10 + ", " + this.m11 + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Matrix2d)) {
            return false;
        }
        final Matrix2d matrix = (Matrix2d) obj;

        return (this.m00 == matrix.m00) && (this.m10 == matrix.m10) && (this.m01 == matrix.m01) && (this.m11 == matrix.m11);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.m00);
        bits += java.lang.Double.doubleToLongBits(this.m10) * 37;
        bits += java.lang.Double.doubleToLongBits(this.m01) * 41;
        bits += java.lang.Double.doubleToLongBits(this.m11) * 43;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public double getM00() {
        return this.m00;
    }

    public double getM10() {
        return this.m10;
    }

    public double getM01() {
        return this.m01;
    }

    public double getM11() {
        return this.m11;
    }

    public Vector2d getColumn0() {
        return new Vector2d(this.m00, this.m10);
    }

    public Vector2d getColumn1() {
        return new Vector2d(this.m01, this.m11);
    }

    public Matrix2d setM00(final double m00) {
        return new Matrix2d(m00, this.m10, this.m01, this.m11);
    }

    public Matrix2d setM10(final double m10) {
        return new Matrix2d(this.m00, m10, this.m01, this.m11);
    }

    public Matrix2d setM01(final double m01) {
        return new Matrix2d(this.m00, this.m10, m01, this.m11);
    }

    public Matrix2d setM11(final double m11) {
        return new Matrix2d(this.m00, this.m10, this.m01, m11);
    }

    public Matrix2d setColumn0(final Vector2d column0) {
        return new Matrix2d(column0, this.m01, this.m11);
    }

    public Matrix2d setColumn1(final Vector2d column1) {
        return new Matrix2d(this.m00, this.m10, column1);
    }

    public double determinant() {
        return this.m00 * this.m11 - this.m01 * this.m10;
    }

    public Matrix2d negate() {
        return new Matrix2d(-this.m00, -this.m10, -this.m01, -this.m11);
    }

    public Matrix2d transpose() {
        return new Matrix2d(this.m00, this.m01, this.m10, this.m11);
    }

    public Matrix2d add(final double b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 + b;
        result.m01 = this.m01 + b;
        result.m10 = this.m10 + b;
        result.m11 = this.m11 + b;

        return result;
    }

    public Matrix2d add(final Matrix2d b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 + b.m00;
        result.m01 = this.m01 + b.m01;
        result.m10 = this.m10 + b.m10;
        result.m11 = this.m11 + b.m11;

        return result;
    }

    public Matrix2d sub(final double b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 - b;
        result.m01 = this.m01 - b;
        result.m10 = this.m10 - b;
        result.m11 = this.m11 - b;

        return result;
    }

    public Matrix2d sub(final Matrix2d b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 - b.m00;
        result.m01 = this.m01 - b.m01;
        result.m10 = this.m10 - b.m10;
        result.m11 = this.m11 - b.m11;

        return result;
    }

    public Matrix2d mul(final double b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 * b;
        result.m01 = this.m01 * b;
        result.m10 = this.m10 * b;
        result.m11 = this.m11 * b;

        return result;
    }

    public Vector2d mul(final Vector2d b) {
        final double x = this.m00 * b.getX() + this.m01 * b.getY();
        final double y = this.m10 * b.getX() + this.m11 * b.getY();

        return new Vector2d(x, y);
    }

    public Matrix2d mul(final Matrix2d b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 * b.m00 + this.m01 * b.m10;
        result.m10 = this.m10 * b.m00 + this.m11 * b.m10;
        result.m01 = this.m00 * b.m01 + this.m01 * b.m11;
        result.m11 = this.m10 * b.m01 + this.m11 * b.m11;

        return result;
    }

    public Matrix2d div(final double b) {
        final Matrix2d result = new Matrix2d();

        result.m00 = this.m00 / b;
        result.m01 = this.m01 / b;
        result.m10 = this.m10 / b;
        result.m11 = this.m11 / b;

        return result;
    }

    public static Matrix2d rotation(final double angle) {
        return new Matrix2d(Math.cos(angle), Math.sin(angle), -Math.sin(angle), Math.cos(angle));
    }

}