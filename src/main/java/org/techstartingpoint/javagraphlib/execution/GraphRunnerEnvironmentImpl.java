
package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.services.GraphProcessSetService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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
	public void run(String workflowFileName) throws Throwable {
        environment=new GraphEnvironmentImpl();
		GraphRunnerImpl runner=new GraphRunnerImpl(environment,new GraphProcessSetService(),workflowFileName,this);
		runner.run();
	}
	
	

	
}
