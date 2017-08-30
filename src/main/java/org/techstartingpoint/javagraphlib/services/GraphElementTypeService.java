
package org.techstartingpoint.javagraphlib.services;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.dao.GraphElementTypeRepository;
import org.techstartingpoint.javagraphlib.model.*;


/**
 * Related tool operations
 * @author Jose Alberto Guastavino
 *
 */
public class GraphElementTypeService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private GraphElementTypeRepository repository;

	private GraphPluginService pluginService;
	


	/**
	 * Persist a tool
	 * @param elementType
	 * @param isNew
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 */
    public GraphNodeType save(GraphNodeType elementType, boolean isNew) {
    	if (isNew) {
    		elementType.setId(null);
    	}
    	GraphNodeType result = repository.save(elementType);
		return result;
    }
	
    
    /**
     * Get tool by its unique name
     * @param name
     * @return
     * 
     * @author Jose Alberto Guastavino
     */
    public GraphNodeType getByName(String name) {
    	GraphNodeType flowElementBase=new GraphNodeType();
    	flowElementBase.setName(name);
		return null;
    }	
	
    
    /**
     * Get all available tools in repository
     * @return
     * 
     * 
     * @author Jose Alberto Guastavino
     */
	public List<GraphNodeType> findAll() {
		return null;
	}


	/**
	 * Erase a jar and deactivate it for next restart
	 * @param jarLocation
	 * @param jarName
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public List<String> remove(String jarLocation, String jarName) {
		List<String> removedClasses=new ArrayList<String>();
		return removedClasses;
	}

	
	/**
	 * Get all Subflows declared in Jobs and transform to Tools
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private List<GraphNodeType> getGlobalSubflows() {
		return null;
	}
	
	

	/**
	 * 
	 * Persist a new list of tools on repository removing the old one
	 * 
	 * @param previousToolList
	 * @param toolList
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 */
	private void updateGlobalSubFlowsTools(List<GraphNodeType> previousToolList,
			List<GraphNodeType> toolList) {
		Map<String,GraphNodeType> toolMap=new HashMap<String,GraphNodeType>();
		for (GraphNodeType tool:toolList) {
			toolMap.put(tool.getName(), tool);
		}
		// remove or update tools in database
		for (GraphNodeType tool:previousToolList) {
			GraphNodeType newTool=toolMap.get(tool.getName());
			if (newTool==null) {
			} else {
				newTool.setId(tool.getId());
				repository.save(newTool);
				toolMap.remove(tool.getName());
			}
		}
		// In map remains only the new Global tools
		toolMap.forEach((i,t)->{
			repository.save(t);
		});
		
	}
	
	/**
	 * Update all global subflows synchronizing tools information for a specified job
	 * @param job
	 * @param previousName
	 */
	public void updateGlobalSubFlows(GraphProcessSet job, String previousName) {
		List<GraphNodeType> previousTools=findByJobName(previousName);
	}


	

	public List<GraphNodeType> findByJobName(String jobName) {
		return repository.findByJobName(jobName);
	}

}
