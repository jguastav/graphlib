package org.techstartingpoint.javagraphlib.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GraphCheckings {
    private static final String GRAPH_CANNOT_BE_NULL = "Graph cannot be null";

    public static <V, E> boolean isEmpty(Graph<V, E> graph)
    {
        Objects.requireNonNull(graph, GRAPH_CANNOT_BE_NULL);
        return graph.edgeSet().isEmpty();
    }



    // TODO: Change to isCyclic
    /**
     * Check if a graph is simple. A graph is simple if it has no self-loops and multiple edges.
     *
     * @param graph a graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return true if a graph is simple, false otherwise
     */
    public static <V, E> boolean isSimple(Graph<V, E> graph)
    {
        Objects.requireNonNull(graph, GRAPH_CANNOT_BE_NULL);

        for (V v : graph.vertexSet()) {
            Set<V> neighbors = new HashSet<>();
            for (E e : graph.outgoingEdgesOf(v)) {
                V u = Graphs.getOppositeVertex(graph, e, v);
                if (u.equals(v) || !neighbors.add(u)) {
                    return false;
                }
            }
        }

        return true;
    }



    /**
     * Test whether a graph is connected.
     *
     *
     * @param graph the input graph
     * @param <V> the graph vertex type
     * @param <E> the graph edge type
     * @return true if the graph is connected, false otherwise
     */
    public static <V, E> boolean isConnected(Graph<V, E> graph)
    {
            // TODO: Write algorithm
        return false;
    }

    public static <V, E> boolean hasCycle(Graph<V, E> graph)
    {
        // TODO: Write algorithm
        return false;
    }




}
