package at.stefl.commons.swing.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

// TODO: make use of Vertex2d
public abstract class GraphViewerVertex {

    private GraphViewer viewer;

    private final Object vertex;
    private Point position = new Point();

    public GraphViewerVertex(final Object vertex) {
        this.vertex = vertex;
    }

    public GraphViewer getViewer() {
        return this.viewer;
    }

    public Object getVertex() {
        return this.vertex;
    }

    public Point getPosition() {
        return new Point(this.position);
    }

    public Point getMiddle() {
        final Rectangle bounds = this.getBounds();
        return new Point((int) bounds.getCenterX(), (int) bounds.getCenterY());
    }

    public abstract Dimension getSize();

    public Rectangle getBounds() {
        return new Rectangle(this.getPosition(), this.getSize());
    }

    protected void setViewer(final GraphViewer viewer) {
        this.viewer = viewer;
    }

    public void setPosition(final Point position) {
        this.position = new Point(position);
    }

    public abstract void paint(Graphics g);

    public boolean intersects(final Point p) {
        return this.getBounds().contains(p);
    }

    public final void revalidate() {
        if (this.viewer == null) {
            return;
        }
        this.viewer.revalidateVertex(this);
    }

    protected final void fireRepaint() {
        if (this.viewer == null) {
            return;
        }
        this.viewer.repaint();
    }

}