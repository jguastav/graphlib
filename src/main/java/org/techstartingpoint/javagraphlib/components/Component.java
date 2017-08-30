package org.techstartingpoint.javagraphlib.components;

import org.techstartingpoint.javagraphlib.core.InputPort;
import org.techstartingpoint.javagraphlib.model.OutputPort;

public interface Component {
    public  int getInPorts();
    public  int getOutPorts();
    public InputPort getInputPort(int index);
    public OutputPort getOutputPort(int index);
    public void process();

}
