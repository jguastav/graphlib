package org.techstartingpoint.javagraphlib.oldcore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GraphCheckings {
    private static final String GRAPH_CANNOT_BE_NULL = "Graph cannot be null";

    public static <V, E> boolean isEmpty(org.techstartingpoint.javagraphlib.oldcore.Graph<V, E> graph)
    {
        Objects.requireNonNull(graph, GRAPH_CANNOT_BE_NULL);
        return graph.connectorSet().isEmpty();
    }



    // TODO: Change to isCyclic
    /**
     * Check if a graph is simple. A graph is simple if it has no self-loops and multiple connectors.
     *
     * @param graph a graph
     * @param <V> the graph node type
     * @param <E> the graph connector type
     * @return true if a graph is simple, false otherwise
     */
    public static <V, E> boolean isSimple(org.techstartingpoint.javagraphlib.oldcore.Graph<V, E> graph)
    {
        Objects.requireNonNull(graph, GRAPH_CANNOT_BE_NULL);

        for (V v : graph.nodeSet()) {
            Set<V> neighbors = new HashSet<>();
            /*
            for (E e : graph.outgoingConnectorsOf(v)) {

                V u = org.techstartingpoint.javagraphlib.oldcore.Graphs.getOppositeNode(graph, e, v);
                if (u.equals(v) || !neighbors.add(u)) {
                    return false;
                }
            }
            */
        }

        return true;
    }



    /**
     * Test whether a graph is connected.
     *
     *
     * @param graph the input graph
     * @param <V> the graph node type
     * @param <E> the graph connector type
     * @return true if the graph is connected, false otherwise
     */
    public static <V, E> boolean isConnected(org.techstartingpoint.javagraphlib.oldcore.Graph<V, E> graph)
    {
            // TODO: Write algorithm
        return false;
    }

    public static <V, E> boolean hasCycle(org.techstartingpoint.javagraphlib.oldcore.Graph<V, E> graph)
    {
        // TODO: Write algorithm
        return false;
    }




}
