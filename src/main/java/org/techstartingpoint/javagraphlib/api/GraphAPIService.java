
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

	private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String BASE_PACKAGE="org.techstartingpoint.javagraphlib.components.";

    /**
     * Get Activity by a given name in a given job
     * @param workflowFileName
     * @return
     */
    public ExecutorModel getExecutionModel(String workflowFileName) throws IOException, URISyntaxException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // read workflow
        String jsonWorkflowString=readJson(workflowFileName);

        // generate object java
        Workflow jsonWorkflow = convert(jsonWorkflowString);

        log.debug(jsonWorkflow.toString());

        // generate graph process
        ExecutorModel result = convert(jsonWorkflow);

    	return result;
    }

    private ExecutorModel convert(Workflow jsonWorkflow) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, URISyntaxException, MalformedURLException, ClassNotFoundException {
        // Map to store and search nodes
        Map<String,AbstractMainExecutor> nodeMap=new HashMap<String,AbstractMainExecutor>();
        ExecutorModel executorModel =new ExecutorModel();
        for (Node node:jsonWorkflow.getNodes()) {
            String implementationName=BASE_PACKAGE+node.getComponent_info().getName();
            AbstractMainExecutor executorElement=AbstractMainExecutor.create(implementationName,node.getId(),node.getEnvironment_key(),node.getConf());
            executorModel.getExecutors().put(executorElement.getNodeId(),executorElement);
            nodeMap.put(node.getId(),executorElement);
        }
        long idConnector=0;
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
                    executorModel.getConnectors().add(graphConnection);
                }
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
