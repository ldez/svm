package at.stefl.commons.swing.graph;

import java.awt.Point;

public class NullGraphLayout extends AbstractGraphLayout {

    public NullGraphLayout(final GraphViewer viewer) {
        super(viewer);
    }

    @Override
    protected void addViewerVertexImpl(final GraphViewerVertex vertex) {
        vertex.setPosition(new Point());
    }

    @Override
    protected void removeViewerVertexImpl(final GraphViewerVertex vertex) {}

}