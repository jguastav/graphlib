package org.techstartingpoint.javagraphlib.core;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * A typed Graph using  vertices <code>V</code> and edges * <code>E&lt;T&gt;</code>.
 * </p>
 *
 *
 * @param <V> the graph vertex type
 * @param <E> the graph edge type
 *
 * @author Jose Alberto Guastavino
 *
 */

public interface Graph<V,E> {

    Set<E> getAllEdges(V sourceVertex, V targetVertex);
    E getEdge(V sourceVertex, V targetVertex);
    EdgeFactory<V, E> getEdgeFactory();
    E addEdge(V sourceVertex, V targetVertex);
    boolean addVertex(V v);
    Set<E> edgeSet();
    Set<E> edgesOf(V vertex);
    Set<E> incomingEdgesOf(V vertex);
    Set<E> outgoingEdgesOf(V vertex);
    boolean removeAllEdges(Collection<? extends E> edges);
    Set<E> removeAllEdges(V sourceVertex, V targetVertex);
    boolean removeAllVertices(Collection<? extends V> vertices);
    E removeEdge(V sourceVertex, V targetVertex);
    boolean removeEdge(E e);
    boolean removeVertex(V v);
    Set<V> vertexSet();
    V getEdgeSource(E e);
    V getEdgeTarget(E e);
    boolean containsVertex(V v);






}
