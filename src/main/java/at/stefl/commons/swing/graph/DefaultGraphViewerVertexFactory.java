package at.stefl.commons.swing.graph;

public class DefaultGraphViewerVertexFactory extends GenericGraphViewerVertexFactory<Object, DefaultGraphViewerVertex> {

    public DefaultGraphViewerVertexFactory() {
        super(Object.class, DefaultGraphViewerVertex.class);
    }

    @Override
    protected DefaultGraphViewerVertex buildVertexGeneric(final Object vertex) {
        return new DefaultGraphViewerVertex(vertex);
    }

}