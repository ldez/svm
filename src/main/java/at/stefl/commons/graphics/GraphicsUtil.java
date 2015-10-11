package at.stefl.commons.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.List;

import at.stefl.commons.math.RectangleD;
import at.stefl.commons.math.geometry.GeometryCircle2D;
import at.stefl.commons.math.geometry.GeometryLine2D;
import at.stefl.commons.math.vector.Vector2d;

public class GraphicsUtil {

    private final Graphics g;
    private final Graphics2D g2;

    public GraphicsUtil(final Graphics g) {
        this.g = g;
        this.g2 = (g instanceof Graphics2D) ? (Graphics2D) g : null;
    }

    public Color getColor() {
        return this.g.getColor();
    }

    public Stroke getStroke() {
        return this.g2.getStroke();
    }

    public void setColor(final Color color) {
        this.g.setColor(color);
    }

    public void setStroke(final Stroke stroke) {
        this.g2.setStroke(stroke);
    }

    public void setStroke(final float width) {
        final Stroke stroke = new BasicStroke(width);
        this.setStroke(stroke);
    }

    public void setPaintMode() {
        this.g.setPaintMode();
    }

    public void setXORMode(final Color color) {
        this.g.setXORMode(color);
    }

    public void fillCircle(final Point p, final int radius2) {
        this.g.fillOval(p.x, p.y, radius2, radius2);
    }

    public void fillCircle(final Point center, final double radius) {
        final int size = (int) (radius * 2);
        this.g.fillOval((int) (center.x - radius), (int) (center.y - radius), size, size);
    }

    public void fillCircle(final Vector2d p, final int radius2) {
        this.g.fillOval((int) p.getX(), (int) p.getY(), radius2, radius2);
    }

    public void fillCircle(final Vector2d center, final double radius) {
        final int size = (int) (radius * 2);
        this.g.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), size, size);
    }

    public void fillCircle(final GeometryCircle2D circle) {
        this.fillCircle(circle.getCenter(), circle.getRadius());
    }

    public void drawLine(final Point a, final Point b) {
        this.g.drawLine(a.x, a.y, b.x, b.y);
    }

    public void drawLine(final Vector2d a, final Vector2d b) {
        this.g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
    }

    public void drawLine(final GeometryLine2D line) {
        this.drawLine(line.getPointA(), line.getPointB());
    }

    public void drawLines(final List<GeometryLine2D> lines) {
        for (final GeometryLine2D line : lines) {
            this.drawLine(line);
        }
    }

    public void drawCircle(final Point p, final int radius2) {
        this.g.drawOval(p.x, p.y, radius2, radius2);
    }

    public void drawCircle(final Point center, final double radius) {
        final int size = (int) (radius * 2);
        this.g.drawOval((int) (center.getX() - radius), (int) (center.getY() - radius), size, size);
    }

    public void drawCircle(final Vector2d p, final int radius2) {
        this.g.drawOval((int) p.getX(), (int) p.getY(), radius2, radius2);
    }

    public void drawCircle(final Vector2d center, final double radius) {
        final int size = (int) (radius * 2);
        this.g.drawOval((int) (center.getX() - radius), (int) (center.getY() - radius), size, size);
    }

    public void drawCircle(final GeometryCircle2D circle) {
        this.drawCircle(circle.getCenter(), circle.getRadius());
    }

    public void drawRectangle(final Rectangle r) {
        this.g.drawRect(r.x, r.y, r.width, r.height);
    }

    public void drawRectangle(final RectangleD r) {
        this.g.drawRect((int) r.getLeft(), (int) r.getTop(), (int) r.getWidth(), (int) r.getHeight());
    }

    public void drawPolygon(final List<Vector2d> vertices) {
        final int[] xPoints = new int[vertices.size()];
        final int[] yPoints = new int[vertices.size()];
        int i = 0;

        for (final Vector2d vertex : vertices) {
            xPoints[i] = (int) vertex.getX();
            yPoints[i] = (int) vertex.getY();
            i++;
        }

        this.g.drawPolygon(xPoints, yPoints, vertices.size());
    }

    public void fillRectangle(final Rectangle r) {
        this.g.fillRect(r.x, r.y, r.width, r.height);
    }

    public void fillRectangle(final RectangleD r) {
        this.g.fillRect((int) r.getLeft(), (int) r.getTop(), (int) r.getWidth(), (int) r.getHeight());
    }

    public void fillPolygon(final List<Vector2d> vertices) {
        final int[] xPoints = new int[vertices.size()];
        final int[] yPoints = new int[vertices.size()];
        int i = 0;

        for (final Vector2d vertex : vertices) {
            xPoints[i] = (int) vertex.getX();
            yPoints[i] = (int) vertex.getY();
            i++;
        }

        this.g.fillPolygon(xPoints, yPoints, vertices.size());
    }

    public void drawString(final Point start, final String string) {
        this.g.drawString(string, start.x, start.y);
    }

    public void drawString(final Vector2d start, final String string) {
        this.g.drawString(string, (int) start.getX(), (int) start.getY());
    }

    public void drawXCenterString(final Point xCenter, final String string) {
        final FontMetrics fontMetrics = this.g.getFontMetrics();
        final Rectangle2D bounds = fontMetrics.getStringBounds(string, this.g);

        this.g.drawString(string, (int) (xCenter.x - bounds.getWidth() / 2), xCenter.y);
    }

    public void drawXCenterString(final Vector2d xCenter, final String string) {
        final FontMetrics fontMetrics = this.g.getFontMetrics();
        final Rectangle2D bounds = fontMetrics.getStringBounds(string, this.g);

        this.g.drawString(string, (int) (xCenter.getX() - bounds.getWidth() / 2), (int) xCenter.getY());
    }

}