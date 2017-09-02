package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class SQLExecutor extends AbstractMainExecutor {

    public String getName() {return "SQLExecutor";}

    private static Class[] inputClasses={String.class};
    private static Class[] outputClasses={String.class};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}

    @Override
    protected void runMain() throws Exception {
        System.out.println("SQLExecutor");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
    }


}
