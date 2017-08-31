package org.techstartingpoint.javagraphlib.execution;


/**
 * Interface defined to access the shared resources of the running environment that excomponents (elements) can use
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphEnvironment {
	
	StdOutput getOutput();
	void showMessage(String fluxId, String message);


	
	
}
