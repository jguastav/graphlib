
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

	/**
	 * Stablish the baseDir where plugins are uploaded
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	public static String getUploadBaseDir() {
		String uploadBaseDir=getBaseDir()+"uploads/";
        if(! new File(uploadBaseDir).exists())
        {
            new File(uploadBaseDir).mkdir();
        }
		return uploadBaseDir;
	}
	
	
	/**
	 * Gets the dir parent of upload folder
	 * 
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	public static String getBaseDir() {
        final String dir = System.getProperty("user.dir");
        File file1=new File(dir);
        String baseDir=file1.getParent()+"/";
        return baseDir;
	}
	

	/**
	 * Return the list of executors on a Jar that previously exists in current ClassLoader
	 * @throws IOException 
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	public static List<String> checkExecutorsList(String fileName) throws IOException  {
		List<String> totalClassNameList=getClassNameList(fileName);
		List<String> existentClasses=new ArrayList<String>();
		URLClassLoader classLoader=(URLClassLoader) AbstractMainBaseComponent.class.getClassLoader();
		for (String className:totalClassNameList) {
			 Class<?> beanClass;
			try {
					System.out.println("Loading class: "+className);
					beanClass = classLoader.loadClass(className);
					 if (beanClass!=null) {
							if (AbstractMainExecutor.class.isAssignableFrom(beanClass) || AbstractGraphLibComponent.class.isAssignableFrom(beanClass)) {
								existentClasses.add(className);
							}
					 }
			} catch (ClassNotFoundException e) {
				// No problem. is a new class
			}
		}
		return existentClasses;
	}
	
	
	/**
	 * Identifies all the executors present in a JAR that are already present in classloader
	 * @param classLoader
	 * @param classNames
	 * @return
	 * @throws ClassNotFoundException
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	
	private static List<String> getExecutorsList(URLClassLoader classLoader,List<String> classNames) throws ClassNotFoundException {
		List<String> result=new ArrayList<String>();
		for (String className:classNames) {
			System.out.println("getExecutorList(..."+className+"...)");
				 Class<?> beanClass = classLoader.loadClass(className);
				 if (AbstractMainExecutor.class.isAssignableFrom(beanClass)  && !beanClass.getName().equals(AbstractMainExecutor.class.getName())) {
					 result.add(className);
				 }
		}
		return result;
	}



	/**
	 * Get list of classes present in a jar file
	 * @param completeFileName
	 * @return
	 * @throws IOException
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	private static List<String> getClassNameList(String completeFileName) throws IOException {
		List<String> classNames = new ArrayList<String>();
		ZipInputStream zip = new ZipInputStream(new FileInputStream(completeFileName));
		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
		    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
		        // This ZipEntry represents a class. Now, what class does it represent?
		        String className = entry.getName().replace('/', '.'); // including ".class"
		        classNames.add(className.substring(0, className.length() - ".class".length()));
		    }
		}	
		return classNames;
	}



}
