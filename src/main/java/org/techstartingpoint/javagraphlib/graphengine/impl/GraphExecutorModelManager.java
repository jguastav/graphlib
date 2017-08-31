package org.techstartingpoint.javagraphlib.graphengine.impl;

import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.model.*;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;


/**
 * Class for manage the 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphExecutorModelManager {
	

	
	/**
	 * Instantiate initially all akka excomponents that will participate in the activity execution
	 * It generates a flat graph that includes all subflows
	 * @param graphNodes
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 
	 * @throws MalformedURLException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws URISyntaxException 
	 * @author Jose Alberto Guastavino

	 * 
	 * 
	 */
	public static GraphExecutionModel generateExecutionModel(
			GraphExecutionModel executionModel,
			List<GraphNode> graphNodes,
			List<GraphConnection> graphConnections,
			GraphCompleteEnvironment environment)
					throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, NoSuchMethodException, InvocationTargetException, URISyntaxException {
		GraphExecutionModel result=executionModel;
		for (GraphNode graphNode : graphNodes) {
			try {
						AbstractMainExecutor executorElement =buildComponentExecutor(graphNode,environment);
						result.getExecutors().put(executorElement.getNodeId().toString(),executorElement);
						environment.showMessage(graphNode.getId(), "added class "+executorElement.getComponentClassName());
			} catch (ClassNotFoundException e) {
				environment.getOutput().sendMessage( graphNode.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (InstantiationException e) {
				environment.showMessage( graphNode.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (IllegalAccessException e) {
				environment.showMessage( graphNode.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (SecurityException e) {
				environment.showMessage( graphNode.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (IllegalArgumentException e) {
				environment.showMessage( graphNode.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}
		result.getConnectors().addAll(generateExecutionConnectors(graphConnections));
		result=rebuildConnectors(result);
		return result;
	}
	

	private static AbstractMainExecutor buildComponentExecutor(GraphNode graphNode,  GraphCompleteEnvironment environment) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = graphNode.getType().getClassName();
		Class<?> clazz;
		clazz = Class.forName(className);
		AbstractMainExecutor executorElement =(AbstractMainExecutor) clazz.newInstance();
		executorElement.setNodeId(graphNode.getId().toString());
		executorElement.setGraphEnvironment(environment);
		executorElement.setEnvironmentKey(graphNode.getEnvironmentKey());
		executorElement.setNodeConfiguration(graphNode.getNodeConfiguration());
		return executorElement;
	}
	
	


	/** 
	 * Generate execution connectors with ids based on execution environment (taking into account prefixes for subflows element identifiers)
	 * @param connectors
	 * @return
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static List<GraphExecutionConnection> generateExecutionConnectors(List<GraphConnection> connectors) {
		List<GraphExecutionConnection> result =new ArrayList<GraphExecutionConnection>();
		for (GraphConnection connector:connectors) {
			result.add(new GraphExecutionConnection(connector));
		}
		return result;
	}

	/**
	 * Make connectors point to and from node elements (not subflow elements)
	 * @param executionModel
	 * @return
	 * 
	 *  @author Jose Alberto Guastavino
	 */
	private static GraphExecutionModel rebuildConnectors(GraphExecutionModel executionModel) {
		GraphExecutionModel result=executionModel;
		// normalize initial map
		result.setInitialNodes(normalizeTranslation(executionModel.getInitialNodes()));
		result.setFinalNodes(normalizeTranslation(executionModel.getFinalNodes()));
		Map<String,String> initialNodesMap=result.getInitialNodes();
		Map<String,String> finalNodesMap=result.getFinalNodes();
		// replace connectors for deepest nodes
		for (GraphExecutionConnection connector:result.getConnectors()) {
			if (connector.getSourceIndex()>=0) {
				if (finalNodesMap.containsKey(connector.getSourceExecutionId())) {
					connector.setSourceExecutionId(finalNodesMap.get(connector.getSourceExecutionId()));
				}
			} else { // is async connector == -1
				if (initialNodesMap.containsKey(connector.getSourceExecutionId())) {
					connector.setSourceExecutionId(initialNodesMap.get(connector.getSourceExecutionId()));
				}
			}
			if (initialNodesMap.containsKey(connector.getTargetExecutionId())) {
				connector.setTargetExecutionId(initialNodesMap.get(connector.getTargetExecutionId()));
			}
		}
		return result;
	}
	
	



	
	/**
	 * Detects one element that is implemented using the given className
	 *  
	 * @param activity
	 * @param className
	 * @return
	 * 
	 * 	Returns the id of the element or null if it is not found
 	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static String findClassNode(GraphProcess activity, String className) {
		String nodeId=null;
		for (GraphNode graphNode :activity.getNodeList()) {
			if (graphNode.getType().getClassName().equals(className)) {
				nodeId= graphNode.getId();
			}
		}
		return nodeId;
	}

	
	/**
	 * Get a set of ids of nodes having no predecessors
	 * @param activity
	 * @return
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static Map<String,GraphNode> getNoPredecessorNodes(GraphProcess activity) {
		Map<String,GraphNode> noPredecessors=new HashMap<String,GraphNode>();
		for (GraphNode element:activity.getNodeList()) {
			noPredecessors.put(element.getId(),element);
		}
		for (GraphConnection connector:activity.getConnectorList())  {
			if (noPredecessors.containsKey(connector.getTargetId())) {
				noPredecessors.remove(connector.getTargetId());
			}
		}
		return noPredecessors;
	}
	
	
	
	


	
	/**
	 * Refcomponent the translation maps of nodes to subflow nodes to deepest node in each case
	 * @param translation
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static Map<String, String> normalizeTranslation(Map<String, String> translation) {
		
		for (Map.Entry<String, String> entry:translation.entrySet()) {
			String deepestString=getDeepest(translation,entry.getValue());
		}
		return translation;
	}

	private static String getDeepest(Map<String, String> translation, String value) {
		String result=value;
		if (translation.containsKey(value)) {
			result=getDeepest(translation,translation.get(value));
		}
		return result;
	}

	
	/**
	 * Add translation keys to map links to and from nodes on subflows to the start and final node of each subflow
	 * 
	 * 
	 * @param subFlowModel
	 * @param initialNode
	 * @param finalNode
	 * @param prefix
	 * 		identifier of the parent node . should never be empty
	 * @return
	 */
	
	private static GraphExecutionModel addNodeTranslationItems(GraphExecutionModel subFlowModel, Long initialNode,
															   Long finalNode, String prefix) {
		assert prefix!=null && prefix.length()>1;
		GraphExecutionModel changedModel=subFlowModel;
		// Gets the prefix with no end point - It's the subFlow id
		String nodeReferenceId=prefix.substring(0,prefix.length()-1);
		changedModel.getInitialNodes().put(nodeReferenceId, prefix+initialNode);
		changedModel.getFinalNodes().put(nodeReferenceId,prefix+finalNode);
		return changedModel;
	}



	/**
	 * 
	 * @param activity
	 * @return
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static Map<SimpleEntry<String, Integer>, GraphNode> getNoDirectionNodes(GraphProcess activity) {
		// populate map
		Map<SimpleEntry<String,Integer>,GraphNode> freeOutputConnections=new HashMap<SimpleEntry<String, Integer>,GraphNode>();
		for (GraphNode element:activity.getNodeList()) {
			for (int i=1;i<=element.getOutputPorts();i++) {
				freeOutputConnections.put(new SimpleEntry<String, Integer>(element.getId(), new Integer(i)),element);
			}
		}
		for (GraphConnection connector:activity.getConnectorList()) {
			SimpleEntry<String, Integer> newKey=new SimpleEntry<String,Integer>(connector.getSourceElement().getId(),new Integer(connector.getSourceIndex()));
			if (freeOutputConnections.containsKey(newKey)) {
				freeOutputConnections.remove(newKey);
			}
		}
		return freeOutputConnections;
	}

	
	/**
	 * Add connections from Start node to all node that does not have predecessors
	 * 
	 * 
	 * 
	 * @param activity
	 * The activity
	 * @param initialGraphNode
	 * The Start Node
	 * @param noPredecessorNodes
	 * List of no predecessor nodes
	 * 
	 * @return
	 * 	The changed activity
	 * 
	 * 
	 * author Jose Alberto Guastavino
	 */
	private static GraphProcess addDummyConnectorsFromStart(
			GraphProcess activity,
			GraphNode initialGraphNode,
			Map<Long,GraphNode> noPredecessorNodes) {
		GraphProcess activityWithNewConnectors=activity;
		for (Map.Entry<Long, GraphNode> elementEntry:noPredecessorNodes.entrySet()) {
			activityWithNewConnectors.getConnectorList().add(
					new GraphConnection(
							new Long(activity.getConnectorList().size()+1),
							initialGraphNode,1,
							elementEntry.getValue(),1));
		}
		return activityWithNewConnectors;
	}

	
	/**
     *
	 * 
	 * Add connections from nodes / connectors with no target to Final node 
	 * 
	 * @param activity
	 * @param finalGraphNode
	 * @param noDirectionNodes
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private static GraphProcess addDummyConnectorsToFinal(GraphProcess activity, GraphNode finalGraphNode,
														  Map<SimpleEntry<Long, Integer>, GraphNode> noDirectionNodes) {
		GraphProcess changedActivity=activity;
		for (Map.Entry<SimpleEntry<Long, Integer>, GraphNode> source:noDirectionNodes.entrySet()) {
			changedActivity.getConnectorList().add(new GraphConnection(
						new Long(activity.getConnectorList().size()+1),
						source.getValue(),source.getKey().getValue(),
					finalGraphNode,1
					));
		}
		return changedActivity;
	}
	
	
	
	
	
}
