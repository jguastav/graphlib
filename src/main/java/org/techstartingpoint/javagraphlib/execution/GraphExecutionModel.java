
package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.execution.GraphExecutionConnection;

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
	List<GraphExecutionConnection> connectors= new ArrayList<GraphExecutionConnection>();
	/**
	 * ids containing entry nodes for subfluxes
	 */
	Map<String,String> initialNodes=new HashMap<String,String>();
	/**
	 * ids containing entry nodes for subfluxes
	 */
	Map<String,String> finalNodes=new HashMap<String,String>();
	
	
	
	/**
	 * Components of the activity
	 */
	public Map<String, AbstractMainExecutor> getExecutors() {
		return executors;
	}
	public void setExecutors(Map<String, AbstractMainExecutor> executors) {
		this.executors = executors;
	}
	public List<GraphExecutionConnection> getConnectors() {
		return connectors;
	}
	public void setConnectors(List<GraphExecutionConnection> connectors) {
		this.connectors = connectors;
	}
	@Override
	public String toString() {
		return String.format("GraphExecutionModel [\n\texecutors=%s, \n\tconnectors=%s]", executors, connectors);
	}
	public Map<String, String> getInitialNodes() {
		return initialNodes;
	}
	public void setInitialNodes(Map<String, String> initialNodes) {
		this.initialNodes = initialNodes;
	}
	public Map<String, String> getFinalNodes() {
		return finalNodes;
	}
	public void setFinalNodes(Map<String, String> finalNodes) {
		this.finalNodes = finalNodes;
	}
	
	
	
	

}
