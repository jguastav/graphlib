package org.techstartingpoint.javagraphlib.components;


import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class WriteFile extends AbstractMainExecutor {

    @Override
    protected void runMain() throws Exception {
        System.out.println("WriteFile");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
        System.out.println("input(0)="+this.getInputValue(0));

    }

    public String getName() {return "WriteFile";}

    private static Class[] inputClasses={String.class};
    private static Class[] outputClasses={};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}



}
