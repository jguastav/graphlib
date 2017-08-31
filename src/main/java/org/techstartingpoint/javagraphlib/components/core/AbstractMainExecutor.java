package org.techstartingpoint.javagraphlib.components.core;

import org.techstartingpoint.javagraphlib.excomponents.ComponentRef;
import org.techstartingpoint.javagraphlib.excomponents.ComponentSystem;
import org.techstartingpoint.javagraphlib.graphengine.GraphEnvironment;
import org.techstartingpoint.javagraphlib.graphengine.GraphExtension;
import org.techstartingpoint.javagraphlib.graphengine.GraphRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * Class used by the runner to manage execute elements.
 * this class contains the information for a running process based in a tool and an component
 * 
 * @author Jose Alberto Guastavino
 *
 */
public abstract class AbstractMainExecutor extends AbstractMainBaseComponent {
	private String nodeId;
	private List<ExecutionPort> inputs;
	private List<ExecutionPort> outputs;
	private boolean finished=false;




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
	 * Unique name in the world (think that tools can be added by user)
	 */
	private String identifier;
	

	
	
	// Global Elements
	private GraphEnvironment graphEnvironment;
	
	
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
		this.identifier=className;
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
	

	public void instantiate(
			ComponentSystem componentSystem,
			GraphExtension springExtension,
			String instanceName, 
			GraphRunner graphRunner) throws ClassNotFoundException {
		this.componentSystem = componentSystem;
		this.componentInstanceName=instanceName;
		System.out.println("AbstractMainExecutor.instantiate()"+this.componentInstanceName);
		System.out.println(this.getName()+":"+this.getNodeId());
		this.componentSystem.register(this.componentInstanceName,this);
	}

/*
	        				springExtension.props(
                                    this.getComponentClassName(),
        						this.nodeId,
            this.graphEnvironment,
    graphRunner),
            this.componentInstanceName);
*/

    public void start() throws Exception {
		send(new Object(),null);
	}

	private class DummyMessage {};
	
	public void send(Object message, AbstractMainExecutor sender) throws Exception {
		Object messageToSend=message;
		if (messageToSend==null) {
			messageToSend=new DummyMessage();
		}
		
		try {
			System.out.println("AbstractMainExecutor.send()"+this.getNodeId());
			System.out.println("AbstractMainExecutor.send(sender)");
            this.tell(messageToSend, sender);
		} catch (Exception e) {
			e.printStackTrace();
			this.graphEnvironment.getOutput().sendMessage(this.getNodeId(), e.getMessage());
			throw e;
		} finally {
			this.finished=true;
		}
	}

    private void tell(Object messageToSend, AbstractMainExecutor sender) {
	    // TODO: Call method onReceive







    }




    // CORE EXECUTION


    private GraphRunner runner;



    /**
     * Main entry point on receiving a message from another component or starting it
     *
     * @author Jose Alberto Guastavino
     *
     */
    public void onReceive(Object message) throws Throwable {
        if (this.runner!=null) {
            this.runner.broadcast(this.nodeId, -1, message);
        }
        Long startedTime=System.currentTimeMillis();
        runCore(message);
        Long endTime=System.currentTimeMillis();
        Long duration=endTime-startedTime;
    }


    /**
     * Method each plugin element component should implement
     *
     * Inside elements can be calles
     * 	setOutput(index,message): to set the message to be sent to another component
     *  sendMessage(....) : to show information in the output console of the app
     *  getEnvironment()... : to use shared resources of the execution runnning environment
     *  getProperties() : to access customized data of the element setted in each instance
     *
     * @param message
     * @throws Exception
     *
     * @author Jose Alberto Guastavino
     *
     */
    protected abstract void runCore(Object message) throws Exception;


    // END CORE EXECUTION


    protected void setOutput(int index,ExecutionPort o) {
		this.outputs.set(index, o);
	}
	
	public List<ExecutionPort> getInputs() {
		return inputs;
	}

	public void setInputs(List<ExecutionPort> inputs) {
		this.inputs = inputs;
	}

	public List<ExecutionPort> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<ExecutionPort> outputs) {
		this.outputs = outputs;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public GraphEnvironment getEnvironment() {
		return graphEnvironment;
	}

	protected void setEnvironment(GraphEnvironment graphEnvironment) {
		this.graphEnvironment = graphEnvironment;
	}




	public boolean isFinished() {
		return finished;
	}




	public void setFinished(boolean finished) {
		this.finished = finished;
	}




	public boolean isReadyToStart() {
		boolean result=true;
		for (int i=0;i<this.getInputPorts();i++) {
			if (this.inputs.get(i)==null) {
				result=false;
			}
		}
		return result;
	}

	public String getIdentifier() {
		return identifier;
	}





	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}






	public GraphEnvironment getGraphEnvironment() {
		return graphEnvironment;
	}





	public void setGraphEnvironment(GraphEnvironment graphEnvironment) {
		this.graphEnvironment = graphEnvironment;
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


	@Override
	public String toString() {
		return String.format(
				"AbstractMainExecutor [nodeId=%s, inputs=%s, outputs=%s, finished=%s, componentInstanceName=%s, componentSystem=%s, identifier=%s,  graphEnvironment=%s]",
                nodeId, inputs, outputs, finished,  componentInstanceName, componentSystem, identifier,
				graphEnvironment);
	}



}
