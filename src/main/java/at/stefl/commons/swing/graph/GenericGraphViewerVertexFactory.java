package at.stefl.commons.swing.graph;

public abstract class GenericGraphViewerVertexFactory<V extends Object, GV extends GraphViewerVertex> implements GraphViewerVertexFactory {

    private final Class<V> vertexClass;
    private final Class<GV> viewerVertexClass;

    public GenericGraphViewerVertexFactory(final Class<V> vertexClass, final Class<GV> viewerVertexClass) {
        this.vertexClass = vertexClass;
        this.viewerVertexClass = viewerVertexClass;
    }

    @Override
    public final Class<V> getVertexClass() {
        return this.vertexClass;
    }

    @Override
    public final Class<GV> getViewerVertexClass() {
        return this.viewerVertexClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final GV buildVertex(final Object vertex) {
        return this.buildVertexGeneric((V) vertex);
    }

    protected abstract GV buildVertexGeneric(V vertex);

}