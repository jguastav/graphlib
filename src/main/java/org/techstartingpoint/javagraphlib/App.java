package org.techstartingpoint.javagraphlib;


import org.techstartingpoint.javagraphlib.graphengine.impl.GraphRunnerEnvironmentImpl;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Application to load and run a flow based on a json files referencing to existing classes components
 */
public class App {


    static GraphRunnerEnvironmentImpl runnerEnvironment;

    public static void main(String[] args) throws IOException, URISyntaxException {

        // String jsonFileName=args[0];
        // String workflowFileName=jsonFileName;
        // TODO: quit line to get argument
        String workflowFileName="workflow.json";
        runnerEnvironment=new GraphRunnerEnvironmentImpl();
        runnerEnvironment.run(workflowFileName);

    }


}
