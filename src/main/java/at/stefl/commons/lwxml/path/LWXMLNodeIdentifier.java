package at.stefl.commons.lwxml.path;

public class LWXMLNodeIdentifier {

    private final String elementName;
    private final int index;

    public LWXMLNodeIdentifier(final String node) {
        this(node, 0);
    }

    public LWXMLNodeIdentifier(final String elementName, final int index) {
        if (elementName == null) {
            throw new NullPointerException("elementName cannot be null");
        }
        if (elementName.length() == 0) {
            throw new IllegalArgumentException("elementName cannot be empty");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index cannot be less than 0");
        }

        this.elementName = elementName;
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(this.elementName);

        if (this.index != 0) {
            builder.append("[");
            builder.append(this.index);
            builder.append("]");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LWXMLNodeIdentifier)) {
            return false;
        }
        final LWXMLNodeIdentifier other = (LWXMLNodeIdentifier) obj;

        return this.elementName.equals(other.elementName) && (this.index == other.index);
    }

    @Override
    public int hashCode() {
        return this.elementName.hashCode() + 31 * this.index;
    }

    public String getElementName() {
        return this.elementName;
    }

    public int getIndex() {
        return this.index;
    }

}