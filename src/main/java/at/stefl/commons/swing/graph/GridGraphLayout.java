package at.stefl.commons.swing.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class GridGraphLayout extends AbstractGraphLayout {

    public static final int DEFAULT_HGAP = 20;
    public static final int DEFAULT_VGAP = 20;

    private final int hgap = DEFAULT_HGAP;
    private final int vgap = DEFAULT_VGAP;

    public GridGraphLayout(final GraphViewer viewer) {
        super(viewer);
    }

    @Override
    public void moveVertex(final GraphViewerVertex vertex, final Point p) {
        final Point oldPosition = vertex.getPosition();
        final int dx = p.x - oldPosition.x;
        final int dy = p.y - oldPosition.y;
        final Point middle = vertex.getMiddle();
        final int mdx = oldPosition.x - middle.x;
        final int mdy = oldPosition.y - middle.y;
        middle.x += dx;
        middle.y += dy;
        final int x = ((int) Math.round((double) middle.x / this.hgap)) * this.hgap + mdx;
        final int y = ((int) Math.round((double) middle.y / this.vgap)) * this.vgap + mdy;

        super.moveVertex(vertex, new Point(x, y));
    }

    @Override
    public void paint(final Graphics g) {
        final Dimension dimension = this.getSize();

        g.setColor(Color.LIGHT_GRAY);

        for (int y = this.vgap; y < dimension.height; y += this.vgap) {
            g.drawLine(0, y, dimension.width, y);
        }

        for (int x = this.hgap; x < dimension.width; x += this.hgap) {
            g.drawLine(x, 0, x, dimension.height);
        }
    }

}