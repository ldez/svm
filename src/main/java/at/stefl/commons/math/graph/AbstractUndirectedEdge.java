package at.stefl.commons.math.graph;

import at.stefl.commons.util.collection.HashMultiset;
import at.stefl.commons.util.collection.Multiset;

public abstract class AbstractUndirectedEdge extends AbstractEdge implements UndirectedEdge {

    public static final int VERTEX_COUNT = 2;

    @Override
    public int getVertexCount() {
        return VERTEX_COUNT;
    }

    @Override
    public Multiset<? extends Object> getVertices() {
        final Multiset<Object> result = new HashMultiset<Object>(VERTEX_COUNT);

        result.add(this.getVertexA());
        result.add(this.getVertexB());

        return result;
    }

    @Override
    public boolean isLoop() {
        return this.getVertexA().equals(this.getVertexB());
    }

}