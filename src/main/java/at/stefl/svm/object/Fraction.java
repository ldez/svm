package at.stefl.svm.object;

public class Fraction {

    private int numeratior;
    private int denominator;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Fraction [numeratior=");
        builder.append(this.numeratior);
        builder.append(", denominator=");
        builder.append(this.denominator);
        builder.append("]");
        return builder.toString();
    }

    public int getNumeratior() {
        return this.numeratior;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setNumeratior(final int numeratior) {
        this.numeratior = numeratior;
    }

    public void setDenominator(final int denominator) {
        this.denominator = denominator;
    }

}