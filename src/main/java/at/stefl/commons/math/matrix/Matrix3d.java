package at.stefl.commons.math.matrix;

import at.stefl.commons.math.vector.Vector2d;
import at.stefl.commons.math.vector.Vector3d;
import at.stefl.commons.util.string.StringUtil;

public class Matrix3d {

    public static final Matrix3d IDENTITY = new Matrix3d(1);

    private double m00, m01, m02;
    private double m10, m11, m12;
    private double m20, m21, m22;

    public Matrix3d() {}

    public Matrix3d(final double diagonal) {
        this.m00 = diagonal;
        this.m11 = diagonal;
        this.m22 = diagonal;
    }

    public Matrix3d(final double m00, final double m10) {
        this.m00 = m00;
        this.m10 = m10;
    }

    public Matrix3d(final double m00, final double m10, final double m20) {
        this.m00 = m00;
        this.m10 = m10;
        this.m20 = m20;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m20 = m20;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        this.m20 = m20;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11, final double m21) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
        this.m20 = m20;
        this.m21 = m21;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11, final double m21, final double m02) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m20 = m20;
        this.m21 = m21;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11, final double m21, final double m02, final double m12) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11, final double m21, final double m02, final double m12, final double m22) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
    }

    public Matrix3d(final double... components) {
        switch (components.length) {
            case 0:
                break;
            case 1:
                this.m00 = components[0];
                this.m11 = components[0];
                this.m22 = components[0];
                break;
            case 2:
                this.m00 = components[0];
                this.m10 = components[1];
                break;
            case 3:
                this.m00 = components[0];
                this.m10 = components[1];
                this.m20 = components[2];
                break;
            case 4:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m10 = components[1];
                this.m20 = components[2];
                break;
            case 5:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m10 = components[1];
                this.m11 = components[4];
                this.m20 = components[2];
                break;
            case 6:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m10 = components[1];
                this.m11 = components[4];
                this.m20 = components[2];
                this.m21 = components[5];
                break;
            case 7:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m02 = components[6];
                this.m10 = components[1];
                this.m11 = components[4];
                this.m20 = components[2];
                this.m21 = components[5];
                break;
            case 8:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m02 = components[6];
                this.m10 = components[1];
                this.m11 = components[4];
                this.m12 = components[7];
                this.m20 = components[2];
                this.m21 = components[5];
                break;
            default:
                this.m00 = components[0];
                this.m01 = components[3];
                this.m02 = components[6];
                this.m10 = components[1];
                this.m11 = components[4];
                this.m12 = components[7];
                this.m20 = components[2];
                this.m21 = components[5];
                this.m22 = components[8];
                break;
        }
    }

    public Matrix3d(final Vector3d column0, final double m01, final double m11, final double m21, final double m02, final double m12, final double m22) {
        this.m00 = column0.getX();
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = column0.getY();
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = column0.getZ();
        this.m21 = m21;
        this.m22 = m22;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final Vector3d column1, final double m02, final double m12, final double m22) {
        this.m00 = m00;
        this.m01 = column1.getX();
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = column1.getY();
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = column1.getZ();
        this.m22 = m22;
    }

    public Matrix3d(final double m00, final double m10, final double m20, final double m01, final double m11, final double m21, final Vector3d column2) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = column2.getX();
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = column2.getY();
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = column2.getZ();
    }

    public Matrix3d(final Vector3d column0, final Vector3d column1, final double m02, final double m12, final double m22) {
        this.m00 = column0.getX();
        this.m01 = column1.getX();
        this.m02 = m02;
        this.m10 = column0.getY();
        this.m11 = column1.getY();
        this.m12 = m12;
        this.m20 = column0.getZ();
        this.m21 = column1.getZ();
        this.m22 = m22;
    }

    public Matrix3d(final Vector3d column0, final double m01, final double m11, final double m21, final Vector3d column2) {
        this.m00 = column0.getX();
        this.m01 = m01;
        this.m02 = column2.getX();
        this.m10 = column0.getY();
        this.m11 = m11;
        this.m12 = column2.getY();
        this.m20 = column0.getZ();
        this.m21 = m21;
        this.m22 = column2.getZ();
    }

    public Matrix3d(final double m00, final double m10, final double m20, final Vector3d column1, final Vector3d column2) {
        this.m00 = m00;
        this.m01 = column1.getX();
        this.m02 = column2.getX();
        this.m10 = m10;
        this.m11 = column1.getY();
        this.m12 = column2.getY();
        this.m20 = m20;
        this.m21 = column1.getZ();
        this.m22 = column2.getZ();
    }

    public Matrix3d(final Vector3d column0, final Vector3d column1, final Vector3d column2) {
        this.m00 = column0.getX();
        this.m01 = column1.getX();
        this.m02 = column2.getX();
        this.m10 = column0.getY();
        this.m11 = column1.getY();
        this.m12 = column2.getY();
        this.m20 = column0.getZ();
        this.m21 = column1.getZ();
        this.m22 = column2.getZ();
    }

    public Matrix3d(final Matrix2d matrix) {
        this.m00 = matrix.getM00();
        this.m01 = matrix.getM01();
        this.m10 = matrix.getM10();
        this.m11 = matrix.getM11();
        this.m22 = 1d;
    }

    @Override
    public String toString() {
        return "[" + this.m00 + ", " + this.m01 + ", " + this.m02 + "]" + StringUtil.NEW_LINE + "[" + this.m10 + ", " + this.m11 + ", " + this.m12 + "]" + StringUtil.NEW_LINE + "[" + this.m20 + ", " + this.m21 + ", " + this.m22 + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Matrix3d)) {
            return false;
        }
        final Matrix3d matrix = (Matrix3d) obj;

        return (this.m00 == matrix.m00) && (this.m10 == matrix.m10) && (this.m20 == matrix.m20) && (this.m01 == matrix.m01) && (this.m11 == matrix.m11) && (this.m21 == matrix.m21) && (this.m02 == matrix.m02) && (this.m12 == matrix.m12)
                && (this.m22 == matrix.m22);
    }

    @Override
    public int hashCode() {
        long bits = java.lang.Double.doubleToLongBits(this.m00);
        bits += java.lang.Double.doubleToLongBits(this.m10) * 37;
        bits += java.lang.Double.doubleToLongBits(this.m20) * 41;
        bits += java.lang.Double.doubleToLongBits(this.m01) * 43;
        bits += java.lang.Double.doubleToLongBits(this.m11) * 47;
        bits += java.lang.Double.doubleToLongBits(this.m21) * 53;
        bits += java.lang.Double.doubleToLongBits(this.m02) * 59;
        bits += java.lang.Double.doubleToLongBits(this.m12) * 61;
        bits += java.lang.Double.doubleToLongBits(this.m22) * 67;
        return (((int) bits) ^ ((int) (bits >> 32)));
    }

    public double getM00() {
        return this.m00;
    }

    public double getM10() {
        return this.m10;
    }

    public double getM20() {
        return this.m20;
    }

    public double getM01() {
        return this.m01;
    }

    public double getM11() {
        return this.m11;
    }

    public double getM21() {
        return this.m21;
    }

    public double getM02() {
        return this.m02;
    }

    public double getM12() {
        return this.m12;
    }

    public double getM22() {
        return this.m22;
    }

    public Vector3d getColumn0() {
        return new Vector3d(this.m00, this.m10, this.m20);
    }

    public Vector3d getColumn1() {
        return new Vector3d(this.m01, this.m11, this.m21);
    }

    public Vector3d getColumn2() {
        return new Vector3d(this.m02, this.m12, this.m22);
    }

    public double[] getGLTransformMatrix() {
        return new double[] { this.m00, this.m10, this.m20, 0, this.m01, this.m11, this.m21, 0, 0, 0, 1, 0, this.m02, this.m12, 0, 1 };
    }

    public Matrix3d setM00(final double m00) {
        return new Matrix3d(m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM10(final double m10) {
        return new Matrix3d(this.m00, m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM20(final double m20) {
        return new Matrix3d(this.m00, this.m10, m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM01(final double m01) {
        return new Matrix3d(this.m00, this.m10, this.m20, m01, this.m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM11(final double m11) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM21(final double m21) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, this.m11, m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setM02(final double m02) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, m02, this.m12, this.m22);
    }

    public Matrix3d setM12(final double m12) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, m12, this.m22);
    }

    public Matrix3d setM22(final double m22) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, m22);
    }

    public Matrix3d setColumn0(final Vector3d column0) {
        return new Matrix3d(column0, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
    }

    public Matrix3d setColumn1(final Vector3d column1) {
        return new Matrix3d(this.m00, this.m10, this.m20, column1, this.m02, this.m12, this.m22);
    }

    public Matrix3d setColumn2(final Vector3d column2) {
        return new Matrix3d(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, column2);
    }

    public double determinant() {
        return this.m00 * this.m11 * this.m22 + this.m01 * this.m12 * this.m20 + this.m02 * this.m10 * this.m21 - (this.m02 * this.m11 * this.m20 + this.m01 * this.m10 * this.m22 + this.m00 * this.m12 * this.m21);
    }

    public Matrix3d negate() {
        return new Matrix3d(-this.m00, -this.m10, -this.m20, -this.m01, -this.m11, -this.m21, -this.m02, -this.m12, -this.m22);
    }

    public Matrix3d transpose() {
        return new Matrix3d(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
    }

    public Matrix3d add(final double b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 + b;
        result.m01 = this.m01 + b;
        result.m02 = this.m02 + b;
        result.m10 = this.m10 + b;
        result.m11 = this.m11 + b;
        result.m12 = this.m12 + b;
        result.m20 = this.m20 + b;
        result.m21 = this.m21 + b;
        result.m22 = this.m22 + b;

        return result;
    }

    public Matrix3d add(final Matrix3d b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 + b.m00;
        result.m01 = this.m01 + b.m01;
        result.m02 = this.m02 + b.m02;
        result.m10 = this.m10 + b.m10;
        result.m11 = this.m11 + b.m11;
        result.m12 = this.m12 + b.m12;
        result.m20 = this.m20 + b.m20;
        result.m21 = this.m21 + b.m21;
        result.m22 = this.m22 + b.m22;

        return result;
    }

    public Matrix3d sub(final double b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 - b;
        result.m01 = this.m01 - b;
        result.m02 = this.m02 - b;
        result.m10 = this.m10 - b;
        result.m11 = this.m11 - b;
        result.m12 = this.m12 - b;
        result.m20 = this.m20 - b;
        result.m21 = this.m21 - b;
        result.m22 = this.m22 - b;

        return result;
    }

    public Matrix3d sub(final Matrix3d b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 - b.m00;
        result.m01 = this.m01 - b.m01;
        result.m02 = this.m02 - b.m02;
        result.m10 = this.m10 - b.m10;
        result.m11 = this.m11 - b.m11;
        result.m12 = this.m12 - b.m12;
        result.m20 = this.m20 - b.m20;
        result.m21 = this.m21 - b.m21;
        result.m22 = this.m22 - b.m22;

        return result;
    }

    public Matrix3d mul(final double b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 * b;
        result.m01 = this.m01 * b;
        result.m02 = this.m02 * b;
        result.m10 = this.m10 * b;
        result.m11 = this.m11 * b;
        result.m12 = this.m12 * b;
        result.m20 = this.m20 * b;
        result.m21 = this.m21 * b;
        result.m22 = this.m22 * b;

        return result;
    }

    public Vector2d mul(final Vector2d b) {
        final double x = this.m00 * b.getX() + this.m01 * b.getY() + this.m02;
        final double y = this.m10 * b.getX() + this.m11 * b.getY() + this.m12;

        return new Vector2d(x, y);
    }

    public Vector3d mul(final Vector3d b) {
        final double x = this.m00 * b.getX() + this.m01 * b.getY() + this.m02 * b.getZ();
        final double y = this.m10 * b.getX() + this.m11 * b.getY() + this.m12 * b.getZ();
        final double z = this.m20 * b.getX() + this.m21 * b.getY() + this.m22 * b.getZ();

        return new Vector3d(x, y, z);
    }

    public Matrix3d mul(final Matrix3d b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 * b.m00 + this.m01 * b.m10 + this.m02 * b.m20;
        result.m10 = this.m10 * b.m00 + this.m11 * b.m10 + this.m12 * b.m20;
        result.m20 = this.m20 * b.m00 + this.m21 * b.m10 + this.m22 * b.m20;
        result.m01 = this.m00 * b.m01 + this.m01 * b.m11 + this.m02 * b.m21;
        result.m11 = this.m10 * b.m01 + this.m11 * b.m11 + this.m12 * b.m21;
        result.m21 = this.m20 * b.m01 + this.m21 * b.m11 + this.m22 * b.m21;
        result.m02 = this.m00 * b.m02 + this.m01 * b.m12 + this.m02 * b.m22;
        result.m12 = this.m10 * b.m02 + this.m11 * b.m12 + this.m12 * b.m22;
        result.m22 = this.m20 * b.m02 + this.m21 * b.m12 + this.m22 * b.m22;

        return result;
    }

    public Matrix3d div(final double b) {
        final Matrix3d result = new Matrix3d();

        result.m00 = this.m00 / b;
        result.m01 = this.m01 / b;
        result.m02 = this.m02 / b;
        result.m10 = this.m10 / b;
        result.m11 = this.m11 / b;
        result.m12 = this.m12 / b;
        result.m20 = this.m20 / b;
        result.m21 = this.m21 / b;
        result.m22 = this.m22 / b;

        return result;
    }

}