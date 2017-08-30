package org.techstartingpoint.javagraphlib.components.lib;


import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;

public class Join extends AbstractMainExecutor {
    /**
     * @param name
     * @param componentClassName
     * @param className
     * @param inputPorts
     * @param outputPorts
     * @author Jose Alberto Guastavino
     */
    public Join(String name, String componentClassName, String className, int inputPorts, int outputPorts) {
        super(name, componentClassName, className, inputPorts, outputPorts);
    }

    public Join() {
        super("Join",
                "org.techstartingpoint.javagraphlib.components.lib.Filter",
                "org.techstartingpoint.javagraphlib.components.lib.Filter",
                2,
                1);
    }

    @Override
    protected void runCore(Object message) throws Exception {
        System.out.println("Join");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
    }


}
