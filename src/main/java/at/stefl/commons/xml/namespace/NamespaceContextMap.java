package at.stefl.commons.xml.namespace;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import at.stefl.commons.util.iterator.EmptyIterator;
import at.stefl.commons.util.iterator.SingleElementIterator;
import at.stefl.commons.xml.XMLConstants;

public class NamespaceContextMap implements NamespaceContext {

    private final Map<String, String> namespaceURIMap;
    private final Map<String, List<String>> prefixesMap;

    public NamespaceContextMap() {
        this.namespaceURIMap = new HashMap<String, String>();
        this.prefixesMap = new HashMap<String, List<String>>();
    }

    public NamespaceContextMap(final NamespaceContextMap contextMap) {
        this.namespaceURIMap = new HashMap<String, String>(contextMap.namespaceURIMap);
        this.prefixesMap = new HashMap<String, List<String>>(contextMap.prefixesMap);
    }

    @Override
    public String getNamespaceURI(final String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix cannot be null");
        }
        if (prefix.equals(XMLConstants.XML_NS_PREFIX)) {
            return XMLConstants.XML_NS_URI;
        }
        if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }

        final String result = this.namespaceURIMap.get(prefix);
        if (result == null) {
            return XMLConstants.NULL_NS_URI;
        }
        return result;
    }

    @Override
    public String getPrefix(final String namespaceURI) {
        final Iterator<String> prefixes = this.getPrefixes(namespaceURI);
        if (!prefixes.hasNext()) {
            return null;
        }
        return prefixes.next();
    }

    @Override
    public Iterator<String> getPrefixes(final String namespaceURI) {
        if (namespaceURI == null) {
            throw new IllegalArgumentException("namespaceURI cannot be null");
        }
        if (namespaceURI.equals(XMLConstants.XML_NS_URI)) {
            return new SingleElementIterator<String>(XMLConstants.XML_NS_PREFIX);
        }
        if (namespaceURI.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
            return new SingleElementIterator<String>(XMLConstants.XMLNS_ATTRIBUTE);
        }

        final List<String> result = this.prefixesMap.get(namespaceURI);
        if (result == null) {
            return new EmptyIterator<String>();
        }
        return result.iterator();
    }

    public void putNamespace(final String prefix, final String namespaceURI) {
        this.namespaceURIMap.put(prefix, namespaceURI);

        List<String> prefixes = this.prefixesMap.get(namespaceURI);
        if (prefixes == null) {
            prefixes = new LinkedList<String>();
            this.prefixesMap.put(namespaceURI, prefixes);
        }
        prefixes.add(prefix);
    }

    public void removeNamespaceURI(final String namespaceURI) {
        final List<String> prefixes = this.prefixesMap.remove(namespaceURI);
        if (prefixes == null) {
            return;
        }

        for (final String prefix : prefixes) {
            this.namespaceURIMap.remove(prefix);
        }
    }

    public void removePrefix(final String prefix) {
        final String namespaceURI = this.namespaceURIMap.remove(prefix);
        if (namespaceURI == null) {
            return;
        }

        final List<String> prefixes = this.prefixesMap.get(namespaceURI);
        prefixes.remove(prefix);
        if (prefixes.isEmpty()) {
            this.prefixesMap.remove(namespaceURI);
        }
    }

}