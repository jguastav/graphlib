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
     * Creates a new connector and adds it to the specified graph similarly to the
     * {@link org.techstartingpoint.javagraphlib.core.Graph#addConnector(Object, Object)} method.
     *
     * @param g the graph for which the connector to be added
     * @param sourceNode source node of the connector
     * @param targetNode target node of the connector
     * @param weight weight of the connector
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return The newly created connector if added to the graph, otherwise <code>
     * null</code>.
     *
     * @see org.techstartingpoint.javagraphlib.core.Graph#addConnector(Object, Object)
     */
    public static <V,E> void ddConnector(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, V sourceNode, V targetNode, double weight)
    {

        // we first create the connector and set the weight to make sure that
        // listeners will see the correct weight upon addConnector.

        g.addConnector(sourceNode, targetNode);
        return;
    }

    /**
     * Adds the specified source and target vertices to the graph, if not already included, and
     * creates a new connector and adds it to the specified graph similarly to the
     * {@link org.techstartingpoint.javagraphlib.core.Graph#addConnector(Object, Object)} method.
     *
     * @param g the graph for which the specified connector to be added
     * @param sourceNode source node of the connector
     * @param targetNode target node of the connector
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return The newly created connector if added to the graph, otherwise <code>
     * null</code>.
     */
    public static <V, E> E addConnectorWithVertices(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, V sourceNode, V targetNode)
    {
        g.addNode(sourceNode);
        g.addNode(targetNode);

        return g.addConnector(sourceNode, targetNode);
    }



    /**
     * Adds a subset of the connectors of the specified source graph to the specified destination graph.
     * The behavior of this operation is undefined if either of the graphs is modified while the
     * operation is in progress. {@link #addConnectorWithVertices} is used for the transfer, so source
     * nodees will be added automatically to the target graph.
     *
     * @param destination the graph to which connectors are to be added
     * @param source the graph used as a source for connectors to add
     * @param connectors the connectors to be added
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return <tt>true</tt> if this graph changed as a result of the call
     */
    public static <V, E> void addAllConnectors(
            org.techstartingpoint.javagraphlib.core.Graph<? super V, ? super E> destination, org.techstartingpoint.javagraphlib.core.Graph<V, E> source, Collection<? extends E> connectors)
    {
        boolean modified = false;

        for (E e : connectors) {
            V s = source.getConnectorSource(e);
            V t = source.getConnectorTarget(e);
            destination.addNode(s);
            destination.addNode(t);
        }

        return;
    }

    /**
     * Adds all of the specified vertices to the destination graph. The behavior of this operation
     * is undefined if the specified node collection is modified while the operation is in
     * progress. This method will invoke the {@link org.techstartingpoint.javagraphlib.core.Graph#addNode(Object)} method.
     *
     * @param destination the graph to which connectors are to be added
     * @param vertices the vertices to be added to the graph
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return <tt>true</tt> if graph changed as a result of the call
     *
     * @throws NullPointerException if the specified vertices contains one or more null vertices, or
     *         if the specified node collection is <tt>
     * null</tt>.
     *
     * @see org.techstartingpoint.javagraphlib.core.Graph#addNode(Object)
     */
    public static <V, E> boolean addAllVertices(
            org.techstartingpoint.javagraphlib.core.Graph<? super V, ? super E> destination, Collection<? extends V> vertices)
    {
        boolean modified = false;

        for (V v : vertices) {
            modified |= destination.addNode(v);
        }

        return modified;
    }

    /**
     * Returns a list of vertices that are the neighbors of a specified node. If the graph is a
     * multigraph vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link org.techstartingpoint.javagraphlib.core.Graph#connectorsOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for neighbors in
     * @param node the node to get the neighbors of
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return a list of the vertices that are the neighbors of the specified node.
     */
    public static <V, E> List<V> neighborListOf(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, V node)
    {
        List<V> neighbors = new ArrayList<>();

        for (E e : g.connectorsOf(node)) {
            neighbors.add(getOppositeNode(g, e, node));
        }

        return neighbors;
    }

    /**
     * Returns a list of vertices that are the direct predecessors of a specified node. If the
     * graph is a multigraph, vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link org.techstartingpoint.javagraphlib.core.Graph#incomingConnectorsOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for predecessors in
     * @param node the node to get the predecessors of
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return a list of the vertices that are the direct predecessors of the specified node.
     */
    public static <V, E> List<V> predecessorListOf(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, V node)
    {
        List<V> predecessors = new ArrayList<>();
        Set<? extends E> connectors = g.incomingConnectorsOf(node);

        for (E e : connectors) {
            predecessors.add(getOppositeNode(g, e, node));
        }

        return predecessors;
    }

    /**
     * Returns a list of vertices that are the direct successors of a specified node. If the graph
     * is a multigraph vertices may appear more than once in the returned list.
     *
     * <p>
     * The method uses {@link org.techstartingpoint.javagraphlib.core.Graph#outgoingConnectorsOf(Object)} to traverse the graph.
     *
     * @param g the graph to look for successors in
     * @param node the node to get the successors of
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return a list of the vertices that are the direct successors of the specified node.
     */
    public static <V, E> List<V> successorListOf(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, V node)
    {
        List<V> successors = new ArrayList<>();
        Set<? extends E> connectors = g.outgoingConnectorsOf(node);

        for (E e : connectors) {
            successors.add(getOppositeNode(g, e, node));
        }

        return successors;
    }


    /**
     * Gets the node opposite another node across an connector.
     *
     * @param g graph containing e and v
     * @param e connector in g
     * @param v node in g
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return node opposite to v across e
     */
    public static <V, E> V getOppositeNode(org.techstartingpoint.javagraphlib.core.Graph<V, E> g, E e, V v)
    {
        V source = g.getConnectorSource(e);
        V target = g.getConnectorTarget(e);
        if (v.equals(source)) {
            return target;
        } else if (v.equals(target)) {
            return source;
        } else {
            throw new IllegalArgumentException("no such node: " + v.toString());
        }
    }

    /**
     *
     * @param graph graph to be mutated
     * @param source source node of the new connectors
     * @param targets target vertices for the new connectors
     * @param <V> the graph node type
     * @param <E> the graph connector type
     */
    public static <V, E> void addOutgoingConnectors(org.techstartingpoint.javagraphlib.core.Graph<V, E> graph, V source, Iterable<V> targets)
    {
        if (!graph.containsNode(source)) {
            graph.addNode(source);
        }
        for (V target : targets) {
            if (!graph.containsNode(target)) {
                graph.addNode(target);
            }
            graph.addConnector(source, target);
        }
    }

    /**
     * Add connectors from multiple source vertices to one target node. Whether duplicates are created
     * depends on the underlying {@link org.techstartingpoint.javagraphlib.core.Graph} implementation.
     *
     * @param graph graph to be mutated
     * @param target target node for the new connectors
     * @param sources source vertices for the new connectors
     * @param <V> the graph node type
     * @param <E> the graph connector type
     */
    public static <V, E> void addIncomingConnectors(org.techstartingpoint.javagraphlib.core.Graph<V, E> graph, V target, Iterable<V> sources)
    {
        if (!graph.containsNode(target)) {
            graph.addNode(target);
        }
        for (V source : sources) {
            if (!graph.containsNode(source)) {
                graph.addNode(source);
            }
            graph.addConnector(source, target);
        }
    }

    /**
     * Check if a node has any direct successors.
     *
     * @param graph the graph to look for successors
     * @param node the node to look for successors
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return true if the node has any successors, false otherwise
     */
    public static <V, E> boolean nodeHasSuccessors(org.techstartingpoint.javagraphlib.core.Graph<V, E> graph, V node)
    {
        return !graph.outgoingConnectorsOf(node).isEmpty();
    }

    /**
     * Check if a node has any direct predecessors.
     *
     * @param graph the graph to look for predecessors
     * @param node the node to look for predecessors
     * @param <V> the graph node type
     * @param <E> the graph connector type
     *
     * @return true if the node has any predecessors, false otherwise
     */
    public static <V, E> boolean nodeHasPredecessors(org.techstartingpoint.javagraphlib.core.Graph<V, E> graph, V node)
    {
        return !graph.incomingConnectorsOf(node).isEmpty();
    }
}
