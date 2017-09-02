package org.techstartingpoint.javagraphlib.components;


import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class Join extends AbstractMainExecutor {

    public String getName() {return "Join";}


    private static Class[] inputClasses={String.class,String.class};
    private static Class[] outputClasses={String.class};
    public Class[] getInputClasses() {return inputClasses;}
    public Class[] getOutputClasses() {return outputClasses;}


    @Override
    protected void runMain() throws Exception {
        System.out.println("Join");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
        System.out.println("input(0)="+this.getInputValue(0));
        System.out.println("input(1)="+this.getInputValue(1));
        this.setOutputValue(0,"join:"+this.getInputValue(0)+" - "+this.getInputValue(1));
    }


}
