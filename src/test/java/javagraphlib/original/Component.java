package javagraphlib.original;

public interface Component {
    public  int getInPorts();
    public  int getOutPorts();
    public InputPort getInputPort(int index);
    public OutputPort getOutputPort(int index);
    public void process();

}
