package org.techstartingpoint.javagraphlib.core;

import java.util.ArrayList;

public class DirectedGraph extends org.techstartingpoint.javagraphlib.core.GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        for (int key : getSuccessorData().keySet()) {
            if (getSuccessorData().get(key).contains(v)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int predecessor(int v, int k) {
        if (!contains(v)) {
            return 0;
        }
        if (k >= getPredecessorData().get(v).size() || k < 0) {
            return 0;
        }
        return getPredecessorData().get(v).get(k);
    }

    @Override
    public org.techstartingpoint.javagraphlib.core.Iteration<Integer> predecessors(int v) {
        if (!contains(v)) {
            return org.techstartingpoint.javagraphlib.core.Iteration.iteration(new ArrayList<Integer>());
        } else {
            return org.techstartingpoint.javagraphlib.core.Iteration.iteration(getPredecessorData().get(v));
        }
    }
}

