
package org.techstartingpoint.javagraphlib.graphengine.impl;

import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.model.GraphProcess;
import org.techstartingpoint.javagraphlib.model.GraphProcessSet;
import org.techstartingpoint.javagraphlib.services.GraphProcessSetService;

import java.io.IOException;
import java.net.URISyntaxException;
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
	public void run(String workflowFileName) throws IOException, URISyntaxException {
		GraphRunnerImpl runner=new GraphRunnerImpl(environment,graphProcessService,workflowFileName,this);
		runner.run();
	}
	
	

	
}
