package javagraphlib.original;

import java.util.Collection;

public interface Graph<T> {
    boolean contains(T item);
    void addVertex(T vertex);
    boolean areAdjacent(T a, T b) throws Exception;
    void removeVertex(T vertex) throws Exception;
    void addEdge(T from, int fromPortIndex, T to, int toPortIndex) throws Exception;
    void removeEdge(T from, T to) throws Exception;
    Collection<T> getNeighborsFor(T vertex) throws Exception;
    void depthSearch(T start) throws Exception;
    void breathSearch(T start) throws Exception;
}
