package org.techstartingpoint.javagraphlib.core;

public interface EdgeFactory<V, E>
{
    E createEdge(V sourceVertex, V targetVertex);
}
