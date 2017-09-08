package com.onelake.workflowexecutor.graph;

import com.onelake.workflowexecutor.collections.DirectedCycle;
import com.onelake.workflowexecutor.collections.DirectedGraph;

public class DirectedCycleTest {
    DirectedGraph G = new DirectedGraph(0,0,null);



    public void testCycle() {
        DirectedCycle finder = new DirectedCycle(G);

        if (finder.hasCycle()) {
            System.out.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("No directed cycle");
        }
        System.out.println();
    }




}
