package org.techstartingpoint.javagraphlib.components.lib;

import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;

public class SQLExecutor extends AbstractMainExecutor {
    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public SQLExecutor(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    public SQLExecutor() {
        super("SQLExecutor",
                "org.techstartingpoint.javagraphlib.components.lib.SQLExecutor",
                "org.techstartingpoint.javagraphlib.components.lib.SQLExecutor",
                1,
                1);
    }

    @Override
    protected void runCore(Object message) throws Exception {
        System.out.println("SQLExecutor");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }


}
