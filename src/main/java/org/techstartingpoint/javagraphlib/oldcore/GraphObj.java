package org.techstartingpoint.javagraphlib.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

abstract class GraphObj extends AbstractGraph {

    /** A new, empty Graph. */
    GraphObj() {
        successorData = new HashMap<Integer, ArrayList<Integer>>();
        predecessorData = new HashMap<Integer, ArrayList<Integer>>();
        nodes = new ArrayList<Integer>();
    }

    @Override
    public int nodeSize() {
        return successorData.size();
    }

    @Override
    public int maxNode() {
        if (nodeSize() == 0) {
            return 0;
        }
        int maximum = nodes.get(0);
        for (int index = 1; index < nodes.size(); index++) {
            if (nodes.get(index) > maximum) {
                maximum = nodes.get(index);
            }
        }
        return maximum;
    }

    @Override
    public int connectorSize() {
        int similar = 0;
        for (int[] tuple : connectors()) {
            similar++;
        }
        return similar;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!successorData.containsKey(v)) {
            return 0;
        }
        return successorData.get(v).size();
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return nodes.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            return successorData.get(u).contains(v);
        }
        return false;
    }

    @Override
    public int add() {
        if (nodes.size() == 0) {
            nodes.add(1);
            successorData.put(1, new ArrayList<Integer>());
            if (isDirected()) {
                predecessorData.put(1, new ArrayList<Integer>());
            }
            return 1;
        } else {
            int item = 0;
            sortlist(nodes);
            if (!nodes.contains(1)) {
                nodes.add(1);
                successorData.put(1, new ArrayList<Integer>());
                if (isDirected()) {
                    predecessorData.put(1, new ArrayList<Integer>());
                }
                return 1;
            }
            for (int index = 0; index < nodes.size() - 1; index++) {
                if (nodes.get(index + 1) - nodes.get(index) > 1) {
                    item = nodes.get(index) + 1;
                    nodes.add(item);
                    successorData.put(item, new ArrayList<Integer>());
                    if (isDirected()) {
                        predecessorData.put(item, new ArrayList<Integer>());
                    }
                    break;
                }
            }
            if (item == 0) {
                int newitem = nodes.get(nodes.size() - 1) + 1;
                nodes.add(newitem);
                successorData.put(newitem, new ArrayList<Integer>());
                if (isDirected()) {
                    predecessorData.put(newitem, new ArrayList<Integer>());
                }
                return newitem;
            } else {
                return item;
            }
        }
    }

    /** RETURNS and sorts the given list NUM. */
    void sortlist(ArrayList<Integer> num) {
        Collections.sort(num);
    }

    @Override
    public int add(int u, int v) {
        if (contains(u) && contains(v)) {
            if (isDirected()) {
                if (!successorData.get(u).contains(v)) {
                    successorData.get(u).add(v);
                }
                if (!predecessorData.get(v).contains(u)) {
                    predecessorData.get(v).add(u);
                }
            } else {
                if (u == v) {
                    successorData.get(u).add(v);
                } else {
                    if (!successorData.get(u).contains(v)) {
                        successorData.get(u).add(v);
                        successorData.get(v).add(u);
                    }
                }
            }
            return u;
        }
        return u;
    }

    @Override
    public void remove(int v) {
        if (isDirected()) {
            ArrayList<Integer> succ = predecessorData.get(v);
            nodes.remove((Integer) v);
            ArrayList<Integer> preds = successorData.get(v);
            for (int item : preds) {
                predecessorData.get(item).remove((Integer) v);
            }
            for (int newitem : succ) {
                successorData.get(newitem).remove((Integer) v);
            }
            predecessorData.remove(v);
            successorData.remove(v);
        } else {
            ArrayList<Integer> listNodes = successorData.get(v);
            if (successorData.get(v).contains(v)) {
                listNodes.remove((Integer) v);
            }
            successorData.remove(v);
            nodes.remove((Integer) v);
            for (int item : listNodes) {
                successorData.get(item).remove((Integer) v);
            }
        }
    }

    @Override
    public void remove(int u, int v) {
        if (contains(u) && contains(v)) {
            if (isDirected()) {
                if (successorData.get(u).contains(v)) {
                    successorData.get(u).remove((Integer) v);
                    predecessorData.get(v).remove((Integer) u);
                } else {
                    return;
                }
            } else {
                if (successorData.get(u).contains(v)) {
                    successorData.get(u).remove((Integer) v);
                    successorData.get(v).remove((Integer) u);
                } else {
                    return;
                }
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(nodes);
    }

    @Override
    public int successor(int v, int k) {
        if (!nodes.contains(v)) {
            return 0;
        } else {
            if (k >= successorData.get(v).size() || k < 0) {
                return 0;
            } else {
                return successorData.get(v).get(k);
            }
        }
    }

    @Override
    public abstract int predecessor(int v, int k);

    @Override
    public Iteration<Integer> successors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(new ArrayList<Integer>());
        } else {
            return Iteration.iteration(successorData.get(v));
        }
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> connectors() {
        ArrayList<int[]> connectorData = new ArrayList<int[]>();
        if (isDirected()) {
            for (int key : successorData.keySet()) {
                for (int value : successorData.get(key)) {
                    int[] current = {key, value};
                    connectorData.add(current);
                }
            }
        } else {
            for (int key : successorData.keySet()) {
                for (int value : successorData.get(key)) {
                    int[] current = {key, value};
                    boolean addTo = true;
                    for (int[] tup : connectorData) {
                        int[] temp = {tup[1], tup[0]};
                        if (current[0] == temp[0] && current[1] == temp[1]) {
                            addTo = false;
                        }
                    }
                    if (addTo) {
                        connectorData.add(current);
                    }
                }
            }
        }
        return Iteration.iteration(connectorData);
    }

    @Override
    protected boolean mine(int v) {
        return nodes.contains(v);
    }

    @Override
    protected void checkMyNode(int v) {
        if (!mine(v)) {
            throw new NullPointerException("Given node does not exist.");
        }
    }

    @Override
    protected int connectorId(int u, int v) {
        if (!isDirected()) {
            int x = Math.max(u, v);
            int y = Math.min(u, v);
            return ((x + y) * (x + y + 1)) / 2 + y;
        }
        return ((u + v) * (u + v + 1)) / 2 + v;
    }

    /** RETURNS my successor data.. */
    HashMap<Integer, ArrayList<Integer>> getSuccessorData() {
        return successorData;
    }

    /** RETURNS my predecessor data.. */
    HashMap<Integer, ArrayList<Integer>> getPredecessorData() {
        return predecessorData;
    }

    /** RETURNS all vertices of the graph. */
    ArrayList<Integer> getNodes() {
        return nodes;
    }

    /** A specific form that saves an information about the
     * graph node and its neighbors. */
    private HashMap<Integer, ArrayList<Integer>> successorData;

    /** Saves information about predecessors of the node. */
    private HashMap<Integer, ArrayList<Integer>> predecessorData;

    /** A form that saves all vertices of the graph. */
    private ArrayList<Integer> nodes;

    /** Number of connectors in the graph. Initially 0 when graph is
     * empty. */
    private int numConnectors = 0;
}