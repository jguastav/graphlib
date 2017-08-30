
package org.techstartingpoint.javagraphlib.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techstartingpoint.javagraphlib.dao.GraphProcessMongoRepository;
import org.techstartingpoint.javagraphlib.model.GraphProcess;
import org.techstartingpoint.javagraphlib.model.GraphProcessSet;
import org.techstartingpoint.javagraphlib.model.GraphNode;

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

    /**
     * Get Activity by a given name in a given job
     * @param workflowFileName
     * @return
     */
    public GraphProcess getGraphProcess(String workflowFileName) throws IOException, URISyntaxException {

        String jsonWorkflow=readJson(workflowFileName);



    	GraphProcess result=null;


    	// TODO: READ JSON
		// TODO: Convert json to GraphProcess

    	return result;
    }


    /**
     * Read a json from file and returns the json in a String
     * @param fileName
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    private String readJson(String fileName) throws URISyntaxException, IOException {
        String jsonString=new String(Files.readAllBytes(Paths.get(getClass().getResource(fileName).toURI())));
        log.debug(jsonString);
        return jsonString;
    }
    
}
