
package org.techstartingpoint.javagraphlib.graphengine.impl;

import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.graphengine.model.StdOutput;
import org.techstartingpoint.javagraphlib.model.GraphProcess;
import org.techstartingpoint.javagraphlib.model.GraphProcessSet;
import org.techstartingpoint.javagraphlib.services.GraphProcessSetService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Main execution context that manages running processes
 * Starts / Stops and manage running flows
 *
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerEnvironmentImpl {
	
	
	GraphProcessSetService graphProcessService;
	
	GraphCompleteEnvironment environment;

	
	private Map<GraphProcessKey,GraphRunnerImpl> runners=new HashMap<GraphProcessKey,GraphRunnerImpl>();

	
	/**
	 * Runs an activity
	 * @param workflowFileName
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public void run(String workflowFileName) throws IOException, URISyntaxException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        environment=new GraphEnvironmentImpl();
		GraphRunnerImpl runner=new GraphRunnerImpl(environment,new GraphProcessSetService(),workflowFileName,this);
		runner.run();
	}
	
	

	
}
