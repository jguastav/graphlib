package org.techstartingpoint.javagraphlib;


import com.onelake.api.error.OnelakeException;
import org.techstartingpoint.javagraphlib.execution.GraphRunnerEnvironmentImpl;


/**
 * Application to load and add a flow based on a workflow files referencing to existing classes components
 */
public class App {


    static GraphRunnerEnvironmentImpl runnerEnvironment;

    public static void main(String[] args) throws OnelakeException {



        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");

        // String jsonFileName=args[0];
        // String workflowFileName=jsonFileName;
        runnerEnvironment=new GraphRunnerEnvironmentImpl();

        String workflowFileName=args[0];
        System.out.println("running "+workflowFileName);
        runnerEnvironment.run(workflowFileName);
        System.out.println("==============================================================================running ");


    }





}
