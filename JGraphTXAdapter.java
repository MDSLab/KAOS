import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

/**
 * Offers compatibility between a {@link org.jgrapht.Graph} and an
 * {@link com.mxgraph.view.mxGraph}. Propagates changes (adding and deleting
 * vertices and edges) from the mxGraph to the Graph. If the Graph is also an
 * instance of ListenableGraph, changes from the Graph will also be propagated
 * to the mxGraph. Only changes causing an mxEvent.ADD_CELLS or
 * mxEvent.REMOVE_CELLS will be propagated. (so don't use methods from the
 * underlying mxIGraphModel)
 * 
 * 
 * @author GraphIT
 * 
 * @param <V>
 *            type of the vertices of the corresponding
 *            {@link org.jgrapht.Graph}
 * @param <E>
 *            type of the edges of the corresponding {@link org.jgrapht.Graph}
 * 
 * @since 1.6
 * 
 */
public class JGraphTXAdapter<V, E> extends mxGraph implements
        GraphListener<V, E>, mxIEventListener {

    /**
     * the corresponding Graph
     */
    private Graph<V, E> graphT;

    /**
     * optional style information for vertices and edges in the mxGraph
     */
    private String edgeStyle, vertexStyle;

    /**
     * maps from the Graph vertices to the mxCells of the mxGraph and vice versa
     */
    private Map<V, mxICell> vertexToCell = new HashMap<V, mxICell>();
    private Map<mxICell, V> cellToVertex = new HashMap<mxICell, V>();

    /**
     * maps from the Graph edges to the mxCells of the mxGraph and vice versa
     */
    private Map<E, mxICell> edgeToCell = new HashMap<E, mxICell>();
    private Map<mxICell, E> cellToEdge = new HashMap<mxICell, E>();

    /**
     * sets for propagating changes between JGraphX and JGraphT and to avoid
     * bouncing events between the two listeners.
     */
    private Set<Object> jtElementBeingAdded = new HashSet<Object>();
    private Set<Object> jtElementBeingRemoved = new HashSet<Object>();
    private Set<mxICell> jxElementBeingAdded = new HashSet<mxICell>();
    private Set<mxICell> jxElementBeingRemoved = new HashSet<mxICell>();

    /**
     * simple constructor without style information
     * 
     * @param graphT
     *            the Graph to describe as an mxGraph
     */
    public JGraphTXAdapter(Graph<V, E> graphT) {
        this(graphT, null, null);
    }

    /**
     * constructor with optional style information for vertices and edges.
     * registers listeners for changes in the mxGraph and, if possible for
     * changes in the Graph.
     * 
     * @param graphT
     *            the Graph to describe as an mxGraph
     * @param edgeStyle
     *            optional style information for the mxGraph edges.
     *            <code>null</code> if no style is desired
     * @param vertexStyle
     *            optional style information for the mxGraph vertices.
     *            <code>null</code> if no style is desired
     */
    public JGraphTXAdapter(Graph<V, E> graphT, String edgeStyle,
            String vertexStyle) {
        this.graphT = graphT;
        this.edgeStyle = edgeStyle;
        this.vertexStyle = vertexStyle;
        addListener(mxEvent.ADD_CELLS, this);
        addListener(mxEvent.REMOVE_CELLS, this);
        if (graphT instanceof ListenableGraph<?, ?>) {
            ((ListenableGraph<V, E>) graphT).addGraphListener(this);
        }
        insertGraph();
    }

    /**
     * adds all vertices and edges of the Graph to the mxGraph
     */
    private void insertGraph() {
        model.beginUpdate();
        try {
            for (V vertex : graphT.vertexSet()) {
                addGraphTVertex(vertex);
            }
            for (E edge : graphT.edgeSet()) {
                addGraphTEdge(edge);
            }
        } finally {
            getModel().endUpdate();
        }

    }

    /**
     * adds an edge from the corresponding Graph to the mxGraph
     * 
     * @param edge
     *            to be added
     */
    private void addGraphTEdge(E edge) {
        model.beginUpdate();
        try {
            V source = graphT.getEdgeSource(edge);
            V target = graphT.getEdgeTarget(edge);
            mxCell cell = (mxCell) createEdge(getDefaultParent(), null, edge,
                    getVertexToCell(source), getVertexToCell(target),
                    edgeStyle);
            insertEdgeInternally(cell, getVertexToCell(source),
                    getVertexToCell(target));
        } finally {
            model.endUpdate();
        }
    }

    /**
     * adds a vertex from the corresponding Graph to the mxGraph
     * 
     * @param vertex
     *            to be added
     */
    private void addGraphTVertex(V vertex) {
        model.beginUpdate();
        try {
            mxCell cell = (mxCell) createVertex(getDefaultParent(), null,
                    vertex, 0, 0, 40, 40, vertexStyle);
            insertVertexInternally(cell);
        } finally {
            model.endUpdate();
        }
    }

    /**
     * returns the associated mxCell for a given vertex. returns
     * <code>null</code> if the vertex is invalid.
     * 
     * @return the associated cell
     */
    public mxICell getVertexToCell(V vertex) {
        return vertexToCell.get(vertex);
    }

    /**
     * returns the associated vertex for a given mxCell. returns
     * <code>null</code> if the vertex is invalid.
     * 
     * @return the associated vertex
     */
    public V getCellToVertex(mxICell cell) {
        return cellToVertex.get(cell);
    }

    /**
     * returns the associated mxCell for a given edge. returns <code>null</code>
     * if the edge is invalid.
     * 
     * @return the associated cell
     */
    public mxICell getEdgeToCell(E edge) {
        return edgeToCell.get(edge);
    }

    /**
     * returns the associated mxCell for a given edge. returns
     * <code>null</code> if the edge is invalid.
     * 
     * @return the associated vertex
     */
    public E getCellToEdge(mxICell cell) {
        return cellToEdge.get(cell);
    }

    /*
     * Below are the internally add and remove Methods
     */

    private void insertEdgeInternally(mxICell cell, mxICell source,
            mxICell target) {
        jxElementBeingAdded.add(cell);
        model.beginUpdate();
        try {
            addCell(cell, cell.getParent(), null, source, target);
        } finally {
            model.endUpdate();
        }
        jxElementBeingAdded.remove(cell);
    }

    private void insertVertexInternally(mxICell cell) {
        jxElementBeingAdded.add(cell);
        model.beginUpdate();
        try {
            addCell(cell);
        } finally {
            model.endUpdate();
        }
        jxElementBeingAdded.remove(cell);
    }

    private void removeEdgeInternally(mxICell cell) {
        jxElementBeingRemoved.add(cell);
        model.beginUpdate();
        try {
            removeCells(new Object[] { cell });
        } finally {
            model.endUpdate();
        }
        jxElementBeingRemoved.remove(cell);
    }

    private void removeVertexInternally(mxICell cell) {
        jxElementBeingRemoved.add(cell);
        model.beginUpdate();
        try {
            removeCells(new Object[] { cell });
        } finally {
            model.endUpdate();
        }
        jxElementBeingRemoved.remove(cell);
    }

    private void internallyAddJGraphTEdge(V source, V target, mxICell cell) {
        E edge = graphT.getEdgeFactory().createEdge(source, target);
        jtElementBeingAdded.add(edge);
        if (target != null) {
            graphT.addEdge(source, target, edge);
            cell.setValue(edge);
        }
        jtElementBeingAdded.remove(edge);
    }

    private void internallyAddJGraphTVertex(V vertex) {
        jtElementBeingAdded.add(vertex);
        graphT.addVertex(vertex);
        jtElementBeingAdded.remove(vertex);
    }

    private void internallyRemoveJGraphTEdge(E edge) {
        jtElementBeingRemoved.add(edge);
        graphT.removeEdge(edge);
        jtElementBeingRemoved.remove(edge);
    }

    private void internallyRemoveJGraphTVertex(V vertex) {
        jtElementBeingRemoved.add(vertex);
        graphT.removeVertex(vertex);
        jtElementBeingRemoved.remove(vertex);
    }

    /*
     * Below are the event handlers for the GraphListener that will handle
     * adding and removing vertices and edges from the mxGraph should the Graph
     * also be a ListenableGraph.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public void vertexAdded(GraphVertexChangeEvent<V> e) {
        V vertex = e.getVertex();
        if (!jtElementBeingAdded.remove(vertex)) {
            addGraphTVertex(vertex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vertexRemoved(GraphVertexChangeEvent<V> e) {
        V vertex = e.getVertex();
        if (!jtElementBeingRemoved.remove(vertex)) {
            removeVertexInternally(getVertexToCell(vertex));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edgeAdded(GraphEdgeChangeEvent<V, E> e) {
        E edge = e.getEdge();
        if (!jtElementBeingAdded.remove(edge)) {
            addGraphTEdge(edge);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edgeRemoved(GraphEdgeChangeEvent<V, E> e) {
        E edge = e.getEdge();
        if (!jtElementBeingRemoved.remove(edge)) {
            removeEdgeInternally(getEdgeToCell(edge));
        }
    }

    /*
     * Below is the event handler that adds and deletes vertices and edges from
     * the Graph and updates the corresponding maps
     */

    @SuppressWarnings("unchecked")
    @Override
    public void invoke(Object sender, mxEventObject evt) {
        Object[] cells = (Object[]) evt.getProperty("cells");
        if (evt.getName().equals(mxEvent.ADD_CELLS)) {
            for (Object o : cells) {
                if (o instanceof mxCell) {
                    mxCell cell = (mxCell) o;
                    if (cell.isEdge()) {
                        if (!jxElementBeingAdded.remove(cell)) {
                            internallyAddJGraphTEdge(
                                    getCellToVertex(cell.getSource()),
                                    getCellToVertex(cell.getTarget()), cell);
                        }
                        cellToEdge.put(cell, (E) cell.getValue());
                        edgeToCell.put((E) cell.getValue(), cell);
                    } else if (cell.isVertex()) {
                        if (!jxElementBeingAdded.remove(cell)) {
                            internallyAddJGraphTVertex((V) cell.getValue());
                        }
                        cellToVertex.put(cell, (V) cell.getValue());
                        vertexToCell.put((V) cell.getValue(), cell);
                    }
                }
            }
        }
        if (evt.getName().equals(mxEvent.REMOVE_CELLS)) {
            for (Object o : cells) {
                if (o instanceof mxCell) {
                    mxCell cell = (mxCell) o;
                    if (cell.isEdge()) {
                        if (!jxElementBeingRemoved.remove(cell)) {
                            internallyRemoveJGraphTEdge((E) cell.getValue());
                        }
                        cellToEdge.remove(cell);
                        edgeToCell.remove((E) cell.getValue());
                    } else if (cell.isVertex()) {
                        if (!jxElementBeingRemoved.remove(cell)) {
                            internallyRemoveJGraphTVertex((V) cell.getValue());
                        }
                        cellToVertex.remove(cell);
                        vertexToCell.remove((V) cell.getValue());
                    }
                }
            }
        }
    }

}