package at.stefl.commons.math.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGraph<V, E extends AbstractEdge> implements Graph<V, E> {

    @Override
    public String toString() {
        return "V = " + this.getVertices() + " E = " + this.getEdges();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AbstractGraph<?, ?>)) {
            return false;
        }
        final AbstractGraph<?, ?> graph = (AbstractGraph<?, ?>) obj;

        return this.getVertices().equals(graph.getVertices()) && this.getEdges().equals(graph.getEdges());
    }

    @Override
    public int hashCode() {
        int bits = this.getVertices().hashCode();
        bits += this.getEdges().hashCode() * 37;
        return bits;
    }

    @Override
    public int getVertexCount() {
        return this.getVertices().size();
    }

    @Override
    public int getEdgeCount() {
        return this.getEdges().size();
    }

    @Override
    public int getEdgeCount(final E edge) {
        int result = 0;

        for (final E e : this.getEdges()) {
            if (edge.equals(e)) {
                result++;
            }
        }

        return result;
    }

    @Override
    public E getEdge(final Set<V> vertices) {
        for (final E edge : this.getEdges()) {
            if (vertices.equals(edge.getVertices())) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public Set<E> getConnectedEdges(final V vertex) {
        final Collection<E> edges = this.getEdges();
        final Set<E> result = new HashSet<E>();

        for (final E edge : edges) {
            final Collection<? extends Object> connectedVerices = edge.getVertices();
            if (connectedVerices.contains(vertex)) {
                result.add(edge);
            }
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<V> getConnectedVertices(final V vertex) {
        final Set<E> connectedEdges = this.getConnectedEdges(vertex);
        final Set<V> result = new HashSet<V>();

        for (final E edge : connectedEdges) {
            final Collection<? extends Object> connectedVerices = edge.getVertices();
            result.addAll((Collection<? extends V>) connectedVerices);
        }

        result.remove(vertex);
        return result;
    }

    @Override
    public boolean containsVertex(final V vertex) {
        return this.getVertices().contains(vertex);
    }

    @Override
    public boolean containsEdge(final E edge) {
        return this.getEdges().contains(edge);
    }

    @Override
    public boolean removeAllEdges(final E edge) {
        final int edgeCount = this.getEdgeCount(edge);
        int removed = 0;

        for (int i = 0; i < edgeCount; i++) {
            if (this.removeEdge(edge)) {
                removed++;
            }
        }

        return removed > 0;
    }

}