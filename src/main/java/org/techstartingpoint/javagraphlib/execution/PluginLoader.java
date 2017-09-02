
package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.api.AbstractGraphLibComponent;
import org.techstartingpoint.javagraphlib.api.AbstractMainBaseComponent;
import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.model.workflow.NodeConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



/**
 * 
 * 
 * Operations related to files / plugins and classloader
 * It could be replaced by an OSGI library like Apache Felix
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class PluginLoader {
	


	/**
	 * Get an instance of a class provided by a plugin
	 * @param className
	 * @param environmentKey
     * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws URISyntaxException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	public static AbstractMainBaseComponent getInstance(String className,
                                                        String environmentKey,
                                                        NodeConfiguration nodeConfiguration)
            throws MalformedURLException,
                ClassNotFoundException,
                InstantiationException,
                IllegalAccessException,
                URISyntaxException,
                NoSuchMethodException,
                SecurityException,
                IllegalArgumentException,
                InvocationTargetException {
	    final URLClassLoader sysloader = (URLClassLoader) AbstractMainExecutor.class.getClassLoader();
		Class classToLoad = Class.forName (className, true, sysloader);
        AbstractMainBaseComponent instance = (AbstractMainBaseComponent) classToLoad.newInstance ();
        instance.setNodeConfiguration(nodeConfiguration);
        instance.setEnvironmentKey(environmentKey);
		return  instance;
		
	}



}
