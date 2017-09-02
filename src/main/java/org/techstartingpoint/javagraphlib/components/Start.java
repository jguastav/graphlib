package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class Start extends AbstractMainExecutor {


    public int getTotalInputPorts() {return 0;}
    public int getTotalOutputPorts() {return 1;}
    public String getName() {return "ReadFile";}


    @Override
    protected void runMain() throws Exception {
        System.out.println("Start");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
    }


}
