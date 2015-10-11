package at.stefl.commons.lwxml;

public class LWXMLAttribute {

    private final String name;
    private final String value;

    public LWXMLAttribute(final String name, final String value) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            throw new NullPointerException();
        }

        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.name + "=" + this.value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LWXMLAttribute)) {
            return false;
        }
        final LWXMLAttribute other = (LWXMLAttribute) obj;

        return this.name.equals(other.name) && this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31 * this.value.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

}