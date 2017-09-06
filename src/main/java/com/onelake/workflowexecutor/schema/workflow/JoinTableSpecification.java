package com.onelake.workflowexecutor.schema.workflow;

import java.util.List;

public class JoinTableSpecification {
    String join_dataset;
    List<String> join_fileds;
    String join_type;

    public String getJoin_dataset() {
        return join_dataset;
    }

    public void setJoin_dataset(String join_dataset) {
        this.join_dataset = join_dataset;
    }

    public List<String> getJoin_fileds() {
        return join_fileds;
    }

    public void setJoin_fileds(List<String> join_fileds) {
        this.join_fileds = join_fileds;
    }

    public String getJoin_type() {
        return join_type;
    }

    public void setJoin_type(String join_type) {
        this.join_type = join_type;
    }

    @Override
    public String toString() {
        return "JoinTableSpecification{" +
                "join_dataset='" + join_dataset + '\'' +
                ", join_fileds=" + join_fileds +
                ", join_type='" + join_type + '\'' +
                '}';
    }
}
