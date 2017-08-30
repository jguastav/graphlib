
package org.techstartingpoint.javagraphlib.graphengine.impl;

import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that allows register the state of running processes
 * All excomponents notifies start and finish of execution to this class.
 * In each moment this class has the current ids and quantity of running processes
 * A flux (or activity) is considered to be finish if the count of processes registered in this class is empty
 * @author Jose Alberto Guastavino
 *
 */
public class ExecutionEnvironment {

	Map<String,Integer> executing;
	
	public ExecutionEnvironment() {
		this.executing=new HashMap<String,Integer>();
	}
	
	/**
	 * Flow is considered finished if there is no running processes
	 * This method shoud be consulted only after a flow has started
	 * @return true if has finished (there are no running processes) / false if there are running processes waiting to finish
	 */
	public boolean hasFinished() {
		if  (this.executing.size()>0) {
			System.out.println("Executing --------------------------------------------------------------------------------------------------------");
			System.out.println(this.executing.toString());
		}
		return this.executing.size()==0; 
	}

	/**
	 * Adds information of the running process.
	 * It should be called each time an component starts to run
	 * @param executor
	 */
	public void run(AbstractMainExecutor executor) {
		Integer executingProcesses=executing.get(executor.getNodeId());
		if (executingProcesses!=null) {
			executing.put(executor.getNodeId(),executingProcesses+1);
		} else {
			executing.put(executor.getNodeId(),1);
		}
		System.out.println("Run --------------------------------------------------------------------------------------------------------");
		System.out.println(this.executing.toString());
	}

	/**
	 * Notifies the end of a running process
	 * Each time a running process finish the quantity of running processes is decreased and it is removed if the quantity of processes reaches 0 
	 * @param fluxId
	 */
	public void finish(String fluxId) {
		Integer executions=this.executing.get(fluxId);
		if (executions!=null) {
			executions--;
			if (executions<=0) {
				this.executing.remove(fluxId);
			} else {
				this.executing.put(fluxId, executions);
			}
		}
		System.out.println("Finishing --------------------------------------------------------------------------------------------------------");
		System.out.println(this.executing.toString());
		
	}
	
	
}
