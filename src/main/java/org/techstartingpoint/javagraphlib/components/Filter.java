package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;

public class Filter extends AbstractMainExecutor {
    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public Filter(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    public Filter() {
        super("Filter",
                "org.techstartingpoint.javagraphlib.components.Filter",
                "org.techstartingpoint.javagraphlib.components.Filter",
                1,
                1);
    }

    @Override
    protected void runCore(Object message) throws Exception {
        System.out.println("Filter");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }


}
