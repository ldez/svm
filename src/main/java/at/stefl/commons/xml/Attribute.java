package at.stefl.commons.xml;

import javax.xml.namespace.QName;

public class Attribute {

    private final QName qname;
    private final String value;

    public Attribute(final QName qname, final String value) {
        this.qname = qname;
        this.value = value;
    }

    public QName getQName() {
        return this.qname;
    }

    public String getValue() {
        return this.value;
    }

}