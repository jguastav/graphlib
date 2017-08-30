package org.techstartingpoint.javagraphlib.oldcore;

import org.techstartingpoint.javagraphlib.oldcore.deps.ConnectorFactory;

import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * A typed Graph using  vertices <code>V</code> and connectors * <code>E&lt;T&gt;</code>.
 * </p>
 *
 *
 * @param <V> the graph node type
 * @param <E> the graph connector type
 *
 * @author Jose Alberto Guastavino
 *
 */

public interface Graph<V,E> {

    Set<E> getAllConnectors(V sourceNode, V targetNode);
    E getConnector(V sourceNode, V targetNode);
    ConnectorFactory<V, E> getConnectorFactory();
    E addConnector(V sourceNode, V targetNode);
    boolean addNode(V v);
    Set<E> connectorSet();
    Set<E> connectorsOf(V node);
    Set<E> incomingConnectorsOf(V node);
    Set<E> outgoingConnectorsOf(V node);
    boolean removeAllConnectors(Collection<? extends E> connectors);
    Set<E> removeAllConnectors(V sourceNode, V targetNode);
    boolean removeAllVertices(Collection<? extends V> vertices);
    E removeConnector(V sourceNode, V targetNode);
    boolean removeConnector(E e);
    boolean removeNode(V v);
    Set<V> nodeSet();
    V getConnectorSource(E e);
    V getConnectorTarget(E e);
    boolean containsNode(V v);






}
