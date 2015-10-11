package at.stefl.commons.swing.graph;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

// TODO: make use of Vertex2d
public interface GraphLayout {

    public Dimension getMinimumSize();

    public Dimension getPreferedSize();

    public Dimension getMaximumSize();

    public void addViewerVertex(GraphViewerVertex vertex);

    public void addViewerEdge(GraphViewerEdge edge);

    public void removeViewerVertex(GraphViewerVertex vertex);

    public void removeViewerEdge(GraphViewerEdge edge);

    public void moveVertex(GraphViewerVertex vertex, Point p);

    public void revalidate(GraphViewerVertex vertex);

    public void paint(Graphics g);

}