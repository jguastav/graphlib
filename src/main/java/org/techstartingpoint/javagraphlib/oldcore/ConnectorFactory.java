package org.techstartingpoint.javagraphlib.core;

public interface ConnectorFactory<V, E>
{
    E createConnector(V sourceNode, V targetNode);
}
