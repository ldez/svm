package at.stefl.commons.xml.namespace;

import java.util.Iterator;
import java.util.LinkedList;

// TODO: improve
public class NamespaceContextStack implements NamespaceContext {

    // removed Deque because of Android 1.6
    // private Deque<NamespaceContextMap> contextStack = new
    // LinkedList<NamespaceContextMap>();
    private final LinkedList<NamespaceContextMap> contextStack = new LinkedList<NamespaceContextMap>();

    public NamespaceContextStack() {
        this.push();
    }

    private NamespaceContextMap peek() {
        return this.contextStack.getFirst();
    }

    @Override
    public String getNamespaceURI(final String prefix) {
        return this.peek().getNamespaceURI(prefix);
    }

    @Override
    public String getPrefix(final String namespaceURI) {
        return this.peek().getPrefix(namespaceURI);
    }

    @Override
    public Iterator<String> getPrefixes(final String namespaceURI) {
        return this.peek().getPrefixes(namespaceURI);
    }

    public void putNamespace(final String prefix, final String namespaceURI) {
        this.peek().putNamespace(prefix, namespaceURI);
    }

    public void removeNamespaceURI(final String namespaceURI) {
        this.peek().removeNamespaceURI(namespaceURI);
    }

    public void removePrefix(final String prefix) {
        this.peek().removePrefix(prefix);
    }

    public void push() {
        this.contextStack.addFirst(new NamespaceContextMap(this.peek()));
    }

    public void pop() {
        if (this.contextStack.size() == 1) {
            throw new IllegalStateException("removing root is illegal");
        }

        this.contextStack.removeFirst();
    }

}