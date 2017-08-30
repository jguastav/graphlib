

package org.techstartingpoint.javagraphlib.graphengine.impl;


import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.graphengine.model.StdOutput;
import org.techstartingpoint.javagraphlib.model.GraphLog;

/**
 * Environment Accessible for the runner.
 * This environment can have any shared resources among executions and excomponents.
 * This environment has initially an output where all excomponents can send messages
 * It could be extended
 * 
 * 
 * @author  Jose Alberto Guastavino
 *
 */
public class GraphEnvironmentImpl implements GraphCompleteEnvironment {
	

	private StdOutput output=new StdOutput();
	private StdOutput stdError=new StdOutput();
	
	private GraphLog log;



	/**
	 * valid status : INACTIVE / RUNNING
	 */
	private String status=INACTIVE_STATUS;
	
	public void showMessage(String fluxId,String message) {
		this.getOutput().sendMessage(fluxId, message);
	}	

	public StdOutput getOutput() {
		return output;
	}

	public void setOutput(StdOutput output) {
		this.output = output;
	}

	public GraphLog getLog() {
		return log;
	}

	public void setLog(GraphLog log) {
		this.log = log;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StdOutput getStdError() {
		return stdError;
	}

	public void setStdError(StdOutput stdError) {
		this.stdError = stdError;
	}


	
	
}
