package at.stefl.commons.math.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.stefl.commons.util.collection.HashMultiset;
import at.stefl.commons.util.collection.Multiset;

public class HashUndirectedGraph<V, E extends AbstractUndirectedEdge> extends AbstractUndirectedGraph<V, E> implements ListenableGraph<V, E> {

    private final Set<V> vertices = new HashSet<V>();
    private final Multiset<E> edges = new HashMultiset<E>();

    private final List<GraphListener> listeners = new ArrayList<GraphListener>();

    public HashUndirectedGraph() {}

    public HashUndirectedGraph(final Graph<V, E> graph) {
        for (final V vertex : graph.getVertices()) {
            this.addVertex(vertex);
        }

        for (final E edge : graph.getEdges()) {
            this.addEdge(edge);
        }
    }

    @Override
    public synchronized int getVertexCount() {
        return this.vertices.size();
    }

    @Override
    public synchronized int getEdgeCount() {
        return this.edges.size();
    }

    @Override
    public synchronized int getEdgeCount(final E edge) {
        return this.edges.uniqueCount(edge);
    }

    @Override
    public synchronized Set<V> getVertices() {
        return new HashSet<V>(this.vertices);
    }

    @Override
    public synchronized Multiset<E> getEdges() {
        return new HashMultiset<E>(this.edges);
    }

    @Override
    public boolean containsVertex(final V vertex) {
        return this.vertices.contains(vertex);
    }

    @Override
    public boolean containsEdge(final E edge) {
        return this.edges.contains(edge);
    }

    @Override
    public synchronized boolean addVertex(final V vertex) {
        if (!this.vertices.add(vertex)) {
            return false;
        }
        this.fireVertexAdded(vertex);
        return true;
    }

    @Override
    public synchronized boolean addEdge(final E edge) {
        if (!this.edges.add(edge)) {
            return false;
        }
        this.fireEdgeAdded(edge);
        return true;
    }

    @Override
    public void addListener(final GraphListener listener) {
        synchronized (this.listeners) {
            this.listeners.add(listener);
        }
    }

    @Override
    public synchronized boolean removeVertex(final V vertex) {
        if (!this.vertices.remove(vertex)) {
            return false;
        }

        for (final E edge : new HashMultiset<E>(this.edges)) {
            if (edge.contains(vertex)) {
                this.removeEdge(edge);
            }
        }

        this.fireVertexRemoved(vertex);
        return true;
    }

    @Override
    public synchronized boolean removeEdge(final E edge) {
        if (!this.edges.remove(edge)) {
            return false;
        }
        this.fireEdgeRemoved(edge);
        return true;
    }

    @Override
    public void removeListener(final GraphListener listener) {
        synchronized (this.listeners) {
            this.listeners.remove(listener);
        }
    }

    private void fireVertexAdded(final V vertex) {
        synchronized (this.listeners) {
            for (final GraphListener listener : this.listeners) {
                listener.vertexAdded(vertex);
            }
        }
    }

    private void fireEdgeAdded(final E edge) {
        synchronized (this.listeners) {
            for (final GraphListener listener : this.listeners) {
                listener.edgeAdded(edge);
            }
        }
    }

    private void fireVertexRemoved(final V vertex) {
        synchronized (this.listeners) {
            for (final GraphListener listener : this.listeners) {
                listener.vertexRemoved(vertex);
            }
        }
    }

    private void fireEdgeRemoved(final E edge) {
        synchronized (this.listeners) {
            for (final GraphListener listener : this.listeners) {
                listener.edgeRemoved(edge);
            }
        }
    }

}