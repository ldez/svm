package at.stefl.commons.swing.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraphLayout implements GraphLayout {

    private final GraphViewer viewer;

    private final List<GraphViewerVertex> vertices = new ArrayList<GraphViewerVertex>();
    private final List<GraphViewerEdge> edges = new ArrayList<GraphViewerEdge>();

    public AbstractGraphLayout(final GraphViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension();
    }

    @Override
    public Dimension getPreferedSize() {
        Rectangle all = new Rectangle();

        synchronized (this.vertices) {
            for (final GraphViewerVertex vertex : this.vertices) {
                all = all.union(vertex.getBounds());
            }
        }

        return all.getSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected final Dimension getSize() {
        return this.viewer.getSize();
    }

    public GraphViewer getGraphViewer() {
        return this.viewer;
    }

    protected final int getVertexCount() {
        synchronized (this.vertices) {
            return this.vertices.size();
        }
    }

    protected final int getEdgeCount() {
        synchronized (this.edges) {
            return this.edges.size();
        }
    }

    protected final List<GraphViewerVertex> getVertices() {
        synchronized (this.vertices) {
            return new ArrayList<GraphViewerVertex>(this.vertices);
        }
    }

    protected final List<GraphViewerEdge> getEdges() {
        synchronized (this.edges) {
            return new ArrayList<GraphViewerEdge>(this.edges);
        }
    }

    @Override
    public final void addViewerVertex(final GraphViewerVertex vertex) {
        this.addViewerVertexImpl(vertex);

        synchronized (this.vertices) {
            this.vertices.add(vertex);
        }

        this.viewer.setPreferredSize(this.getPreferedSize());
        this.fireRevalidate();
    }

    protected void addViewerVertexImpl(final GraphViewerVertex vertex) {}

    @Override
    public final void addViewerEdge(final GraphViewerEdge edge) {
        this.addViewerEdgeImpl(edge);

        synchronized (this.edges) {
            this.edges.add(edge);
        }
    }

    protected void addViewerEdgeImpl(final GraphViewerEdge edge) {}

    @Override
    public final void removeViewerVertex(final GraphViewerVertex vertex) {
        this.removeViewerVertexImpl(vertex);

        synchronized (this.vertices) {
            this.vertices.add(vertex);
        }
    }

    protected void removeViewerVertexImpl(final GraphViewerVertex vertex) {}

    @Override
    public final void removeViewerEdge(final GraphViewerEdge edge) {
        this.removeViewerEdgeImpl(edge);

        synchronized (this.edges) {
            this.edges.add(edge);
        }
    }

    protected void removeViewerEdgeImpl(final GraphViewerEdge edge) {}

    @Override
    public void moveVertex(final GraphViewerVertex vertex, final Point p) {
        final Rectangle newBounds = vertex.getBounds();
        newBounds.setLocation(p);

        if (newBounds.x < 0) {
            newBounds.x = 0;
        }
        if (newBounds.y < 0) {
            newBounds.y = 0;
        }

        vertex.setPosition(newBounds.getLocation());
        this.viewer.setPreferredSize(this.getPreferedSize());
        this.fireRevalidate();
        this.viewer.scrollRectToVisible(vertex.getBounds());
    }

    protected Point moveVertexImpl(final GraphViewerVertex vertex, final Point p) {
        return p;
    }

    @Override
    public void paint(final Graphics g) {}

    @Override
    public void revalidate(final GraphViewerVertex vertex) {}

    protected final void fireRepaint() {
        this.viewer.repaint();
    }

    protected final void fireRevalidate() {
        this.viewer.revalidate();
    }

}