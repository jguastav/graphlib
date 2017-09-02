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

    public int getTotalInputPorts() {return 1;}
    public int getTotalOutputPorts() {return 0;}
    public String getName() {return "WriteFile";}



}
