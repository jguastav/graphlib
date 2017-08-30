

package org.techstartingpoint.javagraphlib.components.core;

/**
 * Implementation information related to an executor
 * @author Jose Alberto Guastavino
 *
 */
public class ComponentImplementation {
	

	// executor implementation properties
	private String className;

	
	
	public ComponentImplementation(String className){
		this.className=className;
	}
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	
}
