package org.techstartingpoint.javagraphlib.graph;

import org.techstartingpoint.javagraphlib.execution.*;
import org.techstartingpoint.javagraphlib.model.workflow.NodeConfiguration;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Class used by the runner to manage execute elements.
 * this class contains the information for a running process based in a tool and an component
 * 
 * @author Jose Alberto Guastavino
 *
 */
public abstract class AbstractMainExecutor  {



	private String nodeId;
	private List<ExecutionPort> inputs;
	private List<ExecutionPort> outputs;
	private boolean finished=false;

    /**
     * Number of input interfaces (Can be data or no data interfaces)
     * no data interfaces referes to a precedence relation
     *
     * 	Number of input interfaces (Includes data and no data interfaces)
     * Input interfaces are represented as connectors that can receive data (or signal) from another element.
     * “no data” interfaces refers to a precedence relation.
     * There are no difference between data or no data connectors. The real difference depends on the implementation. Usually “no data” input means that in the implementation of runMain in the component the task can be executed with no importance of the message that has received.
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
     * akka component for akka implementation
     */
	
	/**
	 * component name in akka context
	 */
	private String componentInstanceName;
	
	
	/**
	 * Context on which component is running
	 */
	private ComponentSystem componentSystem;


    /**
     * Name of the tool to be shown in tool gallery
     *
     * @author Jose Alberto Guastavino
     *
     */
    private String name;




    String environmentKey;
    NodeConfiguration nodeConfiguration;






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

	
	
	/**
	 * 
	 * @param name
	 * @param componentClassName
	 * @param className
	 * @param inputPorts
	 * @param outputPorts
	 *
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public AbstractMainExecutor(
			String name,
			String componentClassName,
			String className,
			int inputPorts,
			int outputPorts) {
		this.setComponentClassName(componentClassName);
		this.setName(name);
		this.inputs=new ArrayList<ExecutionPort>(inputPorts);
		for (int i=0;i<inputPorts;i++) {
			inputs.add(new ExecutionPort(i,Integer.toString(i),null,null,false));
		}
		this.outputs=new ArrayList<ExecutionPort>(outputPorts);
		for (int i=0;i<outputPorts;i++) {
            outputs.add(new ExecutionPort(i,Integer.toString(i),null,null,false));
		}
		this.setInputPorts(inputPorts);
		this.setOutputPorts(outputPorts);
		this.finished=false;
	}



    public static AbstractMainExecutor create(String className, String id, String environmentKey, NodeConfiguration nodeConfiguration) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz;
        clazz = Class.forName(className);
        AbstractMainExecutor executorElement =(AbstractMainExecutor) clazz.newInstance();
        executorElement.setNodeId(id);
        executorElement.setEnvironmentKey(environmentKey);
        executorElement.setNodeConfiguration(nodeConfiguration);
        return executorElement;
    }



	public void instantiate(
			ComponentSystem componentSystem,
			String instanceName,
			GraphRunner graphRunner) throws ClassNotFoundException {
		this.componentSystem = componentSystem;
		this.componentInstanceName=instanceName;
		System.out.println("AbstractMainExecutor.instantiate()"+this.componentInstanceName);
		System.out.println(this.getName()+":"+this.getNodeId());
		this.componentSystem.register(this.componentInstanceName,this);
		this.runner=graphRunner;
	}

/*
	        				springExtension.props(
                                    this.getComponentClassName(),
        						this.nodeId,
            this.graphEnvironment,
    graphRunner),
            this.componentInstanceName);
*/




    public Object getOutputValue(int index) throws Exception {
        return this.getOutputs().get(index).getValue();
    }


    public Object getInputValue(int index) throws Exception {
        return this.getInputs().get(index).getValue();
    }


    public void setInputValue(int index,Object value) throws Exception {
        this.getInputs().get(index).setValue(value);
        this.getInputs().get(index).setSetted(true);
    }


    protected void setOutputValue(int index,Object value) throws Exception {
	    this.getOutputs().get(index).setValue(value);
	    this.getOutputs().get(index).setSetted(true);
	    this.runner.broadcast(this.getNodeId(),index,value);
    }


    // CORE EXECUTION
    private GraphRunner runner;





    /**
     * Main entry point on receiving a message from another component or starting it
     *
     * @author Jose Alberto Guastavino
     *
     */
    public void run() throws Throwable {
        runPre();
        runMain();
        runPost();
        // call to emtpy outputs to set those outputs that were not setted by the component during execution
        this.emptyOutputs();
        System.out.println("-------------------------------------------------------------------------");
        this.finished=true;
    }


    private void emptyOutputs() throws Exception {
        for (int i=0;i<this.getOutputPorts();i++) {
            if (!this.getOutputs().get(i).isSetted()) {
                this.setOutputValue(i,null);
            }
        }
    }


    // To be implemented by component if needed to run before main run
    public void runPre() { }

    // To be implemented by component if needed to run before main run
    public void runPost() { }


    /**
     * Method each plugin element component should implement
     *
     * Inside elements can be calles
     * 	setOutput(index,message): to set the message to be sent to another component
     *  sendMessage(....) : to show information in the output console of the app
     *  getEnvironment()... : to use shared resources of the execution runnning environment
     *  getProperties() : to access customized data of the element setted in each instance
     *
     * @throws Exception
     *
     * @author Jose Alberto Guastavino
     *
     */
    protected abstract void runMain() throws Exception;


    // END CORE EXECUTION


	public List<ExecutionPort> getInputs() {
		return inputs;
	}

	public List<ExecutionPort> getOutputs() {
		return outputs;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public boolean isFinished() {
		return finished;
	}




	public void setFinished(boolean finished) {
		this.finished = finished;
	}




	public boolean isReadyToStart() {

		boolean result=true;
		if (finished) {
            result=false;
        } else {
            for (int i=0;i<this.getInputPorts();i++) {
                if (!this.inputs.get(i).isSetted()) {
                    result=false;
                }
            }
        }
		return result;
	}




	public String getComponentInstanceName() {
		return componentInstanceName;
	}


	public void setComponentInstanceName(String componentInstanceName) {
		this.componentInstanceName = componentInstanceName;
	}


	public ComponentSystem getComponentSystem() {
		return componentSystem;
	}


	public void setComponentSystem(ComponentSystem componentSystem) {
		this.componentSystem = componentSystem;
	}

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


	@Override
	public String toString() {
		return String.format(
				"AbstractMainExecutor [nodeId=%s, inputs=%s, outputs=%s, finished=%s, componentInstanceName=%s, componentSystem=%s]",
                nodeId, inputs, outputs, finished,  componentInstanceName, componentSystem);
	}



}
