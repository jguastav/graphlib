package com.onelake.workflowexecutor.components.mock;

import com.onelake.workflowexecutor.api.AbstractMainExecutor;

public class SQLExecutorMock extends AbstractMainExecutor {

    public String getName() {return "SQLExecutorMock";}

    private static Class[] inputClasses={String.class};
    private static Class[] outputClasses={String.class};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}

    @Override
    protected void runMain()  {
        System.out.println("SQLExecutorMock");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
    }


}
