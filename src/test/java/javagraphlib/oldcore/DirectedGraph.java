package javagraphlib.oldcore;

import javagraphlib.oldcore.deps.Iteration;

import java.util.ArrayList;

public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        /*
        for (int key : getSuccessorData().keySet()) {
            if (getSuccessorData().get(key).contains(v)) {
                count++;
            }
        }
        */
        return count;
    }


    @Override
    public int predecessor(int v, int k) {
        if (!contains(v)) {
            return 0;
        }
        /*
        if (k >= getPredecessorData().get(v).size() || k < 0) {
            return 0;
        }
        return getPredecessorData().get(v).get(k);
        */
        return 0;
    }


    @Override
    public Iteration<Integer> predecessors(int v) {
        if (!contains(v)) {
            return Iteration.iteration(new ArrayList<Integer>());
        } else {
            return null;
            /*
            return org.techstartingpoint.javagraphlib.oldcore.deps.Iteration.iteration(getPredecessorData().get(v));
            */
        }
    }

    @Override
    public int maxVertex() {
        return 0;
    }

    public int vertexSize() {
        return 0;
    }

    public int edgeSize() {
        return 0;
    }
}

