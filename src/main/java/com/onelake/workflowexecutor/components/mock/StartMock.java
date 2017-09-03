package com.onelake.workflowexecutor.components.mock;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class StartMock extends AbstractMainExecutor {

    private static Class[] inputClasses={};
    private static Class[] outputClasses={String.class};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}

    public String getName() {return "StartMock";}


    @Override
    protected void runMain() throws Exception {
        System.out.println("StartMock");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
    }


}
