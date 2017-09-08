
package com.onelake.workflowexecutor.executions;


import java.util.List;
import java.util.Map.Entry;

import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.schema.repo.ComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.onelake.workflowexecutor.api.AbstractMainExecutor;
import com.onelake.workflowexecutor.collections.ExecutorModel;
import com.onelake.workflowexecutor.collections.GraphConnection;
import com.onelake.workflowexecutor.util.GraphAPIService;


/**
 * 
 * Main executor of single activities
 * It should be created an instance of this class for each running workflow
 * It creates an AkkaComponentSystem and runs those elements that has no predecessors
 * When the elements remove send it output to the target excomponents via broadcast methods
 * Async connections are like dummy messages sent initially by the excomponents
 * 
 * @author Jose Alberto Guastavino
 *
 */
public class GraphRunnerImpl implements GraphRunner,GraphRunnerLauncher {
	

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Current running workflow
	 */
    /**
     * Data defition of the main flow arranged to be executed including a map of all nodes (included nodes present in subflows) and connectors
     * Connectors are rearranged to deal with the amp of nodes
     */
	private ExecutorModel executionModel;

    /**
	 * Main framework of execution where all running activities are launched
	 * In this class Is used to force stop
	 */
	GraphRunnerEnvironmentImpl caller;


	/**
	 * Akka component System
	 */
	ComponentSystem componentSystem;

	

	
	public GraphRunnerImpl() {

	}

	/**
	 * 
	 * @param workflowService
	 * @param workflowFileName
	 * @param graphRunnerEnvironment
	 * 
	 * @author Jose Alberto Guastavino
	 */
	public GraphRunnerImpl(GraphAPIService workflowService,
						   String workflowFileName,
						   ComponentRepository componentRepository,
						   GraphRunnerEnvironmentImpl graphRunnerEnvironment) throws OnelakeException {
		this.executionModel =workflowService.getExecutionModel(workflowFileName,componentRepository);
		// retrieve workflow


		//CheckCycle checkCycle=new CheckCycle();
		//this.isCyclic=checkCycle.checkCycle(this.workflow.getNodeList(), this.workflow.getConnectorList());
		// this.environment.sendMessage(-1L, "jobName:"+jobName+" workflow:"+activityName+" cyclic:"+this.isCyclic);
		this.caller=graphRunnerEnvironment;
	}
	

	
	/**
	 * StartMock all excomponents that have no predecessors
	 * Launches the first elements to be executed
	 * Elements and connectors that can be executed are those that doess not need input data (LAUNCHED_BY_SIGNAL) and doesnt have predecessors
	 * @param executionModel
	 * @
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	private void runExecutors(ExecutorModel executionModel)  {
		// main cycle
		this.componentSystem = ComponentSystem.create();
		// start all executors
		// makes all executors begin to listen to messages
		int idInstanceIndex=1;
		for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
			executorEntry.getValue().setRunningContext(componentSystem, this);
		}
		// launch all executors that can be started
        boolean executorsThatCanBeStarted=true;
        while (executorsThatCanBeStarted) {
            executorsThatCanBeStarted=false;
            for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
                if (executorEntry.getValue().isReadyToStart()) {
                    executorsThatCanBeStarted=true;
                    executorEntry.getValue().run();
                }
            }
        }
        List<AbstractMainExecutor> pendingExecutors=executionModel.getPendingNodes();
        System.out.println("PendingNodes");
        for (AbstractMainExecutor executor:pendingExecutors) {
            System.out.println(executor);
        }


	}



	/**
	 * Main add exectuion method
	 * Instantiates and runs the first excomponents
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public void run() throws OnelakeException {
        this.executionModel.validate();

        System.out.println("ExecutionModel:\n\t"+this.executionModel);
        runExecutors(this.executionModel);
		try {
		} catch (Exception e) {
			System.out.println(
					"0:"+
					"GraphRunner.add(): Finish add with errors:"+
							this.executionModel ==null?
									"null":
									" exception "+e.getMessage()+" "+
									e.getCause()!=null?e.getCause().toString():"");
			if (e.getCause()!=null) {
                System.out.println("0:"+e.getCause().toString());
			}
			// this.caller.stop(this.job.getName(),this.workflow.getName());
		}
	}

	
	/**
	 * Method used by excomponents to send messages to each others
	 * in runMain of excomponents should call setOutput that internally add this method... sending the message to all connected target elements
	 * 
	 * 
	 * @author Jose Alberto Guastavino
	 * 
	 */
	public void broadcast(String nodeId, int outputIndex, Object value)  {
		List<GraphConnection> connectors=this.executionModel.getConnectors();
		AbstractMainExecutor executor=this.executionModel.getExecutors().get(nodeId);
		if (outputIndex>=0 && outputIndex<executor.getTotalOutputPorts()) {
			for (GraphConnection thisConnector:connectors) {
				if (thisConnector.getSourceId().equals(executor.getNodeId()) && thisConnector.getSourceIndex()==outputIndex) {
					AbstractMainExecutor target=this.executionModel.getExecutors().get(thisConnector.getTargetId());
					int targetIndex=thisConnector.getTargetIndex();
					target.setInputValue(targetIndex,value);;
				}
			}
		} 
	}

	

	

}
 