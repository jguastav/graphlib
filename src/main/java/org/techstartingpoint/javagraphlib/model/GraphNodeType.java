
package org.techstartingpoint.javagraphlib.model;

import org.techstartingpoint.javagraphlib.components.core.AbstractMainBaseComponent;
import org.techstartingpoint.javagraphlib.components.util.PluginLoader;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


/**
 * 
 * Definition of elements
 * The definition defines the features an element has
 * Ths is the representation of the tool to be persisted and interact with fronted
 * The real implementation will be made with a pair of classes that extend from AbstractMainBaseComponent and AbstractGraphLibComponent
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphNodeType {

	

	private String id;

	private String name;

	

	/**
	 * Category name to group tools
	 */
	private String category;
	
	
	// node implementation
	private String className;
	


	private Integer inputPorts;
	/**
	 * number of output data interfaces
	 * this number does not include the async interface
	 */
	private Integer outputPorts;


	public GraphNodeType() {}
	
	
	public GraphNodeType( String className)
			throws MalformedURLException, 
				ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				URISyntaxException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		this((AbstractMainBaseComponent) PluginLoader.getInstance(className));

			this.setClassName(className);
			
	}


	public GraphNodeType(AbstractMainBaseComponent tool) {
		this.name=tool.getName();
		this.inputPorts=tool.getInputPorts();
		this.outputPorts=tool.getOutputPorts();
		this.className=tool.getComponentImplementation().getClassName();
	}

	
	/**
	 * update tool information
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws URISyntaxException
	 */
	public void update() throws 
			MalformedURLException, 
			ClassNotFoundException, 
			InstantiationException, 
			IllegalAccessException, 
			NoSuchMethodException, 
			SecurityException, 
			IllegalArgumentException, 
			InvocationTargetException, 
			URISyntaxException {
		GraphNodeType newInstance=new GraphNodeType(this.className);
		this.name=newInstance.getName();
		this.inputPorts=newInstance.getInputPorts();
		this.outputPorts=newInstance.getOutputPorts();
	}
	
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getInputPorts() {
		return inputPorts;
	}

	public void setInputPorts(Integer inputPorts) {
		this.inputPorts = inputPorts;
	}

	public Integer getOutputPorts() {
		return outputPorts;
	}

	public void setOutputPorts(Integer outputPorts) {
		this.outputPorts = outputPorts;
	}


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}






	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}



	
	

}
