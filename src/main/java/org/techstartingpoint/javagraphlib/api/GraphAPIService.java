
package org.techstartingpoint.javagraphlib.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.onelake.api.error.OnelakeException;
import com.onelake.workflowexecutor.error.WorkflowErrorCode;
import com.onelake.workflowexecutor.schema.repo.ComponentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.graph.ExecutorModel;
import org.techstartingpoint.javagraphlib.graph.GraphConnection;
import org.techstartingpoint.javagraphlib.model.workflow.Connection;
import org.techstartingpoint.javagraphlib.model.workflow.Node;
import org.techstartingpoint.javagraphlib.model.workflow.Workflow;

/**
 * 
 * 
 * Job Related Operations
 * 
 * @author Jose Alberto Guastavino
 *
 */

public class GraphAPIService {

    private static final Logger logger = LoggerFactory.getLogger(GraphAPIService.class.getName());

    /**
     * Get Activity by a given name in a given job
     * @param workflowFileName
     * @param componentRepository
     * @return
     */
    public ExecutorModel getExecutionModel(String workflowFileName, ComponentRepository componentRepository) throws OnelakeException {

        // read workflow
        String jsonWorkflowString=readJson(workflowFileName);

        // generate object java
        Workflow jsonWorkflow = convert(jsonWorkflowString);

        logger.debug(jsonWorkflow.toString());

        // generate graph process
        ExecutorModel result = convert(jsonWorkflowString,jsonWorkflow,componentRepository);

    	return result;
    }


    private String getComponentName(String id,String name,String version) {
        return id+"-"+name+"-"+version;
    }

    /**
     * Generates ExecutorModel based on a previous Workflow model
     * Also check valiudation of ExecutionModel
     * @param jsonWorkflow
     * @param componentRepository
     * @return
     * @throws OnelakeException
     */
    private ExecutorModel convert(String workflowName,Workflow jsonWorkflow,ComponentRepository componentRepository) throws OnelakeException {
        // Map to store and search nodes
        Map<String,AbstractMainExecutor> nodeMap=new HashMap<String,AbstractMainExecutor>();
        if (jsonWorkflow.getNodes().size()==0) {
            logger.error(WorkflowErrorCode.ZeroNodesGraph.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.ZeroNodesGraph).set("graphName", workflowName).set("class", GraphAPIService.class);
        }
        ExecutorModel executorModel =new ExecutorModel();
        // generate nodes
        for (Node node:jsonWorkflow.getNodes()) {
            String implementationName=componentRepository.getComponents().get(
                    getComponentName(node.getComponent_info().getId(),
                            node.getComponent_info().getName(),
                            node.getComponent_info().getVersion()));
            if (implementationName!=null) {
                AbstractMainExecutor executorElement=
                        AbstractMainExecutor.create(
                                implementationName,node.getId(),node.getEnvironment_key(),node.getConf());
                executorModel.addExecutor(executorElement);
                nodeMap.put(node.getId(),executorElement);
            } else {
                logger.error(WorkflowErrorCode.NoImplementationName.getMessage());
                throw OnelakeException.build(WorkflowErrorCode.NoImplementationName).
                        set("graphName", workflowName).
                        set("nodeId",node.getComponent_info().getId()).
                        set("class", GraphAPIService.class);
            }

        }
        long idConnector=0;
        // generate connections
        for (Connection connection:jsonWorkflow.getConnections()) {
            String sourceId=connection.getFrom().getNode_id();
            String targetId=connection.getTo().getNode_id();
            if (sourceId!=null && targetId!=null) {
                AbstractMainExecutor sourceNode=nodeMap.get(sourceId);
                AbstractMainExecutor targetNode=nodeMap.get(targetId);
                if (sourceNode!=null && targetNode!=null) {
                    GraphConnection graphConnection=
                            new GraphConnection(
                                    idConnector,
                                    sourceNode,connection.getFrom().getPort_index(),
                                    targetNode,connection.getTo().getPort_index());
                    executorModel.addConnector(graphConnection);
                }
            } else {
                logger.error(WorkflowErrorCode.WrongIdNodeOnConnector.getMessage());
                throw OnelakeException.build(WorkflowErrorCode.WrongIdNodeOnConnector).
                        set("graphName", workflowName).
                        set("sourceId",connection.getFrom().getNode_id()).
                        set("targetId",connection.getTo().getNode_id()).
                        set("class", GraphAPIService.class);
            }
            idConnector++;
        }
        System.out.println(executorModel.toString());
        return executorModel;
    }

    /**
     * Generate plain Workflow object from workflow string
     * @param jsonWorkflowString
     * @return
     */
    private Workflow convert(String jsonWorkflowString) throws OnelakeException {
        Gson gson = new Gson();
        Workflow result=null;
        try {
            result=gson.fromJson(jsonWorkflowString, Workflow.class);
        } catch (JsonSyntaxException e) {
            logger.error(WorkflowErrorCode.JsonParserError.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.JsonParserError, e).set("fileName", jsonWorkflowString).set("class", GraphAPIService.class);

        }
        return result;

    }



    /**
     * Read a workflow from file and returns the workflow in a String
     * @param fileName
     * @return
     * @
     */
    private String readJson(String fileName) throws OnelakeException {


        java.net.URL resource=getClass().getClassLoader().getResource(fileName);
        String jsonString= null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(resource.toURI())));
        } catch (IOException e) {
            logger.error(WorkflowErrorCode.IOException.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.IOException, e).set("fileName", fileName);
        } catch (URISyntaxException e) {
            logger.error(WorkflowErrorCode.URISyntaxError.getMessage());
            throw OnelakeException.build(WorkflowErrorCode.URISyntaxError, e).set("fileName", fileName);
        }
        logger.debug(jsonString);
        return jsonString;
    }
    
}
