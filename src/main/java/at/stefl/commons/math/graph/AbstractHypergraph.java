package at.stefl.commons.math.graph;

public abstract class AbstractHypergraph<V, E extends AbstractHyperedge> extends AbstractGraph<V, E> implements Hypergraph<V, E> {

    @Override
    public boolean removeAllEdges(final E edge) {
        return this.removeEdge(edge);
    }

}