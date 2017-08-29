package javagraphlib.original;

public class Edge<T> {
    Vertex<T> from;
    int fromPortIndex;
    Vertex<T> to;
    int toPortIndex;

    Edge(Vertex<T> from, int fromPortIndex,
         Vertex<T> to, int toPortIndex) {
        this.from = from;
        this.fromPortIndex = fromPortIndex;
        this.to = to;
        this.toPortIndex = toPortIndex;
    }

    Vertex<T> dest() {
        return to;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge<T> other = (Edge<T>) obj;
        if (from == null) {
            if (other.from != null)
                return false;
        } else if (!from.equals(other.from))
            return false;
        if (to == null) {
            if (other.to != null)
                return false;
        } else if (!to.equals(other.to))
            return false;
        if (toPortIndex != other.toPortIndex)
            return false;
        if (fromPortIndex != other.fromPortIndex)
            return false;
        return true;
    }
}
