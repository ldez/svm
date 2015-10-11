package at.stefl.commons.swing.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

// TODO: make use of Vertex2d
public class DefaultGraphViewerVertex extends GraphViewerVertex {

    public static final double DEFAULT_RADIUS = 15;

    private final int radius2;

    public DefaultGraphViewerVertex(final Object vertex) {
        this(vertex, DEFAULT_RADIUS);
    }

    public DefaultGraphViewerVertex(final Object vertex, final double radius) {
        super(vertex);

        this.radius2 = (int) (radius * 2);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(this.radius2, this.radius2);
    }

    @Override
    public void paint(final Graphics g) {
        final Point p = this.getPosition();
        g.setColor(Color.BLACK);
        g.fillOval(p.x, p.y, this.radius2, this.radius2);
    }

    @Override
    public boolean intersects(final Point p) {
        final double radius = (double) this.radius2 / 2;
        final double distance = this.getMiddle().distance(p);
        return distance <= radius;
    }

}