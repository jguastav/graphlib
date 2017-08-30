

package org.techstartingpoint.javagraphlib.graphengine.launcher;


import org.techstartingpoint.javagraphlib.graphengine.GraphEnvironment;

/**
 * 
 * Complete GraphEnvironment
 * 
 * Environment that contains all shared data between flows and executions
 * Differet from Flux Environment this interface is intended to have ALL shared resources
 * GraphEnvironment different from this should have only methods accesibles by component elements
 * Any method that is considered should be accessible by excomponents should be moved to FluxEnvironmnet
 * 
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphCompleteEnvironment extends GraphEnvironment {
	
	public final static String INACTIVE_STATUS="INACTIVE";
	public final static String RUNNING_STATUS="RUNNING";

	public String getStatus();
	public void setStatus(String status);


}
