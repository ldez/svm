package at.stefl.svm.object;

public class Color {

    private final int argb;

    public Color(final int rgb) {
        this.argb = 0xff000000 | rgb;
    }

    public Color(final int red, final int green, final int blue) {
        this.argb = 0xff000000 | ((red & 0xff) << 16) | ((green & 0xff) << 8) | ((blue & 0xff) << 0);
    }

    @Override
    public String toString() {
        return "[r=" + this.getRed() + ", g=" + this.getGreen() + ", b=" + this.getBlue() + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Color)) {
            return false;
        }

        return this.argb == ((Color) obj).argb;
    }

    @Override
    public int hashCode() {
        return this.argb;
    }

    public int getARGB() {
        return this.argb;
    }

    public int getAlpha() {
        return (this.argb >> 24) & 0xff;
    }

    public int getRed() {
        return (this.argb >> 16) & 0xff;
    }

    public int getGreen() {
        return (this.argb >> 8) & 0xff;
    }

    public int getBlue() {
        return (this.argb >> 0) & 0xff;
    }

}