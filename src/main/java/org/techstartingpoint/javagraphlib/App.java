package org.techstartingpoint.javagraphlib;


import org.techstartingpoint.javagraphlib.execution.GraphRunnerEnvironmentImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Application to load and add a flow based on a workflow files referencing to existing classes components
 */
public class App {


    static GraphRunnerEnvironmentImpl runnerEnvironment;

    public static void main(String[] args) throws Throwable {

        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");

        // String jsonFileName=args[0];
        // String workflowFileName=jsonFileName;
        // TODO: quit line to get argument
        String workflowFileName="workflow.json";
        workflowFileName="workflow2WithStart.json";

        runnerEnvironment=new GraphRunnerEnvironmentImpl();
        runnerEnvironment.run(workflowFileName);

    }


}
