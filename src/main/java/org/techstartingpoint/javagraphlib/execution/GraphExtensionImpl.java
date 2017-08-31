
package org.techstartingpoint.javagraphlib.execution;


import org.techstartingpoint.javagraphlib.excomponents.Props;
import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.execution.GraphEnvironment;
import org.techstartingpoint.javagraphlib.execution.GraphExtension;
import org.techstartingpoint.javagraphlib.execution.GraphRunner;

import java.net.URLClassLoader;

/**
 * Helper class to create akka excomponents based on the name
 * To work classes must be loaded in classloader first. This initializtion is made based on plugins
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphExtensionImpl implements GraphExtension {

    public Props props(String componentClassName, String nodeId, GraphEnvironment graphEnvironment, GraphRunner graphRunner) throws ClassNotFoundException {
    		
	    final URLClassLoader sysloader = (URLClassLoader) AbstractMainExecutor.class.getClassLoader();
		Class classToLoad = Class.forName (componentClassName, true, sysloader);
    	
        return Props.create(classToLoad,nodeId, graphEnvironment, graphRunner);
    }

}