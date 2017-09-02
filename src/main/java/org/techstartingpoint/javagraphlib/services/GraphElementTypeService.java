
package org.techstartingpoint.javagraphlib.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.graph.GraphNodeType;


/**
 * Related tool operations
 * @author Jose Alberto Guastavino
 *
 */
public class GraphElementTypeService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());


	private GraphPluginService pluginService;
	




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

	

}
