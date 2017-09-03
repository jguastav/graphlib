
package org.techstartingpoint.javagraphlib.graph;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.api.GraphAPIService;
import sun.security.provider.certpath.Vertex;

import java.util.*;


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

    private static final Logger logger = LoggerFactory.getLogger(ExecutorModel.class.getName());

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

        int nodeElements = this.getExecutors().size();
        Map<String,Integer> nodes=new HashMap<String,Integer>();
        int i=0;
        for (Map.Entry<String,AbstractMainExecutor> mapEntry:this.getExecutors().entrySet()) {
            nodes.put(mapEntry.getKey(),i);
            i++;
        }

        Set<Edge> edgeSet =new HashSet<Edge>();
        for (GraphConnection connector:this.getConnectors()) {
            Integer from=nodes.get(connector.getSourceId());
            Integer to=nodes.get(connector.getTargetId());
            if (from!=null && to!=null) {
                edgeSet.add(new Edge(from,to));
            }
        }





        DirectedGraph directedGraph=convert(nodeElements,edgeSet);
        if (checkCycle(directedGraph)) {
            logger.error(WorkflowErrorCode.GraphHasCycle.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.GraphHasCycle);
        }

        if (!checkWeaklyConnected(new ArrayList<Edge> (edgeSet))) {
            logger.error(WorkflowErrorCode.UnconnectedNodes.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.UnconnectedNodes);
        }

    }


    private boolean checkWeaklyConnected(List<Edge> edgeList) {
        boolean result=true;
        int totalElements=this.getExecutors().size();
        if (totalElements>1) {
            result=false;
            List<Integer> connectedElements=new ArrayList<Integer>();
            if (edgeList.size()>0) {
                Set<Edge> edgeSet=new HashSet<Edge>(edgeList);

                Edge edge=edgeList.get(0);

                // add connected nodes
                connectedElements.add(edge.getFrom());
                if (!connectedElements.contains(edge.getTo())) {
                    connectedElements.add(edge.getTo());
                }
                edgeSet.remove(edge);
                boolean continueScanning=true;
                while (connectedElements.size()<totalElements &&  continueScanning) {
                    continueScanning=false;
                    List<Edge> checkingEdgesList=new ArrayList<Edge>(edgeSet);
                    for (Edge thisEdge:checkingEdgesList) {
                        if (connectedElements.contains(thisEdge.getFrom())) {
                            if (!connectedElements.contains(thisEdge.getTo())) {
                                connectedElements.add(thisEdge.getTo());
                            }
                            edgeSet.remove(thisEdge);
                            continueScanning=true;
                        }
                        if (connectedElements.contains(thisEdge.getTo())) {
                            if (!connectedElements.contains(thisEdge.getFrom())) {
                                connectedElements.add(thisEdge.getFrom());
                            }
                            edgeSet.remove(thisEdge);
                            continueScanning=true;
                        }
                    }
                }
                if (connectedElements.size()==totalElements) {
                    result=true;
                    for (Integer value:connectedElements) {
                        if (value>=totalElements || value<0) {
                            result=false;
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean checkCycle(DirectedGraph directedGraph) {
        DirectedCycle finder=new DirectedCycle(directedGraph);
        return finder.hasCycle();
    }


    private DirectedGraph convert(int nodeElements,Set<Edge> edgeSet) {

        DirectedGraph directedGraph=new DirectedGraph(nodeElements, edgeSet.size(),new ArrayList<Edge>(edgeSet));

        return directedGraph;

    }
}
