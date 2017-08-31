package org.techstartingpoint.javagraphlib.model.workflow;

public class Port {
    String node_id;
    int port_index;

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public int getPort_index() {
        return port_index;
    }

    public void setPort_index(int port_index) {
        this.port_index = port_index;
    }

    @Override
    public String toString() {
        return "Port{" +
                "node_id='" + node_id + '\'' +
                ", port_index=" + port_index +
                '}';
    }
}
