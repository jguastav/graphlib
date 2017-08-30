
package org.techstartingpoint.javagraphlib.graphengine.impl;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.GraphUtils;
import org.techstartingpoint.javagraphlib.excomponents.ComponentRef;
import org.techstartingpoint.javagraphlib.excomponents.ComponentSystem;
import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graphengine.GraphRunner;
import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.graphengine.launcher.GraphRunnerLauncher;
import org.techstartingpoint.javagraphlib.model.GraphExecutionConnection;
import org.techstartingpoint.javagraphlib.model.GraphExecutionModel;
import org.techstartingpoint.javagraphlib.model.GraphProcess;
import org.techstartingpoint.javagraphlib.services.GraphProcessSetService;


/**
 * 
 * Main executor of single activities
 * It should be created an instance of this class for each running workflow
 * It creates an AkkaComponentSystem and runs those elements that has no predecessors
 * When the elements finish send it output to the target excomponents via broadcast methods
 * Async connections are like dummy messages sent initially by the excomponents
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerImpl implements GraphRunner,GraphRunnerLauncher {
	

	private final Logger log = LoggerFactory.getLogger(this.getClass());





	
	/**
	 * Shared context of all executions
	 * 
	 */
	private GraphCompleteEnvironment environment;
	
	/**
	 * Current running workflow
	 */
	private GraphProcess workflow;

	/**
	 * Element that takes into account running processes in order to know if an workflow is finished
	 */
	private ExecutionEnvironment executionEnvironment;
	
	/**
	 * Main framework of execution where all running activities are launched
	 * In this class Is used to force stop
	 */
	GraphRunnerEnvironmentImpl caller;

	
	/**
	 * Akka component System
	 */
	ComponentSystem componentSystem;

	
	/**
	 * Data defition of the main flow arranged to be executed including a map of all nodes (included nodes present in subflows) and connectors
	 * Connectors are rearranged to deal with the amp of nodes
	 */
	private GraphExecutionModel executionModel;
	
	
	public GraphRunnerImpl() {
		
	}
	
	/**
	 * 
	 * @param fluxEnvironment
	 * @param workflowService
	 * @param workflowFileName
	 * @param graphRunnerEnvironment
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public GraphRunnerImpl(GraphCompleteEnvironment graphEnvironment,
						   GraphProcessSetService workflowService,
						   String workflowFileName,
						   GraphRunnerEnvironmentImpl graphRunnerEnvironment) throws IOException, URISyntaxException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		this.workflow =workflowService.getGraphProcess(workflowFileName);
		// retrieve workflow


        this.environment=graphEnvironment;
		//CheckCycle checkCycle=new CheckCycle();
		//this.isCyclic=checkCycle.checkCycle(this.workflow.getNodeList(), this.workflow.getConnectorList());
		// this.environment.sendMessage(-1L, "jobName:"+jobName+" workflow:"+activityName+" cyclic:"+this.isCyclic);
		this.executionEnvironment=new ExecutionEnvironment();
		this.caller=graphRunnerEnvironment;
	}
	

	
	/**
	 * Start all excomponents that have no predecessors
	 * Launches the first elements to be executed
	 * Elements and connectors that can be executed are those that doess not need input data (LAUNCHED_BY_SIGNAL) and doesnt have predecessors
	 * @param executionModel
	 * @throws Exception
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	private void runExecutors(GraphExecutionModel executionModel) throws Exception {
		// main cycle
		GraphExtensionImpl springExtension=new GraphExtensionImpl();
		this.componentSystem = ComponentSystem.create(
		        this.workflow.getName().replace(' ','_')+"_"+ GraphUtils.getNowId());
		// start all executors
		// makes all executors begin to listen to messages
		int idInstanceIndex=1;
		for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
			executorEntry.getValue().instantiate(componentSystem, springExtension,String.valueOf(idInstanceIndex++),this);
		}
		// launch all initial executors
		for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
			// TODO: change condition - verify all inputs are ready
			if (hasNoPredecessors(executorEntry.getKey())) {
				this.executionEnvironment.run(executorEntry.getValue());
				executorEntry.getValue().start();
			}
			
		}
	}
	
	
	/**
	 * Method to know if an element has no predecessors in the current execution Model
	 * The goal is to identify those nodes that does not need any previous node to start
	 * It was used initially to know if an element can be started at beggining
	 * It should be deprecated
	 * @param executorKey
	 * @return
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	private boolean hasNoPredecessors(String executorKey) {
		List<GraphExecutionConnection> connectors=this.executionModel.getConnectors();
		boolean result=true;
		boolean found=false;
		for (int i=0;i<connectors.size() && !found;i++) {
			found=connectors.get(i).getTargetExecutionId().equals(executorKey);
		}
		result=!found;
		return result;
	}

	
	/**
	 * Main run exectuion method
	 * Instantiates and runs the first excomponents
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public void run() {
		try {
			this.environment.setStatus(GraphCompleteEnvironment.RUNNING_STATUS);
			this.executionModel=
					GraphExecutorModelManager.generateExecutionModel(
							new GraphExecutionModel(),"",this.workflow.getNodeList(),this.workflow.getConnectorList(),this.environment);
			System.out.println("ExecutionModel:"+this.executionModel);
			runExecutors(this.executionModel);
		} catch (Exception e) {
			this.environment.showMessage(
					"0", 
					"GraphRunner.run(): Finish run with errors:"+
							this.workflow ==null?
									"null":
									this.workflow.getName()+" exception "+e.getMessage()+" "+
									e.getCause()!=null?e.getCause().toString():"");
			if (e.getCause()!=null) {
				this.environment.getOutput().sendMessage("0",e.getCause().toString());
			}
			// this.caller.stop(this.job.getName(),this.workflow.getName());
		} finally {
			this.environment.setStatus(GraphCompleteEnvironment.INACTIVE_STATUS);
			
		}
	}

	
	/**
	 * Method used by excomponents to send messages to each others
	 * in runCore of excomponents should call setOutput that internally run this method... sending the message to all connected target elements
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public void broadcast(String fluxId, int outputIndex, Object value) throws Exception {
		List<GraphExecutionConnection> connectors=this.executionModel.getConnectors();
		AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
		if (outputIndex>0 && executor.getOutputPorts()>0) {
			for (GraphExecutionConnection thisConnector:connectors) {
				if (thisConnector.getSourceExecutionId().equals(executor.getNodeId()) && thisConnector.getSourceIndex()==outputIndex) {
					AbstractMainExecutor target=this.executionModel.getExecutors().get(thisConnector.getTargetExecutionId());
					this.executionEnvironment.run(target);
					target.send(value,executor);
				}
			}
		} 
	}

	
	/**
	 * Stop current workflow
	 * This method kill all excomponents and componentSystem of current workflow
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public void stop() {
		try {
			for (Entry<String,AbstractMainExecutor> executorEntry:this.executionModel.getExecutors().entrySet()) {
				AbstractMainExecutor executor=executorEntry.getValue();
				this.componentSystem.stop(executor);
			}
			this.componentSystem.terminate(); // TODO: Its a future- confirma termination
		} catch (Exception e) {
			this.environment.showMessage("0", "GraphRunner.run(): Finish run with errors:"+this.workflow ==null?"null":this.workflow.getName()+" exception "+e.getMessage());
			if (e.getCause()!=null) {
				this.environment.showMessage("0", "GraphRunner.run(): Finish run with errors:"+" exception "+e.getCause().toString());
			}
		} finally {
			this.environment.setStatus(GraphCompleteEnvironment.INACTIVE_STATUS);
			
		}
	}

	

}
 