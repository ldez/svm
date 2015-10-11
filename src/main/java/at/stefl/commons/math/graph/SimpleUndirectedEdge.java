package at.stefl.commons.math.graph;

public class SimpleUndirectedEdge extends AbstractUndirectedEdge {

    private final Object vertexA;
    private final Object vertexB;

    public SimpleUndirectedEdge(final Object vertexA, final Object vertexB) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
    }

    @Override
    public Object getVertexA() {
        return this.vertexA;
    }

    @Override
    public Object getVertexB() {
        return this.vertexB;
    }

    @Override
    public boolean contains(final Object vertex) {
        return vertex.equals(this.vertexA) || vertex.equals(this.vertexB);
    }

}