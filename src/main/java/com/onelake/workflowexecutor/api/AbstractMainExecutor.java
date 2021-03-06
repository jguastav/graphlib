package com.onelake.workflowexecutor.api;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.collections.ExecutorPort;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import com.onelake.workflowexecutor.executions.ComponentSystem;
import com.onelake.workflowexecutor.executions.GraphRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AbstractMainExecutor.class.getName());

	private String nodeId;
	private List<ExecutorPort> inputs;
	private List<ExecutorPort> outputs;
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
     * In this version …. totalInputPorts should be always 1
     *
     *
     * @author Jose Alberto Guastavino
     *
     *
     */

    /**
     * Number of output data interfaces. This number does not include the async output connector.
     * Components can send data to other excomponents calling
     * setOutput(index,message) having index between 1 and totalOutputPorts
     *
     *
     */

    public final int getTotalInputPorts() {return getInputClasses().length;}
    public final int getTotalOutputPorts() {return getOutputClasses().length;}

    abstract public Class[] getInputClasses();
    abstract public Class[] getOutputClasses();


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
    String nodeConfiguration;


    public String getEnvironmentKey() {
        return environmentKey;
    }

    public void setEnvironmentKey(String environmentKey) {
        this.environmentKey = environmentKey;
    }

    public String getNodeConfiguration() {
        return nodeConfiguration;
    }

    public void setNodeConfiguration(String nodeConfiguration) {
        this.nodeConfiguration = nodeConfiguration;
    }


    /*
                try {
            */
                /*
                Constructor c = g.getConstructor(portClass.getClass());
                */
    // ExecutorPort<?> newPort= (ExecutorPort<?>) c.newInstance(Integer.toString(i));
    // inputs.add(newPort);
    /*
                inputs.add(new ExecutorPort<String>(Integer.toString(i)));
                */
                /*
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                */
                /*
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                */




	
	/**
	 * 
	 *
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public AbstractMainExecutor() {
		this.inputs=new ArrayList<ExecutorPort>(getTotalInputPorts());
		for (int i = 0; i< getTotalInputPorts(); i++) {
                inputs.add(new ExecutorPort(this.getInputClasses()[i],Integer.toString(i)));
		}
		this.outputs=new ArrayList<ExecutorPort>(this.getTotalOutputPorts());
		for (int i = 0; i< this.getTotalOutputPorts(); i++) {
            outputs.add(new ExecutorPort(this.getOutputClasses()[i],Integer.toString(i)));
		}
		this.finished=false;
	}



    public static AbstractMainExecutor create(
            String className,
            String id,
            String environmentKey,
            String nodeConfiguration) throws OnelakeException {
        Class<?> clazz;

        try {
            clazz = Class.forName(className);
            AbstractMainExecutor executorElement =(AbstractMainExecutor) clazz.newInstance();
            executorElement.setNodeId(id);
            executorElement.setEnvironmentKey(environmentKey);
            executorElement.setNodeConfiguration(nodeConfiguration);
            return executorElement;
        } catch (ClassNotFoundException e) {
            logger.error(WorkflowErrorCode.ClassNotFound.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.ClassNotFound, e).set("className", className).set("class",AbstractMainExecutor.class);
        } catch (IllegalAccessException e) {
            logger.error(WorkflowErrorCode.IllegalAccess.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.IllegalAccess, e).set("className", className).set("class",AbstractMainExecutor.class);
        } catch (InstantiationException e) {
            logger.error(WorkflowErrorCode.ClassNotInstantiable.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.ClassNotInstantiable, e).set("className", className).set("class",AbstractMainExecutor.class);
        }
    }



	public void setRunningContext(
			ComponentSystem componentSystem,
			GraphRunner graphRunner)  {
		System.out.println("AbstractMainExecutor.setRunningContext()"+this.componentInstanceName);
		System.out.println(this.getName()+":"+this.getNodeId());
        this.componentSystem = componentSystem;
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




    public Object getOutputValue(int index)  {
        return this.getOutputs().get(index).getValue();
    }


    public Object getInputValue(int index)  {
        return this.getInputs().get(index).getValue();
    }


    public void setInputValue(int index,Object value)  {
        this.getInputs().get(index).setValue(value);
        this.getInputs().get(index).setSetted(true);
    }


    protected void setOutputValue(int index,Object value)  {
        System.out.println("index:"+index);
        System.out.println("runner:"+this.runner);

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
    public void run()  {
        runPre();
        runMain();
        runPost();
        // call to emtpy outputs to set those outputs that were not setted by the component during execution
        this.emptyOutputs();
        System.out.println("-------------------------------------------------------------------------");
        this.finished=true;
    }


    private void emptyOutputs()  {
        for (int i = 0; i<this.getTotalOutputPorts(); i++) {
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
     * @
     *
     * @author Jose Alberto Guastavino
     *
     */
    protected abstract void runMain() ;


    // END CORE EXECUTION


	public List<ExecutorPort> getInputs() {
		return inputs;
	}

	public List<ExecutorPort> getOutputs() {
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
            for (int i = 0; i<this.getTotalInputPorts(); i++) {
                if (!this.inputs.get(i).isSetted()) {
                    result=false;
                }
            }
        }
		return result;
	}





	public ComponentSystem getComponentSystem() {
		return componentSystem;
	}


	public void setComponentSystem(ComponentSystem componentSystem) {
		this.componentSystem = componentSystem;
	}



    public abstract String getName();

	@Override
	public String toString() {
		return String.format(
				"AbstractMainExecutor [nodeId=%s, inputs=%s, outputs=%s, finished=%s, componentSystem=%s]",
                nodeId, inputs, outputs, finished,   componentSystem);
	}



}
