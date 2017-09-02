package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graph.GraphConnection;
import org.techstartingpoint.javagraphlib.graph.GraphNode;

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




	
	
}
