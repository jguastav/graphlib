package org.techstartingpoint.javagraphlib.components;


import org.techstartingpoint.javagraphlib.api.AbstractMainExecutor;

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
                "org.techstartingpoint.javagraphlib.components.Join",
                "org.techstartingpoint.javagraphlib.components.Join",
                2,
                1);
    }

    @Override
    protected void runMain() throws Exception {
        System.out.println("Join");
        System.out.println(this.getEnvironmentKey());
        System.out.println(this.getNodeConfiguration());
        System.out.println("input:"+this.getInputPorts());
        System.out.println("output:"+this.getOutputPorts());
        System.out.println("input(0)="+this.getInputValue(0));
        System.out.println("input(1)="+this.getInputValue(1));
        this.setOutputValue(0,"join:"+this.getInputValue(0)+" - "+this.getInputValue(1));
    }


}
