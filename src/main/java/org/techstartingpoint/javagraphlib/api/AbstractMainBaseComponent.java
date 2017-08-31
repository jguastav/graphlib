package org.techstartingpoint.javagraphlib.api;

import org.techstartingpoint.javagraphlib.model.workflow.NodeConfiguration;

/**
 * 
 * Main classes from which tools should extend
 * It representes the main frame of execution of an element or component
 * Each AbstractMainBaseComponent should be associated to a AbstractAfluxComponent that has the core of execution
 * 
 * 
 * @author Jose Alberto Guastavino
 *
 */
public abstract class AbstractMainBaseComponent {

	/**
	 * Number of input interfaces (Can be data or no data interfaces)
	 * no data interfaces referes to a precedence relation
	 * 
	 * 	Number of input interfaces (Includes data and no data interfaces)
	 * Input interfaces are represented as connectors that can receive data (or signal) from another element. 
	 * “no data” interfaces refers to a precedence relation.
	 * There are no difference between data or no data connectors. The real difference depends on the implementation. Usually “no data” input means that in the implementation of runCore in the component the task can be executed with no importance of the message that has received.
	 * In case the implementation doesn’t take data from the received message can be said that the element can be launched by SIGNAL.
	 * In this version …. inputPorts should be always 1
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 * 
	 */
	//TODO: change to array pairs name / index / class
	private int inputPorts;
	
	/**
	 * Number of output data interfaces. This number does not include the async output connector.
	 * Components can send data to other excomponents calling
	 * setOutput(index,message) having index between 1 and outputPorts
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	//TODO: change to array pairs name / index / class
	private int outputPorts;


	

	/**
	 * 
	 * Complete class name of related component that contains the implementation of the task
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	private String componentClassName;

	/**
	 * Name of the tool to be shown in tool gallery
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	private String name;
	



    String environmentKey;
    NodeConfiguration nodeConfiguration;

	
	public int getInputPorts() {
		return inputPorts;
	}

	public void setInputPorts(int inputPorts) {
		this.inputPorts = inputPorts;
	}

	public int getOutputPorts() {
		return outputPorts;
	}

	public void setOutputPorts(int outputPorts) {
		this.outputPorts = outputPorts;
	}


	
	public String getComponentClassName() {
		return componentClassName;
	}

	public void setComponentClassName(String componentClassName) {
		this.componentClassName = componentClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


    public String getEnvironmentKey() {
        return environmentKey;
    }

    public void setEnvironmentKey(String environmentKey) {
        this.environmentKey = environmentKey;
    }

    public NodeConfiguration getNodeConfiguration() {
        return nodeConfiguration;
    }

    public void setNodeConfiguration(NodeConfiguration nodeConfiguration) {
        this.nodeConfiguration = nodeConfiguration;
    }
}
