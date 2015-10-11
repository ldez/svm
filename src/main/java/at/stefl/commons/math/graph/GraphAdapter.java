package at.stefl.commons.math.graph;

public abstract class GraphAdapter implements GraphListener {

    @Override
    public void vertexAdded(final Object vertex) {}

    @Override
    public void edgeAdded(final Edge edge) {}

    @Override
    public void vertexRemoved(final Object vertex) {}

    @Override
    public void edgeRemoved(final Edge edge) {}

}