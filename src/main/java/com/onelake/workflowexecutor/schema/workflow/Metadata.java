package com.onelake.workflowexecutor.schema.workflow;

public class Metadata {
    String type;
    String api_version;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "type='" + type + '\'' +
                ", api_version='" + api_version + '\'' +
                '}';
    }
}
