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
	private List<Object> inputs;
	private List<Object> outputs;
	private boolean finished=false;

	
	/**
	 * akka component for akka implementation
	 */
	

	/**
	 * component instance when it is running
	 */
	private ComponentRef componentInstance;
	
	
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
		this.setComponentImplementation(new ComponentImplementation(className));
		this.identifier=className;
		this.setName(name);
		this.inputs=new ArrayList<Object>(inputPorts);
		for (int i=0;i<inputPorts;i++) {
			inputs.add(null);
		}
		this.outputs=new ArrayList<Object>(outputPorts);
		for (int i=0;i<outputPorts;i++) {
			outputs.add(null);
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
        this.componentInstance =
        		this.componentSystem.componentOf(
        				springExtension.props(
        						this.getComponentClassName(),
        						this.nodeId,
        						this.graphEnvironment,
                                graphRunner),
						this.componentInstanceName);

	}
	
	
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
			if (sender== null) {
				System.out.println("AbstractMainExecutor.send(nosender)");
				System.out.println(this.componentInstance);
	            this.componentInstance.tell(messageToSend, ComponentRef.noSender());
			} else {
				System.out.println("AbstractMainExecutor.send(sender)");
				System.out.println(this.componentInstance);
	            this.componentInstance.tell(messageToSend, sender.componentInstance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.graphEnvironment.getOutput().sendMessage(this.getNodeId(), e.getMessage());
			throw e;
		} finally {
			this.finished=true;
		}
	}

	


	
	protected void setOutput(int index,Object o) {
		this.outputs.set(index, o);
	}
	
	public List<Object> getInputs() {
		return inputs;
	}

	public void setInputs(List<Object> inputs) {
		this.inputs = inputs;
	}

	public List<Object> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Object> outputs) {
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

	
	



	public ComponentRef getComponentInstance() {
		return componentInstance;
	}


	public void setComponentInstance(ComponentRef componentInstance) {
		this.componentInstance = componentInstance;
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
				"AbstractMainExecutor [nodeId=%s, inputs=%s, outputs=%s, finished=%s, componentInstance=%s, componentInstanceName=%s, componentSystem=%s, identifier=%s,  graphEnvironment=%s]",
                nodeId, inputs, outputs, finished, componentInstance, componentInstanceName, componentSystem, identifier,
				graphEnvironment);
	}



}
