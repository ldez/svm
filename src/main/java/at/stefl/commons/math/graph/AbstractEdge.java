package at.stefl.commons.math.graph;

public abstract class AbstractEdge implements Edge {

    @Override
    public String toString() {
        return this.getVertices().toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AbstractEdge)) {
            return false;
        }
        final AbstractEdge edge = (AbstractEdge) obj;

        return this.getVertices().equals(edge.getVertices());
    }

    @Override
    public int hashCode() {
        return this.getVertices().hashCode();
    }

    @Override
    public int getVertexCount() {
        return this.getVertices().size();
    }

    @Override
    public boolean contains(final Object vertex) {
        return this.getVertices().contains(vertex);
    }

}