package at.stefl.commons.swing.graph;

import java.util.Set;

import at.stefl.commons.math.graph.Edge;

public abstract class GenericGraphViewerEdgeFactory<E extends Edge, GE extends GraphViewerEdge> implements GraphViewerEdgeFactory {

    private final Class<E> edgeClass;
    private final Class<GE> viewerEdgeClass;

    public GenericGraphViewerEdgeFactory(final Class<E> edgeClass, final Class<GE> viewerEdgeClass) {
        this.edgeClass = edgeClass;
        this.viewerEdgeClass = viewerEdgeClass;
    }

    @Override
    public final Class<E> getEdgeClass() {
        return this.edgeClass;
    }

    @Override
    public final Class<GE> getViewerEdgeClass() {
        return this.viewerEdgeClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final GE buildEdge(final Edge edge, final Set<GraphViewerVertex> vertices) {
        return this.buildEdgeGeneric((E) edge, vertices);
    }

    protected abstract GE buildEdgeGeneric(E edge, Set<GraphViewerVertex> vertices);

}