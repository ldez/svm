package at.stefl.commons.swing.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;

import at.stefl.commons.graphics.GraphicsUtil;
import at.stefl.commons.math.graph.Edge;

// TODO: make use of Vertex2d
public class DefaultGraphViewerEdge extends GraphViewerEdge {

    public static final float DEFAULT_LINE_WIDTH = 3;

    private final float lineWidth;

    public DefaultGraphViewerEdge(final Edge edge, final Set<GraphViewerVertex> vertices) {
        this(edge, vertices, DEFAULT_LINE_WIDTH);
    }

    public DefaultGraphViewerEdge(final Edge edge, final Set<GraphViewerVertex> vertices, final float lineWidth) {
        super(edge, vertices);

        this.lineWidth = lineWidth;
    }

    @Override
    public void paint(final Graphics g) {
        final Set<? extends GraphViewerVertex> vertices = this.getVertices();
        final Point[] positions = new Point[vertices.size()];

        int i = 0;
        for (final GraphViewerVertex vertex : vertices) {
            positions[i++] = vertex.getMiddle();
        }

        final GraphicsUtil graphicsUtil = new GraphicsUtil(g);
        graphicsUtil.setColor(Color.BLACK);
        graphicsUtil.setStroke(this.lineWidth);

        if (positions.length == 2) {
            graphicsUtil.drawLine(positions[0], positions[1]);
        } else {
            final Point middle = new Point();

            for (final Point position : positions) {
                middle.x += position.x;
                middle.y += position.y;
            }

            middle.x /= positions.length;
            middle.y /= positions.length;

            for (final Point position : positions) {
                graphicsUtil.drawLine(position, middle);
            }
        }
    }

    @Override
    public boolean intersects(final Point p) {
        return false;
    }

}