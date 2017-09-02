package org.techstartingpoint.javagraphlib.api;

import org.techstartingpoint.javagraphlib.execution.GraphEnvironment;
import org.techstartingpoint.javagraphlib.execution.GraphRunner;


/**
 * Base class to be extended to build component
 * The main method component sould implement is runMain(....)
 * 
 * @author Jose Alberto Guastavino
 *
 */
public abstract class AbstractGraphLibComponent extends AbstractMainBaseComponent {
	
	
	// Global Elements
	private GraphEnvironment environment;
	private GraphRunner runner;
	private String nodeId;

	
	public AbstractGraphLibComponent(String nodeId,
									 GraphEnvironment graphEnvironment,
									 GraphRunner graphRunner) {
		this.nodeId = nodeId;
		this.runner= graphRunner;
		this.environment = graphEnvironment;
	}


	/**
	 * Main entry point on receiving a message from another component or starting it
	 *
	 * @author Jose Alberto Guastavino
	 *
	 */
	public void onReceive(Object message) throws Throwable {
		if (this.runner!=null) {
			this.runner.broadcast(this.nodeId, -1, message);
		}
		Long startedTime=System.currentTimeMillis();
		runCore(message);
		Long endTime=System.currentTimeMillis();
		Long duration=endTime-startedTime;
	}

	public void sendOutput(String message) {
		this.environment.showMessage(nodeId, message);
	}
	
	
	/**
	 * Method each plugin element component should implement
	 * 
	 * Inside elements can be calles
	 * 	setOutput(index,message): to set the message to be sent to another component
	 *  sendMessage(....) : to show information in the output console of the app
	 *  getEnvironment()... : to use shared resources of the execution runnning environment
	 *  getProperties() : to access customized data of the element setted in each instance
	 *  
	 * @param message
	 * @throws Exception
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	protected abstract void runCore(Object message) throws Exception;

	public void setOutput(int index, Object value) throws Exception {
		this.runner.broadcast(this.nodeId,index,value);
	}



	public String getNodeId() {
		return nodeId;
	}



	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public GraphEnvironment getFluxEnvironment() {
		return this.environment;

	}
	
	

}
