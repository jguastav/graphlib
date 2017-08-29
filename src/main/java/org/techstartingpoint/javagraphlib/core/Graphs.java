package org.techstartingpoint.javagraphlib.core;


//TODO: Rebuild all this class

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * A collection of utilities to assist with graph manipulation.
 *
 */

public class Graphs {
    /**
     * Creates a new edge and adds it to the specified graph similarly to the
     * {@link Graph#addEdge(Object, Object)} method.
     *
     * @param g the graph for which the edge to be added
     * @param sourceVertex source vertex of the edge
     * @param targetVertex target vertex of the edge
     * @param weight weight of the edge
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return The newly created edge if added to the graph, otherwise <code>
     * null</code>.
     *
     * @see Graph#addEdge(Object, Object)
     */
    public static <V,E> void ddEdge(Graph<V, E> g, V sourceVertex, V targetVertex, double weight)
    {

        // we first create the edge and set the weight to make sure that
        // listeners will see the correct weight upon addEdge.

        g.addEdge(sourceVertex, targetVertex);
        return;
    }

    /**
     * Adds the specified source and target vertices to the graph, if not already included, and
     * creates a new edge and adds it to the specified graph similarly to the
     * {@link Graph#addEdge(Object, Object)} method.
     *
     * @param g the graph for which the specified edge to be added
     * @param sourceVertex source vertex of the edge
     * @param targetVertex target vertex of the edge
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return The newly created edge if added to the graph, otherwise <code>
     * null</code>.
     */
    public static <V, E> E addEdgeWithVertices(Graph<V, E> g, V sourceVertex, V targetVertex)
    {
        g.addVertex(sourceVertex);
        g.addVertex(targetVertex);

        return g.addEdge(sourceVertex, targetVertex);
    }



    /**
     * Adds a subset of the edges of the specified source graph to the specified destination graph.
     * The behavior of this operation is undefined if either of the graphs is modified while the
     * operation is in progress. {@link #addEdgeWithVertices} is used for the transfer, so source
     * vertexes will be added automatically to the target graph.
     *
     * @param destination the graph to which edges are to be added
     * @param source the graph used as a source for edges to add
     * @param edges the edges to be added
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return <tt>true</tt> if this graph changed as a result of the call
     */
    public static <V, E> void addAllEdges(
            Graph<? super V, ? super E> destination, Graph<V, E> source, Collection<? extends E> edges)
    {
        boolean modified = false;

        for (E e : edges) {
            V s = source.getEdgeSource(e);
            V t = source.getEdgeTarget(e);
            destination.addVertex(s);
            destination.addVertex(t);
        }

        return;
    }

    /**
     * Adds all of the specified vertices to the destination graph. The behavior of this operation
     * is undefined if the specified vertex collection is modified while the operation is in
     * progress. This method will invoke the {@link Graph#addVertex(Object)} method.
     *
     * @param destination the graph to which edges are to be added
     * @param vertices the vertices to be added to the graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return <tt>true</tt> if graph changed as a result of the call
     *
     * @throws NullPointerException if the specified vertices contains one or more null vertices, or
     *         if the specified vertex collection is <tt>
     * null</tt>.
     *
     * @see Graph#addVertex(Object)
     */
    public static <V, E> boolean addAllVertices(
            Graph<? super V, ? super E> destination, Collection<? extends V> vertices)
    {
        boolean modified = false;

        for (V v : vertices) {
            modified |= destination.addVertex(v);
        }

        return modified;
    }

    /**
     * Returns a list of vertices that are the neighbors of a specified vertex. If the graph is a
     * multigraph vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link Graph#edgesOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for neighbors in
     * @param vertex the vertex to get the neighbors of
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return a list of the vertices that are the neighbors of the specified vertex.
     */
    public static <V, E> List<V> neighborListOf(Graph<V, E> g, V vertex)
    {
        List<V> neighbors = new ArrayList<>();

        for (E e : g.edgesOf(vertex)) {
            neighbors.add(getOppositeVertex(g, e, vertex));
        }

        return neighbors;
    }

    /**
     * Returns a list of vertices that are the direct predecessors of a specified vertex. If the
     * graph is a multigraph, vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link Graph#incomingEdgesOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for predecessors in
     * @param vertex the vertex to get the predecessors of
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return a list of the vertices that are the direct predecessors of the specified vertex.
     */
    public static <V, E> List<V> predecessorListOf(Graph<V, E> g, V vertex)
    {
        List<V> predecessors = new ArrayList<>();
        Set<? extends E> edges = g.incomingEdgesOf(vertex);

        for (E e : edges) {
            predecessors.add(getOppositeVertex(g, e, vertex));
        }

        return predecessors;
    }

    /**
     * Returns a list of vertices that are the direct successors of a specified vertex. If the graph
     * is a multigraph vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link Graph#outgoingEdgesOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for successors in
     * @param vertex the vertex to get the successors of
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return a list of the vertices that are the direct successors of the specified vertex.
     */
    public static <V, E> List<V> successorListOf(Graph<V, E> g, V vertex)
    {
        List<V> successors = new ArrayList<>();
        Set<? extends E> edges = g.outgoingEdgesOf(vertex);

        for (E e : edges) {
            successors.add(getOppositeVertex(g, e, vertex));
        }

        return successors;
    }


    /**
     * Gets the vertex opposite another vertex across an edge.
     *
     * @param g graph containing e and v
     * @param e edge in g
     * @param v vertex in g
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return vertex opposite to v across e
     */
    public static <V, E> V getOppositeVertex(Graph<V, E> g, E e, V v)
    {
        V source = g.getEdgeSource(e);
        V target = g.getEdgeTarget(e);
        if (v.equals(source)) {
            return target;
        } else if (v.equals(target)) {
            return source;
        } else {
            throw new IllegalArgumentException("no such vertex: " + v.toString());
        }
    }

    /**
     *
     * @param graph graph to be mutated
     * @param source source vertex of the new edges
     * @param targets target vertices for the new edges
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     */
    public static <V, E> void addOutgoingEdges(Graph<V, E> graph, V source, Iterable<V> targets)
    {
        if (!graph.containsVertex(source)) {
            graph.addVertex(source);
        }
        for (V target : targets) {
            if (!graph.containsVertex(target)) {
                graph.addVertex(target);
            }
            graph.addEdge(source, target);
        }
    }

    /**
     * Add edges from multiple source vertices to one target vertex. Whether duplicates are created
     * depends on the underlying {@link Graph} implementation.
     *
     * @param graph graph to be mutated
     * @param target target vertex for the new edges
     * @param sources source vertices for the new edges
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     */
    public static <V, E> void addIncomingEdges(Graph<V, E> graph, V target, Iterable<V> sources)
    {
        if (!graph.containsVertex(target)) {
            graph.addVertex(target);
        }
        for (V source : sources) {
            if (!graph.containsVertex(source)) {
                graph.addVertex(source);
            }
            graph.addEdge(source, target);
        }
    }

    /**
     * Check if a vertex has any direct successors.
     *
     * @param graph the graph to look for successors
     * @param vertex the vertex to look for successors
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return true if the vertex has any successors, false otherwise
     */
    public static <V, E> boolean vertexHasSuccessors(Graph<V, E> graph, V vertex)
    {
        return !graph.outgoingEdgesOf(vertex).isEmpty();
    }

    /**
     * Check if a vertex has any direct predecessors.
     *
     * @param graph the graph to look for predecessors
     * @param vertex the vertex to look for predecessors
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     *
     * @return true if the vertex has any predecessors, false otherwise
     */
    public static <V, E> boolean vertexHasPredecessors(Graph<V, E> graph, V vertex)
    {
        return !graph.incomingEdgesOf(vertex).isEmpty();
    }
}
