package javagraphlib.original;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DirectedGraph<T> implements Graph<T> {

    private final Logger logger = LoggerFactory.getLogger(DirectedGraph.class);

    private HashMap<T, Vertex<T>> graph;

    public DirectedGraph() {
        graph = new HashMap<>();
    }

    public boolean contains(T vertex) {
        return graph.containsKey(vertex);
    }

    public boolean areAdjacent(T src, T dest) throws OnelakeException {
        Vertex<T> srcVertex = graph.get(src);
        Vertex<T> destVertex = graph.get(dest);

        if (srcVertex == null || destVertex == null) {
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);
        }

        return srcVertex.hasNeighbor(destVertex);
    }

    public void addVertex(T vertex) {
        Vertex<T> vertexNode = new Vertex<>(vertex);
        graph.put(vertex, vertexNode);
    }

    public void removeVertex(T vertex) throws OnelakeException {
        Vertex<T> vertexNode = graph.get(vertex);

        if (vertexNode == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);

        Iterator<Vertex<T>> iterator = graph.values().iterator();
        while (iterator.hasNext()) {
            Vertex<T> possibleLink = iterator.next();
            possibleLink.removeEdgeTo(vertexNode);
        }

        graph.remove(vertex);
    }

    public void addEdge(T from, int fromPortIndex, T to, int toPortIndex) throws OnelakeException {
        Vertex<T> fromVertex = graph.get(from);
        Vertex<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);


        Edge<T> edge = new Edge<>(fromVertex, fromPortIndex, toVertex, toPortIndex);
        fromVertex.addEdge(edge);
    }

    public void removeEdge(T from, T to) throws OnelakeException {
        Vertex<T> fromVertex = graph.get(from);
        Vertex<T> toVertex = graph.get(to);

        if (fromVertex == null || toVertex == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);

        if (fromVertex.hasNeighbor(toVertex)) {
            fromVertex.removeEdgeTo(toVertex);
        }
    }

    public List<T> getNeighborsFor(T vertex) throws OnelakeException {
        if (graph.get(vertex) == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);

        return graph.get(vertex).getNeighbors();
    }

    public void depthSearch(T start) throws OnelakeException {
        if (graph.get(start) == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);

        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Stack<T> stack = new Stack<>();
        stack.push(start);

        while (!stack.empty()) {
            T current = stack.peek();
            T neighbor = null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor))
                    break;
            }

            if (neighbor != null && !visited.contains(neighbor)) {
                visited.add(neighbor);
                System.out.println(neighbor);
                stack.push(neighbor);
            } else {
                stack.pop();
            }
        }
    }

    public void breathSearch(T start) throws OnelakeException {
        if (graph.get(start) == null)
            throw OnelakeException.build(WorkflowErrorCode.NoSuchVertex);

        Collection<T> visited = new HashSet<>();
        visited.add(start);

        Queue<T> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            T neighbor= null;
            Iterator<T> iterator = getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                neighbor = iterator.next();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    System.out.println(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}
