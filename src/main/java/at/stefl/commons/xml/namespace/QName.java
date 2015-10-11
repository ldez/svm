package at.stefl.commons.xml.namespace;

import at.stefl.commons.xml.XMLConstants;

public class QName {

    private final String localPart;
    private final String prefix;
    private final String namespaceURI;

    public QName(final String localName) {
        this(localName, XMLConstants.DEFAULT_NS_PREFIX, XMLConstants.NULL_NS_URI);
    }

    public QName(final String localName, final String namespaceURI) {
        this(localName, XMLConstants.DEFAULT_NS_PREFIX, namespaceURI);
    }

    public QName(final String localPart, final String prefix, final String namespaceURI) {
        if (localPart == null) {
            throw new IllegalArgumentException("localPart cannot be null");
        }
        if (prefix == null) {
            throw new IllegalArgumentException("prefix cannot be null");
        }

        this.localPart = localPart;
        this.prefix = prefix;
        this.namespaceURI = namespaceURI;
    }

    @Override
    public int hashCode() {
        return this.localPart.hashCode() ^ this.namespaceURI.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof QName)) {
            return false;
        }
        final QName qname = (QName) obj;

        return this.localPart.equals(qname.localPart) && this.namespaceURI.equals(qname.namespaceURI);
    }

    @Override
    public String toString() {
        String result = this.localPart;

        if (!this.prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)) {
            result = this.prefix + ":" + result;
        }
        if (!this.namespaceURI.equals(XMLConstants.NULL_NS_URI)) {
            result = "{" + this.namespaceURI + "} " + result;
        }

        return result;
    }

    public String getLocalPart() {
        return this.localPart;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getNamespaceURI() {
        return this.namespaceURI;
    }

}