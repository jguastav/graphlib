package org.techstartingpoint.javagraphlib.graphengine;


import org.techstartingpoint.javagraphlib.graphengine.model.StdOutput;

/**
 * Interface defined to access the shared resources of the running environment that excomponents (elements) can use
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphEnvironment {
	
	StdOutput getOutput();
	void showMessage(String fluxId, String message);


	
	
}
