package org.techstartingpoint.javagraphlib.model.workflow;

public class Node {
    String id;
    ComponentInfo component_info;
    String environment_key;
    NodeConfiguration conf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ComponentInfo getComponent_info() {
        return component_info;
    }

    public void setComponent_info(ComponentInfo component_info) {
        this.component_info = component_info;
    }

    public String getEnvironment_key() {
        return environment_key;
    }

    public void setEnvironment_key(String environment_key) {
        this.environment_key = environment_key;
    }

    public NodeConfiguration getConf() {
        return conf;
    }

    public void setConf(NodeConfiguration conf) {
        this.conf = conf;
    }

    @Override
    public String toString() {
        return "\tNode{" +
                "id='" + id + '\'' +
                ", component_info=" + component_info +
                ", environment_key='" + environment_key + '\'' +
                ", conf=" + conf +
                "\n"+
                '}';
    }
}
