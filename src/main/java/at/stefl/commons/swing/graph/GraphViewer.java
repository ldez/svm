package at.stefl.commons.swing.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;

import at.stefl.commons.graphics.GraphicsUtil;
import at.stefl.commons.math.graph.Edge;
import at.stefl.commons.math.graph.Graph;
import at.stefl.commons.math.graph.GraphAdapter;
import at.stefl.commons.math.graph.ListenableGraph;

// TODO: make use of Vertex2d
public class GraphViewer extends JComponent {

    private static final long serialVersionUID = -3851523788422302409L;

    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_SINGLE = 1;
    public static final int SELECTION_MODE_MULTI = 2;

    private class UpdateHandler extends GraphAdapter {

        @Override
        public void vertexAdded(final Object vertex) {
            GraphViewer.this.addVertex(vertex);
        }

        @Override
        public void edgeAdded(final Edge edge) {
            GraphViewer.this.addEdge(edge);
        }

        @Override
        public void vertexRemoved(final Object vertex) {
            GraphViewer.this.removeVertex(vertex);
        }

        @Override
        public void edgeRemoved(final Edge edge) {
            GraphViewer.this.removeEdge(edge);
        }
    }

    public class MouseHandler extends MouseAdapter {

        private GraphViewerVertex movingVertex;
        private int movingDx;
        private int movingDy;

        @Override
        public void mousePressed(final MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1) {
                return;
            }

            synchronized (GraphViewer.this.vertexMap) {
                for (final GraphViewerVertex vertex : GraphViewer.this.vertexMap.values()) {
                    if (vertex.intersects(e.getPoint())) {
                        this.movingVertex = vertex;
                        this.movingDx = vertex.getPosition().x - e.getPoint().x;
                        this.movingDy = vertex.getPosition().y - e.getPoint().y;

                        break;
                    }
                }
            }
        }

        @Override
        public void mouseDragged(final MouseEvent e) {
            if (this.movingVertex == null) {
                return;
            }

            final Point point = new Point(e.getPoint().x + this.movingDx, e.getPoint().y + this.movingDy);
            GraphViewer.this.layout.moveVertex(this.movingVertex, point);
            GraphViewer.this.repaint();
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1) {
                return;
            }

            this.movingVertex = null;
        }
    }

    private class MouseListenerHandler extends MouseAdapter {

        @Override
        public void mousePressed(final MouseEvent e) {
            GraphViewer.this.handleMouseEvent(e);
        }

        @Override
        public void mouseClicked(final MouseEvent e) {
            GraphViewer.this.handleMouseEvent(e);
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            GraphViewer.this.handleMouseEvent(e);
        }
    }

    private Graph<? extends Object, ? extends Edge> model;
    private UpdateHandler updateHandler;

    private final Map<Object, GraphViewerVertex> vertexMap = new HashMap<Object, GraphViewerVertex>();
    private final Map<Edge, GraphViewerEdge> edgeMap = new HashMap<Edge, GraphViewerEdge>();

    private final Set<GraphViewerVertexFactory> vertexFactories = new HashSet<GraphViewerVertexFactory>();
    private final Set<GraphViewerEdgeFactory> edgeFactories = new HashSet<GraphViewerEdgeFactory>();

    private GraphLayout layout = new NullGraphLayout(this);

    private final List<MouseListener> vertexMouseListeners = new ArrayList<MouseListener>();
    private final List<MouseListener> edgeMouseListeners = new ArrayList<MouseListener>();

    private int selectionMode;
    private Rectangle selectionRectangle;
    private final Set<GraphViewerVertex> selectedVertices = new HashSet<GraphViewerVertex>();

    private Map<Object, Object> renderingHints = new HashMap<Object, Object>();

    public GraphViewer() {
        this.addVertexFactory(new DefaultGraphViewerVertexFactory());
        this.addEdgeFactory(new DefaultGraphViewerEdgeFactory());

        final MouseHandler moveHandler = new MouseHandler();
        this.addMouseListener(moveHandler);
        this.addMouseMotionListener(moveHandler);

        final MouseListenerHandler listenerHandler = new MouseListenerHandler();
        this.addMouseListener(listenerHandler);

        this.setSelectionMode(SELECTION_MODE_MULTI);
    }

    public GraphViewer(final Graph<? extends Object, ? extends Edge> graph) {
        this();

        this.setModel(graph);
    }

    public void setModel(final Graph<? extends Object, ? extends Edge> graph) {
        if (this.model != null) {
            if (this.updateHandler != null) {
                ((ListenableGraph<?, ?>) this.model).addListener(this.updateHandler);
                this.updateHandler = null;
            }

            synchronized (this.vertexMap) {
                this.vertexMap.clear();
            }

            synchronized (this.edgeMap) {
                this.edgeMap.clear();
            }
        }

        if (graph instanceof ListenableGraph<?, ?>) {
            this.updateHandler = new UpdateHandler();
            ((ListenableGraph<?, ?>) graph).addListener(new UpdateHandler());
        }

        for (final Object vertex : graph.getVertices()) {
            this.addVertex(vertex);
        }

        for (final Edge edge : graph.getEdges()) {
            this.addEdge(edge);
        }

        this.model = graph;
    }

    public void setGraphLayout(final GraphLayout layout) {
        if (this.layout == layout) {
            return;
        }

        synchronized (this.vertexMap) {
            synchronized (this.edgeMap) {
                for (final GraphViewerVertex vertex : this.vertexMap.values()) {
                    this.layout.addViewerVertex(vertex);
                }

                for (final GraphViewerEdge edge : this.edgeMap.values()) {
                    this.layout.addViewerEdge(edge);
                }

                this.layout = layout;

                for (final GraphViewerVertex vertex : this.vertexMap.values()) {
                    layout.addViewerVertex(vertex);
                }

                for (final GraphViewerEdge edge : this.edgeMap.values()) {
                    layout.addViewerEdge(edge);
                }
            }
        }
    }

    public void setRenderingHints(final Map<?, ?> renderingHints) {
        synchronized (renderingHints) {
            this.renderingHints = new HashMap<Object, Object>(renderingHints);
        }
    }

    public void setSelectionMode(final int selectionMode) {
        if ((selectionMode < SELECTION_MODE_NONE) || (selectionMode > SELECTION_MODE_MULTI)) {
            throw new IllegalArgumentException("The value out of range!");
        }

        this.selectionMode = selectionMode;
    }

    public Graph<? extends Object, ? extends Edge> getModel() {
        return this.model;
    }

    public Map<?, ?> getRenderingHints() {
        synchronized (this.renderingHints) {
            return new HashMap<Object, Object>(this.renderingHints);
        }
    }

    public int getSelectionMode() {
        return this.selectionMode;
    }

    public Object getSelectedVertex() {
        synchronized (this.selectedVertices) {
            if (this.selectedVertices.isEmpty() || (this.selectedVertices.size() > 1)) {
                return null;
            }
            return this.selectedVertices.iterator().next().getVertex();
        }
    }

    public Set<Object> getSelectedVertices() {
        final Set<Object> result = new HashSet<Object>();

        synchronized (this.selectedVertices) {
            for (final GraphViewerVertex vertex : this.selectedVertices) {
                result.add(vertex.getVertex());
            }
        }

        return result;
    }

    public Collection<GraphViewerVertex> getGraphViewerVertices() {
        synchronized (this.vertexMap) {
            return this.vertexMap.values();
        }
    }

    public Collection<GraphViewerEdge> getGraphViewerEdges() {
        synchronized (this.edgeMap) {
            return this.edgeMap.values();
        }
    }

    public GraphViewerVertex getGraphViewerVertex(final Object vertex) {
        synchronized (this.vertexMap) {
            return this.vertexMap.get(vertex);
        }
    }

    public GraphViewerEdge getGraphViewerEdge(final Edge edge) {
        synchronized (this.edgeMap) {
            return this.edgeMap.get(edge);
        }
    }

    private GraphViewerVertexFactory getSuitableVertexFactory(final Class<? extends Object> vertexClass) {
        GraphViewerVertexFactory result = null;
        Class<? extends Object> returnClass = Object.class;

        synchronized (this.vertexFactories) {
            for (final GraphViewerVertexFactory factory : this.vertexFactories) {
                final Class<? extends Object> factoryVertexClass = factory.getVertexClass();

                if (returnClass.isAssignableFrom(factoryVertexClass)) {
                    result = factory;
                    returnClass = factoryVertexClass;
                }
            }
        }

        return result;
    }

    private GraphViewerEdgeFactory getSuitableEdgeFactory(final Class<? extends Edge> edgeClass) {
        GraphViewerEdgeFactory result = null;
        Class<? extends Edge> returnClass = Edge.class;

        synchronized (this.edgeFactories) {
            for (final GraphViewerEdgeFactory factory : this.edgeFactories) {
                final Class<? extends Edge> factoryEdgeClass = factory.getEdgeClass();

                if (returnClass.isAssignableFrom(factoryEdgeClass)) {
                    result = factory;
                    returnClass = factory.getEdgeClass();
                }
            }
        }

        return result;
    }

    private void addVertex(final Object vertex) {
        final GraphViewerVertexFactory factory = this.getSuitableVertexFactory(vertex.getClass());
        final GraphViewerVertex viewerVertex = factory.buildVertex(vertex);
        this.addViewerVertex(viewerVertex);
    }

    private void addEdge(final Edge edge) {
        final GraphViewerEdgeFactory factory = this.getSuitableEdgeFactory(edge.getClass());
        final Set<GraphViewerVertex> vertices = new HashSet<GraphViewerVertex>();

        synchronized (this.vertexMap) {
            for (final Object vertex : edge.getVertices()) {
                final GraphViewerVertex viewerVertex = this.vertexMap.get(vertex);
                vertices.add(viewerVertex);
            }
        }

        final GraphViewerEdge viewerEdge = factory.buildEdge(edge, vertices);
        this.addViewerEdge(viewerEdge);
    }

    private void addViewerVertex(final GraphViewerVertex vertex) {
        synchronized (this.vertexMap) {
            this.vertexMap.put(vertex.getVertex(), vertex);
        }

        vertex.setViewer(this);
        this.layout.addViewerVertex(vertex);

        this.repaint();
    }

    private void addViewerEdge(final GraphViewerEdge edge) {
        synchronized (this.edgeMap) {
            this.edgeMap.put(edge.getEdge(), edge);
        }

        edge.setViewer(this);
        this.layout.addViewerEdge(edge);

        this.repaint();
    }

    public boolean addVertexFactory(final GraphViewerVertexFactory factory) {
        synchronized (this.vertexFactories) {
            return this.vertexFactories.add(factory);
        }
    }

    public boolean addEdgeFactory(final GraphViewerEdgeFactory factory) {
        synchronized (this.edgeFactories) {
            return this.edgeFactories.add(factory);
        }
    }

    public void addVertexMouseListener(final MouseListener listener) {
        synchronized (this.vertexMouseListeners) {
            this.vertexMouseListeners.add(listener);
        }
    }

    public void addEdgeMouseListener(final MouseListener listener) {
        synchronized (this.edgeMouseListeners) {
            this.edgeMouseListeners.add(listener);
        }
    }

    public void addRenderingHint(final Object key, final Object value) {
        synchronized (this.renderingHints) {
            this.renderingHints.put(key, value);
        }

        this.repaint();
    }

    private void removeVertex(final Object vertex) {
        synchronized (this.vertexMap) {
            final GraphViewerVertex viewerVertex = this.vertexMap.get(vertex);
            this.removeViewerVertex(viewerVertex);
        }
    }

    private void removeEdge(final Edge edge) {
        synchronized (this.edgeMap) {
            final GraphViewerEdge viewerEdge = this.edgeMap.get(edge);
            this.removeViewerEdge(viewerEdge);
        }
    }

    private void removeViewerVertex(final GraphViewerVertex vertex) {
        synchronized (this.vertexMap) {
            this.vertexMap.remove(vertex.getVertex());
        }

        this.layout.removeViewerVertex(vertex);

        this.repaint();
    }

    private void removeViewerEdge(final GraphViewerEdge edge) {
        synchronized (this.edgeMap) {
            this.edgeMap.remove(edge.getEdge());
        }

        this.layout.removeViewerEdge(edge);

        this.repaint();
    }

    public boolean removeVertexFactory(final GraphViewerVertexFactory factory) {
        synchronized (this.vertexFactories) {
            return this.vertexFactories.remove(factory);
        }
    }

    public boolean removeEdgeFactory(final GraphViewerEdgeFactory factory) {
        synchronized (this.edgeFactories) {
            return this.edgeFactories.remove(factory);
        }
    }

    public void removeRenderingHint(final Object key) {
        synchronized (this.renderingHints) {
            this.renderingHints.remove(key);
        }

        this.repaint();
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        final Graphics2D g = (Graphics2D) graphics;

        synchronized (this.renderingHints) {
            g.setRenderingHints(this.renderingHints);
        }

        this.layout.paint(g.create());

        synchronized (this.edgeMap) {
            for (final GraphViewerEdge edge : this.edgeMap.values()) {
                edge.paint(g.create());
            }
        }

        synchronized (this.vertexMap) {
            for (final GraphViewerVertex vertex : this.vertexMap.values()) {
                vertex.paint(g.create());
            }
        }

        if ((this.selectionMode == SELECTION_MODE_MULTI) && (this.selectionRectangle != null)) {
            final GraphicsUtil graphicsUtil = new GraphicsUtil(g);
            graphicsUtil.setColor(new Color(1f, 1f, 1f, 0.6f));
            graphicsUtil.fillRectangle(this.selectionRectangle);
            graphicsUtil.setColor(Color.BLACK);
            graphicsUtil.drawRectangle(this.selectionRectangle);
        }
    }

    private void handleMouseEvent(final MouseEvent e) {
        synchronized (this.vertexMap) {
            for (final GraphViewerVertex vertex : this.vertexMap.values()) {
                if (vertex.intersects(e.getPoint())) {
                    e.setSource(vertex.getVertex());
                    this.fireMouseEvent(this.vertexMouseListeners, e);
                    return;
                }
            }

            for (final GraphViewerEdge edge : this.edgeMap.values()) {
                if (edge.intersects(e.getPoint())) {
                    e.setSource(edge.getEdge());
                    this.fireMouseEvent(this.edgeMouseListeners, e);
                    return;
                }
            }
        }
    }

    private void fireMouseEvent(final List<MouseListener> listeners, final MouseEvent e) {
        synchronized (listeners) {
            for (final MouseListener listener : listeners) {
                switch (e.getID()) {
                    case MouseEvent.MOUSE_PRESSED:
                        listener.mousePressed(e);
                        break;
                    case MouseEvent.MOUSE_CLICKED:
                        listener.mouseClicked(e);
                        break;
                    case MouseEvent.MOUSE_RELEASED:
                        listener.mouseReleased(e);
                        break;
                }
            }
        }
    }

    @Override
    public void revalidate() {
        super.revalidate();
    }

    protected void revalidateVertex(final GraphViewerVertex vertex) {
        this.layout.revalidate(vertex);
        this.repaint();
    }

}