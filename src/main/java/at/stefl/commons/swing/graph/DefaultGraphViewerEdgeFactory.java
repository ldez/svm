package at.stefl.commons.swing.graph;

import java.util.Set;

import at.stefl.commons.math.graph.Edge;

public class DefaultGraphViewerEdgeFactory extends GenericGraphViewerEdgeFactory<Edge, DefaultGraphViewerEdge> {

    public DefaultGraphViewerEdgeFactory() {
        super(Edge.class, DefaultGraphViewerEdge.class);
    }

    @Override
    protected DefaultGraphViewerEdge buildEdgeGeneric(final Edge edge, final Set<GraphViewerVertex> vertices) {
        return new DefaultGraphViewerEdge(edge, vertices);
    }

}