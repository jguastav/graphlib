package javagraphlib.original;

import javagraphlib.original.notgiven.DataPacket;

public class WriteFileComponent implements Component {

    private String conf = null;

    InputPort<DataPacket> dataPacketInputPort = null;

    WriteFileComponent(String conf, InputPort<DataPacket> dataPacketInputPort) {
        this.conf = conf;
        this.dataPacketInputPort = dataPacketInputPort;
        initialize();
    }

    private void initialize() {

    }

    @Override
    public int getInPorts() {
        return 1;
    }

    @Override
    public int getOutPorts() {
        return 0;
    }

    @Override
    public InputPort getInputPort(int index) {
        if (index == 0) {
            return dataPacketInputPort;
        } else {
            return null;
        }
    }

    @Override
    public OutputPort getOutputPort(int index) {
        return null;
    }

    @Override
    public void process() {
        // In this process Build
    }
}