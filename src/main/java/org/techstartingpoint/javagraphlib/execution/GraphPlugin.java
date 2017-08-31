
package org.techstartingpoint.javagraphlib.execution;

/**
 * Bean to store plugin information 
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphPlugin {
	private String id;
	private String jarLocation;
	private String jarName;
	
	
	/**
	 * Indicates if the plugin is currengly active
	 * When a plugin is active the classes contained in the jar are loaded 
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	private Boolean activated;
	
	/**
	 * Dynamic activation indicates if a plugin can be activated in the current running app
	 * This property is setted to true when app is started for all inactive plugin-
	 * This property is setted to false only when a plugin is deactivated until the moment the app is restarted
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	private Boolean dynamicActivation;
	
	
	
	public GraphPlugin() {
		super();
	}
	public String getJarLocation() {
		return jarLocation;
	}
	public void setJarLocation(String jarLocation) {
		this.jarLocation = jarLocation;
	}
	public String getJarName() {
		return jarName;
	}
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public GraphPlugin(String jarLocation, String jarName) {
		super();
		this.jarLocation = jarLocation;
		this.jarName = jarName;
		this.activated= false;
		this.dynamicActivation=true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getDynamicActivation() {
		return dynamicActivation;
	}
	public void setDynamicActivation(Boolean dynamicActivation) {
		this.dynamicActivation = dynamicActivation;
	}

	
	
	
}
