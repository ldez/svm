package at.stefl.commons.test;

import org.junit.Test;

import at.stefl.commons.math.graph.Edge;
import at.stefl.commons.math.graph.GraphAdapter;
import at.stefl.commons.math.graph.HashUndirectedGraph;
import at.stefl.commons.math.graph.SimpleUndirectedEdge;

public class HashUndirectedGraphTest {

    @Test
    public void should_testName() throws Exception {
        final HashUndirectedGraph<String, SimpleUndirectedEdge> graph = new HashUndirectedGraph<String, SimpleUndirectedEdge>();
        graph.addListener(new GraphAdapter() {

            @Override
            public void vertexAdded(final Object vertex) {
                System.out.println("a " + vertex);
            }

            @Override
            public void edgeAdded(final Edge edge) {
                System.out.println("a " + edge);
            }

            @Override
            public void vertexRemoved(final Object vertex) {
                System.out.println("r " + vertex);
            }

            @Override
            public void edgeRemoved(final Edge edge) {
                System.out.println("r " + edge);
            }
        });

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));

        System.out.println(graph);

        graph.removeVertex("b");

        System.out.println(graph);
    }

}