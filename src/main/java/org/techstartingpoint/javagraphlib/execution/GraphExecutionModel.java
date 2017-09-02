
package org.techstartingpoint.javagraphlib.execution;

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

public class GraphExecutionModel {
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
		return String.format("GraphExecutionModel [\n\texecutors=%s, \n\tconnectors=%s]", executors, connectors);
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
}
