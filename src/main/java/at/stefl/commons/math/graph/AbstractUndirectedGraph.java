package at.stefl.commons.math.graph;

import java.util.Set;

public abstract class AbstractUndirectedGraph<V, E extends AbstractUndirectedEdge> extends AbstractGraph<V, E> implements UndirectedGraph<V, E> {

    @Override
    public int getVertexDegree(final V vertex) {
        final Set<E> edges = this.getConnectedEdges(vertex);
        int result = 0;

        for (final E edge : edges) {
            if (edge.isLoop()) {
                result += 2;
            } else {
                result++;
            }
        }

        return result;
    }

}