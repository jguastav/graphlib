
package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.api.GraphAPIService;

/**
 * Main execution context that manages running processes
 * Starts / Stops and manage running flows
 *
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerEnvironmentImpl {
	
	

	/**
	 * Runs an activity
	 * @param workflowFileName
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public void run(String workflowFileName) throws Throwable {
		GraphRunnerImpl runner=new GraphRunnerImpl(new GraphAPIService(),workflowFileName,this);
		runner.run();
	}
	
	

	
}
