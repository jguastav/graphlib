
package org.techstartingpoint.javagraphlib.graph;

import com.onelake.api.error.OnelakeException;
import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graph.GraphConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Nodes and connectors arranged for an running environment. 
 * All the nodes are present in a map and all connectors in a list
 * All nodes referres to all nodes in all levels of main flows and subflows
 * 
 * @author Jose Alberto Guastavino
 * 
 *
 */

public class ExecutorModel {
	Map<String,AbstractMainExecutor> executors=new HashMap<String,AbstractMainExecutor>();
	List<GraphConnection> connectors= new ArrayList<GraphConnection>();

	
	/**
	 * Components of the activity
	 */
	public Map<String, AbstractMainExecutor> getExecutors() {
		return executors;
	}
	public List<GraphConnection> getConnectors() {
		return connectors;
	}

	@Override
	public String toString() {
		return String.format("ExecutorModel [\n\texecutors=%s, \n\tconnectors=%s]", executors, connectors);
	}


    /**
     * Get the list of not finished nodes yet during execution
     * @return
     */
    public List<AbstractMainExecutor> getPendingNodes() {
		List<AbstractMainExecutor> executorList=new ArrayList<AbstractMainExecutor>();
		for (Map.Entry<String, AbstractMainExecutor> executorEntry:getExecutors().entrySet()) {
			if (!executorEntry.getValue().isFinished()) {
				executorList.add(executorEntry.getValue());
			}
		}
		return executorList;
    }

    /**
     * Make checkings to ensure the Graph is correct
     *
     *  No cycle
     *  All inputs and output ports are connected
     *  There are not connections to orphan ports
     *  There are not start nodes
     *  there are no finish nodes
     *  check there are no inputs that receives more than one connector
     *
     *  http://algs4.cs.princeton.edu/42digraph/DirectedCycle.java.html
     * http://www.sanfoundry.com/java-programming-examples-graph-problems-algorithms/
     * check connectors when running
     *
     * @throws OnelakeException
     */
    public void validate() throws OnelakeException {
    }


    public boolean checkCycle() {
        return false;
    }
}
