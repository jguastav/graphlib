
package org.techstartingpoint.javagraphlib.graphengine.impl;


import org.techstartingpoint.javagraphlib.excomponents.Props;
import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graphengine.GraphEnvironment;
import org.techstartingpoint.javagraphlib.graphengine.GraphExtension;
import org.techstartingpoint.javagraphlib.graphengine.GraphRunner;

import java.net.URLClassLoader;
import java.util.Map;

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