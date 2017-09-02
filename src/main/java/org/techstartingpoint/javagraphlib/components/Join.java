package org.techstartingpoint.javagraphlib.components;


import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class Join extends AbstractMainExecutor {

    public int getTotalInputPorts() {return 2;}
    public int getTotalOutputPorts() {return 1;}
    public String getName() {return "Join";}


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
