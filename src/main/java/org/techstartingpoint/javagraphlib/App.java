package org.techstartingpoint.javagraphlib;


import org.techstartingpoint.javagraphlib.graphengine.impl.GraphRunnerEnvironmentImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

/**
 * Application to load and run a flow based on a json files referencing to existing classes components
 */
public class App {


    static GraphRunnerEnvironmentImpl runnerEnvironment;

    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");

        // String jsonFileName=args[0];
        // String workflowFileName=jsonFileName;
        // TODO: quit line to get argument
        String workflowFileName="workflow.json";
        runnerEnvironment=new GraphRunnerEnvironmentImpl();
        runnerEnvironment.run(workflowFileName);

    }


}
