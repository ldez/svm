package at.stefl.commons.test;

import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.junit.Test;

import at.stefl.commons.math.graph.HashUndirectedGraph;
import at.stefl.commons.math.graph.SimpleUndirectedEdge;
import at.stefl.commons.swing.graph.GraphViewer;
import at.stefl.commons.swing.graph.GridGraphLayout;

public class GridGraphLayoutTest {

    @Test
    public void should_testName() throws Exception {
        final HashUndirectedGraph<String, SimpleUndirectedEdge> graph = new HashUndirectedGraph<String, SimpleUndirectedEdge>();

        final GraphViewer graphViewer = new GraphViewer(graph);
        graphViewer.setGraphLayout(new GridGraphLayout(graphViewer));
        graphViewer.addRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphViewer.addRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final JScrollPane scrollPane = new JScrollPane(graphViewer);

        final JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        graphViewer.addVertexMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                System.out.println(e);
            }
        });

        graph.addVertex("a");
        graph.addVertex("b");
        graph.addEdge(new SimpleUndirectedEdge("a", "b"));
    }

}