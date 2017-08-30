
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
import org.techstartingpoint.javagraphlib.dao.GraphProcessMongoRepository;
import org.techstartingpoint.javagraphlib.model.*;
import org.techstartingpoint.javagraphlib.model.json.Connection;
import org.techstartingpoint.javagraphlib.model.json.Node;
import org.techstartingpoint.javagraphlib.model.json.Workflow;

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
    private static final String BASE_PACKAGE="org.techstartingpoint.javagraphlib.components.lib.";

    /**
     * Get Activity by a given name in a given job
     * @param workflowFileName
     * @return
     */
    public GraphProcess getGraphProcess(String workflowFileName) throws IOException, URISyntaxException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // read json
        String jsonWorkflowString=readJson(workflowFileName);

        // generate object java
        Workflow jsonWorkflow = convert(jsonWorkflowString);

        log.debug(jsonWorkflow.toString());

        // generate graph process
        GraphProcess result = convert(workflowFileName,jsonWorkflow);

    	return result;
    }

    private GraphProcess convert(String name,Workflow jsonWorkflow) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, URISyntaxException, MalformedURLException, ClassNotFoundException {
        // Map to store and search nodes
        Map<String,GraphNode> nodeMap=new HashMap<String,GraphNode>();


        GraphProcess graphProcess=new GraphProcess(jsonWorkflow.getId(),name);
        List<GraphConnection> connections=graphProcess.getConnectorList();
        for (Node node:jsonWorkflow.getNodes()) {
            String implementationName=BASE_PACKAGE+node.getComponent_info().getName();
            GraphNodeType nodeType=new GraphNodeType(implementationName,node.getEnvironment_key(),node.getConf());
            GraphNode graphNode = new GraphNode(node.getId(),implementationName,nodeType);
            graphProcess.getNodeList().add(graphNode);
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
                }
            }
            idConnector++;
        }
        return graphProcess;
    }


    /**
     * Generate plain Workflow object from json string
     * @param jsonWorkflowString
     * @return
     */
    private Workflow convert(String jsonWorkflowString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonWorkflowString, Workflow.class);

    }



    /**
     * Read a json from file and returns the json in a String
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
