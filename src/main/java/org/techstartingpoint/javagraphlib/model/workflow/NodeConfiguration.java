package org.techstartingpoint.javagraphlib.model.workflow;

import java.util.List;

public class NodeConfiguration {
    String dataset;
    String dataframe;
    List<JoinTableSpecification> joinTableSpecs;

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getDataframe() {
        return dataframe;
    }

    public void setDataframe(String dataframe) {
        this.dataframe = dataframe;
    }

    public List<JoinTableSpecification> getJoinTableSepcs() {
        return joinTableSpecs;
    }

    public void setJoinTableSepcs(List<JoinTableSpecification> joinTableSpecs) {
        this.joinTableSpecs = joinTableSpecs;
    }

    @Override
    public String toString() {
        return "NodeConfiguration{" +
                "dataset='" + dataset + '\'' +
                ", dataframe='" + dataframe + '\'' +
                ", joinTableSpecs=" + joinTableSpecs +
                '}';
    }
}
