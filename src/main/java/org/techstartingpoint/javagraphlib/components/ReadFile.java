package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class ReadFile extends AbstractMainExecutor {

    public ReadFile() {}

    private static Class[] inputClasses={String.class};
    private static Class[] outputClasses={String.class};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}

    public String getName() {return "ReadFile";}


    @Override
    protected void runMain() throws Exception {
        System.out.println("ReadFile");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        this.setOutputValue(0,"generated by read component");
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
        System.out.println("input:"+this.getInputValue(0));
    }


}
