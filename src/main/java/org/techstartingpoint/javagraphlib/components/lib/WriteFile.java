package org.techstartingpoint.javagraphlib.components.lib;


import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;

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
    protected void runCore(Object message) throws Exception {
        System.out.println("WriteFile");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }

    public WriteFile() {
        super("ReadFile",
                "org.techstartingpoint.javagraphlib.components.lib.Filter",
                "org.techstartingpoint.javagraphlib.components.lib.Filter",
                1,
                1);
    }

}
