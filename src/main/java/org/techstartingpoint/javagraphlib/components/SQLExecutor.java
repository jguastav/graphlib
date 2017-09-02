package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class SQLExecutor extends AbstractMainExecutor {

    public int getTotalInputPorts() {return 1;}
    public int getTotalOutputPorts() {return 1;}
    public String getName() {return "SQLExecutor";}


    @Override
    protected void runMain() throws Exception {
        System.out.println("SQLExecutor");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getTotalInputPorts());
        System.out.println("output:"+this.getTotalOutputPorts());
    }


}
