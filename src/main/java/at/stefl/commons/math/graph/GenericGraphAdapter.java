package at.stefl.commons.math.graph;

public abstract class GenericGraphAdapter<V, E extends Edge> extends GraphAdapter {

    protected void vertexAddedGeneric(final V vertex) {}

    @Override
    @SuppressWarnings("unchecked")
    public final void vertexAdded(final Object vertex) {
        final V genericVertex = (V) vertex;
        this.vertexAddedGeneric(genericVertex);
    }

    protected void edgeAddedGeneric(final E edge) {}

    @Override
    @SuppressWarnings("unchecked")
    public final void edgeAdded(final Edge edge) {
        final E genericEdge = (E) edge;
        this.edgeAddedGeneric(genericEdge);
    }

    protected void vertexRemovedGeneric(final V vertex) {}

    @Override
    @SuppressWarnings("unchecked")
    public final void vertexRemoved(final Object vertex) {
        final V genericVertex = (V) vertex;
        this.vertexAddedGeneric(genericVertex);
    }

    protected void edgeRemovedGeneric(final E edge) {}

    @Override
    @SuppressWarnings("unchecked")
    public final void edgeRemoved(final Edge edge) {
        final E genericEdge = (E) edge;
        this.edgeRemovedGeneric(genericEdge);
    }

}