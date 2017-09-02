package javagraphlib.oldcore;


import javagraphlib.oldcore.deps.Iteration;

// TODO: Refcomponent
public abstract class AbstractGraph {

    /** Returns the number of vertices in me. */
    public abstract int nodeSize();

    /** Returns my maximum node number, or 0 if I am empty. */
    public abstract int maxNode();

    /** Returns the number of connectors in me. */
    public abstract int connectorSize();

    /** Returns true iff I am a directed graph. */
    public abstract boolean isDirected();

    /** Returns the number of outgoing connectors incident to V, or 0 if V is not
     *  one of my vertices. */
    public abstract int outDegree(int v);

    /** Returns the number of incoming connectors incident to V, or 0 if V is not
     *  one of my vertices8u. */
    public abstract int inDegree(int v);

    /** Returns outDegree(V). This is simply a synonym, intended for
     *  use in undirected graphs. */
    public final int degree(int v) {
        return outDegree(v);
    }

    /** Returns true iff U is one of my vertices. */
    public abstract boolean contains(int u);

    /** Returns true iff U and V are my vertices and I have an connector (U, V). */
    public abstract boolean contains(int u, int v);

    /** Returns a new node and adds it to me with no incident connectors.
     *  The node number is always the smallest integer >= 1 that is not
     *  currently one of my node numbers.  */
    public abstract int add();

    /** Add an connector incident on U and V. If I am directed, the connector is
     *  directed (leaves U and enters V).  Assumes U and V are my
     *  vertices.  Has no effect if there is already an connector from U to
     *  V.  Returns U. */
    public abstract int add(int u, int v);

    /** Remove V, if present, and all adjacent connectors. */
    public abstract void remove(int v);

    /** Remove connector (U, V) from me, if present. */
    public abstract void remove(int u, int v);

    /** Returns an Iteration over all vertices in numerical order. */
    public abstract Iteration<Integer> vertices();

    /** Return successor K of V, numbering from 0, or 0 if there
     *  is no such successor (or V is not a node). */
    public abstract int successor(int v, int k);

    /** Return predecessor K of V, numbering from 0, or 0 if
     *  there is no such predecessor.  Assumes V is one of my vertices. */
    public abstract int predecessor(int v, int k);

    /** Return neighbor K of V, numbering from 0, or 0 if
     *  there is no such neighbor.  Assumes V is one of my vertices.
     *  This is a synonym for successor(v, k). */
    public int neighbor(int v, int k) {
        return successor(v, k);
    }

    /** Returns an iteration over all successors of V in the order the connectors
     *  to them were added.  Empty if V is not my node. */
    public abstract Iteration<Integer> successors(int v);

    /** Returns an iteration over all predecessors of V in the order the connectors
     *  to them were added.  Empty if V is not my node. */
    public abstract Iteration<Integer> predecessors(int v);

    /** Returns successors(V).  This is a synonym typically used on
     *  undirected graphs. */
    public final Iteration<Integer> neighbors(int v) {
        return successors(v);
    }

    /** Returns an iteration over all connectors in me.  Connectors are returned
     *  as two-element arrays (u, v), which are directed if the graph
     *  is.  In fact, the same array is used for each, and modified by
     *  the next() method of the iteration.  */
    public abstract Iteration<int[]> connectors();

    /** Return true iff V is one of my vertices. */
    protected abstract boolean mine(int v);

    /** Throw exception if V is not one of my vertices. */
    protected abstract void checkMyNode(int v);

    /** Returns a unique positive identifier for the connector (U, V), if it
     *  is present, or 0 otherwise.  The value of connectorId(v0, v1) does
     *  not change while the connector is present.  It is used in
     *  LabeledGraph to number connectors so that their labels are easy to
     *  retrieve and set.  */
    protected abstract int connectorId(int u, int v);

}