package org.techstartingpoint.javagraphlib.components;


import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

public class WriteFile extends AbstractMainExecutor {
    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public WriteFile(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    @Override
    protected void runMain() throws Exception {
        System.out.println("WriteFile");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
        System.out.println("input(0)="+this.getInputValue(0));

    }

    public WriteFile() {
        super("WriteFile",
                "org.techstartingpoint.javagraphlib.components.WriteFile",
                "org.techstartingpoint.javagraphlib.components.WriteFile",
                1,
                0);
    }

}
