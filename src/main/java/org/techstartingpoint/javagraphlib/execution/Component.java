package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.oldcore.InputPort;
import org.techstartingpoint.javagraphlib.graph.OutputPort;

public interface Component {
    public  int getInPorts();
    public  int getOutPorts();
    public InputPort getInputPort(int index);
    public OutputPort getOutputPort(int index);
    public void process();

}
