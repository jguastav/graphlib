package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;

public class ReadFile extends AbstractMainExecutor {
    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public ReadFile(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    public ReadFile() {
        super("ReadFile",
                "org.techstartingpoint.javagraphlib.components.ReadFile",
                "org.techstartingpoint.javagraphlib.components.ReadFile",
                1,
                1);
    }


    @Override
    protected void runMain() throws Exception {
        System.out.println("ReadFile");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }


}
