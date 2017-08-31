
package org.techstartingpoint.javagraphlib.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.execution.GraphPlugin;
import org.techstartingpoint.javagraphlib.springmock.MultipartFile;

/**
 * Service to manage plugins.
 * Plugins contains the set of tools that will be available in aFlux
 * 
 * @author Jose Alberto Guastavino
 * 
 *
 *
 */
public class GraphPluginService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	

	private GraphElementTypeService toolService;

	/**
	 * Should fail on activate all the plugin if there is any class that already has a tool
	 * It loads using main Classloader all tools and excomponents present in each JAR specified in flowPlugin collection in database
	 * @param id
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InstantiationException
	 * 
	 *  @author Jose Alberto Guastavino
	 */
	public void activateOnInit(String id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, URISyntaxException, IOException, InstantiationException {
	}
	
	
	/**
	 * Activate a plugin
	 * 
	 * When a plugin is activated all related classes inside the jar are loaded in the classloader and all tools are available in the application
	 * 
	 * If there is in the jar a class that has be already present in classloader corresponding to a tool or an component the plugins is not activated
	 * Also by now cannot be activated a plugin deactivated in the current session
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InstantiationException
	 * 
	 *  @author Jose Alberto Guastavino
	 *  
	 *  
	 */
	public List<String> activate(String id) throws 
			NoSuchMethodException, 
			SecurityException, 
			IllegalAccessException, 
			IllegalArgumentException, 
			InvocationTargetException, 
			ClassNotFoundException, 
			URISyntaxException, 
			IOException, 
			InstantiationException {
		List<String> result=new ArrayList<String>();
		return result;
	}
	
	public List<GraphPlugin> findAll() {
		return null;
	}
	
	public void register(String location,String name) {
	}

	/**
	 * Deactivate plugin 
	 * It removes all related tools from app but does not remove from classloader.
	 * To remove classes from java classloader application should be restarted
	 * 
	 * A deactivated plugin only can be reactivated after reinit the application
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public List<String> deactivate(String id) {
		List<String> result=new ArrayList<String>();
		return result;
	}
	
	
	public void store(MultipartFile file) throws IllegalStateException, IOException {
	}


	/**
	 * Remove plugin and delete related file from filesystem
	 * @param id
	 * @return
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public List<String> remove(String id) {
		List<String> result = new ArrayList<String>();
		return result;
	}

	
	
}
