
package org.techstartingpoint.javagraphlib.services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;
import org.techstartingpoint.javagraphlib.execution.GraphCompleteEnvironment;
import org.techstartingpoint.javagraphlib.execution.GraphExecutionModel;
import org.techstartingpoint.javagraphlib.graph.GraphConnection;
import org.techstartingpoint.javagraphlib.graph.GraphNode;
import org.techstartingpoint.javagraphlib.graph.GraphNodeType;
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

public class GraphProcessSetService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String BASE_PACKAGE="org.techstartingpoint.javagraphlib.components.";

    /**
     * Get Activity by a given name in a given job
     * @param workflowFileName
     * @return
     */
    public GraphExecutionModel getExecutionModel(String workflowFileName,GraphCompleteEnvironment environment) throws IOException, URISyntaxException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // read workflow
        String jsonWorkflowString=readJson(workflowFileName);

        // generate object java
        Workflow jsonWorkflow = convert(jsonWorkflowString);

        log.debug(jsonWorkflow.toString());

        // generate graph process
        GraphExecutionModel result = convert(workflowFileName,jsonWorkflow,environment);

    	return result;
    }

    private GraphExecutionModel convert(String name,Workflow jsonWorkflow,GraphCompleteEnvironment environment) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, URISyntaxException, MalformedURLException, ClassNotFoundException {
        // Map to store and search nodes
        Map<String,GraphNode> nodeMap=new HashMap<String,GraphNode>();
        GraphExecutionModel graphExecutionModel=new GraphExecutionModel();
        for (Node node:jsonWorkflow.getNodes()) {
            String implementationName=BASE_PACKAGE+node.getComponent_info().getName();
            GraphNodeType nodeType=new GraphNodeType(implementationName,node.getEnvironment_key(),node.getConf());
            GraphNode graphNode = new GraphNode(node.getId(),implementationName,nodeType,node.getEnvironment_key(),node.getConf());
            AbstractMainExecutor executorElement =buildComponentExecutor(graphNode,environment);
            graphExecutionModel.getExecutors().put(executorElement.getNodeId().toString(),executorElement);
            environment.showMessage(graphNode.getId(), "added class "+executorElement.getComponentClassName());
            nodeMap.put(node.getId(),graphNode);
        };
        long idConnector=0;
        for (Connection connection:jsonWorkflow.getConnections()) {
            String sourceId=connection.getFrom().getNode_id();
            String targetId=connection.getTo().getNode_id();
            if (sourceId!=null && targetId!=null) {
                GraphNode sourceNode=nodeMap.get(sourceId);
                GraphNode targetNode=nodeMap.get(targetId);
                if (sourceNode!=null && targetNode!=null) {
                    GraphConnection graphConnection=
                            new GraphConnection(
                                    idConnector,
                                    sourceNode,connection.getFrom().getPort_index(),
                                    targetNode,connection.getTo().getPort_index());
                    graphExecutionModel.getConnectors().add(graphConnection);
                }
            }
            idConnector++;
        }
        System.out.println(graphExecutionModel.toString());
        return graphExecutionModel;
    }

    private static AbstractMainExecutor buildComponentExecutor(GraphNode graphNode,  GraphCompleteEnvironment environment) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = graphNode.getType().getClassName();
        Class<?> clazz;
        clazz = Class.forName(className);
        AbstractMainExecutor executorElement =(AbstractMainExecutor) clazz.newInstance();
        executorElement.setNodeId(graphNode.getId().toString());
        executorElement.setGraphEnvironment(environment);
        executorElement.setEnvironmentKey(graphNode.getEnvironmentKey());
        executorElement.setNodeConfiguration(graphNode.getNodeConfiguration());
        return executorElement;
    }

    /**
     * Generate plain Workflow object from workflow string
     * @param jsonWorkflowString
     * @return
     */
    private Workflow convert(String jsonWorkflowString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonWorkflowString, Workflow.class);

    }



    /**
     * Read a workflow from file and returns the workflow in a String
     * @param fileName
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    private String readJson(String fileName) throws URISyntaxException, IOException {
        java.net.URL resource=getClass().getClassLoader().getResource(fileName);
        String jsonString=new String(Files.readAllBytes(Paths.get(resource.toURI())));
        log.debug(jsonString);
        return jsonString;
    }
    
}
