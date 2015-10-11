package at.stefl.commons.swing.graph;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import at.stefl.commons.math.graph.Edge;

// TODO: make use of Vertex2d
public abstract class GraphViewerEdge {

    private GraphViewer viewer;

    private final Edge edge;
    private final Set<GraphViewerVertex> vertices;

    public GraphViewerEdge(final Edge edge, final Set<GraphViewerVertex> vertices) {
        this.edge = edge;
        this.vertices = Collections.unmodifiableSet(new HashSet<GraphViewerVertex>(vertices));
    }

    public GraphViewer getViewer() {
        return this.viewer;
    }

    public Edge getEdge() {
        return this.edge;
    }

    public Set<GraphViewerVertex> getVertices() {
        return this.vertices;
    }

    protected void setViewer(final GraphViewer viewer) {
        this.viewer = viewer;
    }

    public abstract void paint(Graphics g);

    public abstract boolean intersects(Point p);

    protected final void fireRepaint() {
        if (this.viewer == null) {
            return;
        }
        this.viewer.repaint();
    }

}