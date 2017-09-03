package com.onelake.workflowexecutor.components.mock;


import com.onelake.api.error.OnelakeException;
import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class WriteFileMock extends AbstractMainExecutor {

    @Override
    protected void runMain()  {
        System.out.println("WriteFileMock");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
        System.out.println("input(0)="+this.getInputValue(0));

    }

    public String getName() {return "WriteFileMock";}

    private static Class[] inputClasses={String.class};
    private static Class[] outputClasses={};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}



}
