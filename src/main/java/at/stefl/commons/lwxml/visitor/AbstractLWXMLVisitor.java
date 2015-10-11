package at.stefl.commons.lwxml.visitor;

public abstract class AbstractLWXMLVisitor implements LWXMLVisitor {

    private LWXMLHost currentHost;

    public synchronized LWXMLHost getCurrentHost() {
        return this.currentHost;
    }

    @Override
    public synchronized void visitHost(final LWXMLHost host) {
        if (host != this.currentHost) {
            throw new IllegalArgumentException();
        }
        this.currentHost = host;
    }

    @Override
    public void visitEnd() {
        this.stopVisiting();
    }

    @Override
    public void visitProcessingInstruction(final String target, final String data) {}

    @Override
    public void visitComment(final String text) {}

    @Override
    public void visitStartElement(final String name) {}

    @Override
    public void visitEndAttributeList() {}

    @Override
    public void visitEndElement(final String name) {}

    @Override
    public void visitAttribute(final String name, final String value) {}

    @Override
    public void visitCharacters(final String text) {}

    @Override
    public void visitCDATA(final String text) {}

    public synchronized void stopVisiting() {
        this.currentHost = null;
    }

}