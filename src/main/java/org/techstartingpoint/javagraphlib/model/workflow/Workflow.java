package org.techstartingpoint.javagraphlib.model.workflow;

import java.util.List;

public class Workflow {
    String id;
    Metadata metadata;
    List<Node> nodes;
    List<Connection> connections;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id='" + id + '\'' +
                ", metadata=" + metadata +
                ", nodes=" + nodes +
                ", connections=" + connections +
                '}';
    }
}
