package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class Start extends AbstractMainExecutor {

    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public Start(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    public Start() {
        super("ReadFile",
                "org.techstartingpoint.javagraphlib.components.Start",
                "org.techstartingpoint.javagraphlib.components.Start",
                0,
                1);
    }


    @Override
    protected void runMain() throws Exception {
        System.out.println("Start");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }


}
