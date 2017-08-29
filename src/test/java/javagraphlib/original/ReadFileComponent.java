package javagraphlib.original;

public class ReadFileComponent implements Component {

    private String conf = null;

    OutputPort<DataPacket> dataPacketOutputPort = null;

    ReadFileComponent(String conf) {
        this.conf = conf;
        initialize();
    }

    private void initialize() {

    }

    @Override
    public int getInPorts() {
        return 0;
    }

    @Override
    public int getOutPorts() {
        return 1;
    }

    @Override
    public InputPort getInputPort(int index) {
        return null;
    }

    @Override
    public OutputPort getOutputPort(int index) {
        if (index == 0) { return dataPacketOutputPort; }
        else return null;
    }

    @Override
    public void process() {
        // In this process Build dataPacketOutputPort
    }
}
