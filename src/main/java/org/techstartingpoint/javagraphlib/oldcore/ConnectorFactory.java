package org.techstartingpoint.javagraphlib.oldcore;

public interface ConnectorFactory<V, E>
{
    E createConnector(V sourceNode, V targetNode);
}
